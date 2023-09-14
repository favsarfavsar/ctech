package com.cookerytech.service;

import com.cookerytech.config.EmailConfig;
import com.cookerytech.domain.Offer;
import com.cookerytech.domain.OfferItem;
import com.cookerytech.domain.Role;
import com.cookerytech.domain.User;
import com.cookerytech.domain.enums.OfferStatus;
import com.cookerytech.domain.enums.RoleType;
import com.cookerytech.dto.OfferDTO;
import com.cookerytech.dto.request.OfferCreate;
import com.cookerytech.dto.request.OfferUpdate;
import com.cookerytech.dto.response.OfferCreateResponse;
import com.cookerytech.exception.BadRequestException;
import com.cookerytech.exception.ResourceNotFoundException;
import com.cookerytech.exception.message.ErrorMessage;
import com.cookerytech.mapper.OfferMapper;
import com.cookerytech.repository.OfferRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.cookerytech.dto.response.OfferResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;
    private final UserService userService;
    private final OfferItemService offerItemService;
    private final JavaMailSender mailSender;
    private final CurrencyService currencyService;

    public OfferService(OfferRepository offerRepository, OfferMapper offerMapper, UserService userService, @Lazy OfferItemService offerItemService, JavaMailSender mailSender, CurrencyService currencyService) {
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
        this.userService = userService;
        this.offerItemService = offerItemService;
        this.mailSender = mailSender;
        this.currencyService = currencyService;
    }

    public Page<OfferDTO> findFilteredOffers(String query, Integer statusValue, LocalDate date1, LocalDate date2,
                                             Pageable pageable) {

        OfferStatus status = null;
        if (statusValue != null) {
            status = OfferStatus.fromValue(statusValue);
        } else {
            
            User currentUser = userService.getCurrentUser(); // Assuming you have a method to get the current user

            boolean isSalesSpecialist = false;
            boolean isSalesManager = false;

            for (Role role : currentUser.getRoles()) {
                if (role.getType().equals(RoleType.ROLE_SALES_MANAGER)) {
                    isSalesManager = true;
                    break;
                } else if (role.getType().equals(RoleType.ROLE_SALES_SPECIALIST)) {
                    isSalesSpecialist = true;
                    break;
                }
            }

            if (isSalesManager) {
                status = OfferStatus.fromValue(OfferStatus.WAITING_FOR_APPROVAL.getValue()); // Sales Managers default status
            } else if (isSalesSpecialist) {
                status = OfferStatus.fromValue(OfferStatus.CREATED.getValue()); // Sales Specialists default status
            }

        }

//        LocalDateTime dateTime1 = date1 != null ? date1.atStartOfDay() : null;
//        LocalDateTime dateTime2 = date2 != null ? date2.atStartOfDay().plusDays(1).minusSeconds(1) : null;

        /*LocalDateTime dateTime1 = date1 != null ? date1.atStartOfDay() : null;
        LocalDateTime dateTime2 = date2 != null ? date2.atTime(23, 59, 59) : null;*/

//        LocalDateTime startDateTime = date1 != null ? LocalDateTime.parse(date1 + "T00:00:00") :
//                LocalDateTime.now().minusYears(2000);
//        LocalDateTime endDateTime = date2 != null ? LocalDateTime.parse(date2 + "T23:59:59") :
//                LocalDateTime.now();
        LocalDateTime startDateTime = date1 != null ?
                LocalDateTime.of(date1.getYear(), date1.getMonth(), date1.getDayOfMonth(), 0, 0, 0) :
                LocalDateTime.now().minusYears(2000);
        LocalDateTime endDateTime = date2 != null ?
                LocalDateTime.of(date2.getYear(), date2.getMonth(), date2.getDayOfMonth(), 23, 59, 59) :
                LocalDateTime.now();

        System.out.println(startDateTime + "<- ilk tarih | ikinci tarih ->" + endDateTime);
        System.out.println(new Offer().getSubTotal() + " <- bu sub total");
        System.out.println(new OfferDTO().getSubTotal() + " <- bu OfferDTO sub total");
        System.out.println(new Offer().getDiscount() + " <- bu discount");
        System.out.println(new OfferDTO().getDiscount() + " <- bu OfferDTO discount");
        System.out.println(new Offer().getGrandTotal() + " <- bu grand total");
        System.out.println(new OfferDTO().getGrandTotal() + " <- bu OfferDTO grand total");

        Page<Offer> filteredOffers = offerRepository.findFilteredOffers(query, status, startDateTime, endDateTime, pageable);


        return filteredOffers.map(offerMapper::offerToOfferDTO);
    }

    public OfferDTO findByIdAndUser(Long id, User user) {

        Offer offer = offerRepository.findByIdAndUser(id, user).orElseThrow(
                ()-> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_EXCEPTION))
        );

        return offerMapper.offerToOfferDTO(offer);


    }

    public Page<OfferDTO> getAllOffers(String qLower, String date1, String date2, String statusLower, Pageable pageable) {

        Page<Offer> offers = null;

        if (!qLower.isEmpty() || !date1.isEmpty() || !date2.isEmpty() || !statusLower.isEmpty()) {
            offers = offerRepository.getAllOffers(qLower,date1,date2,statusLower, pageable);
        } else {
            offers = offerRepository.findAllOffersWithPage(pageable);
        }

        if(offers.isEmpty()) {
            throw new ResourceNotFoundException(String.format(ErrorMessage.NO_DATA_IN_DB_TABLE_MESSAGE, "Offers"));
        }
        return offers.map(offerMapper::offerToOfferDTO);
    }

      public List<OfferResponse> getOffersByUserId(Long id) {
       return offerMapper.offersToOfferResponses(offerRepository.findAllByUserId(id));
    }

    public Offer getById(Long id){
        Offer offer = offerRepository.findByOfferId(id).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_EXCEPTION,id)));
        return offer;
    }
    public OfferDTO getOfferDTO(Long id) {
        Offer offer = getById(id);
        return offerMapper.offerToOfferDTO(offer);
    }


    //****************** offer create ********************************//
    //TODO : mail atılacak customer ve SS ye
    public OfferCreateResponse makeOffer(OfferCreate offerCreate) {
        User user = userService.getCurrentUser();
        OfferCreateResponse offerCreateResponse = new OfferCreateResponse();    //return edilecek
        Offer newOffer = new Offer();                                           //DB'ye setlenecek
        //OfferId'ye ihtiyaç duyulduğu için offer create edildi
        newOffer.setDeliveryAt(offerCreate.getDeliveryDate());
        offerRepository.save(newOffer);

        Long offerId = newOffer.getId();

        //code oluşturuldu ve DB'de varmı kontrolü yapıldı
        Boolean isThereCode;
        String code;
        do{
            code = codeGenerate();
            isThereCode = codeTest(code);
        }while (!isThereCode);

        newOffer.setCode(code);
        //grand total setlendi
        newOffer.setGrandTotal(grandTotalCalculate(offerId));
        //stock amount azalt
        stockAmountDecrease(offerId);
        newOffer.setStatus(OfferStatus.CREATED);       //Burası AuthUser'a göre değişecek ise koda if eklenecek
        newOffer.setUser(user);
        newOffer.setCreateAt(LocalDateTime.now());
        newOffer.setUpdateAt(LocalDateTime.now());
        offerRepository.save(newOffer);

        //response için gerekli işlemler
        offerCreateResponse.setId(offerId);
        offerCreateResponse.setCode(code);
        offerCreateResponse.setStatus(newOffer.getStatus().name());
        offerCreateResponse.setItems(getOfferItems(offerId));

        //mail gönderme
        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(EmailConfig.constantEmail);
        message.setTo(user.getEmail());
        message.setSubject("New Offer");
        message.setText(offerCreateResponse.toString());
        mailSender.send(message);

        return offerCreateResponse;
    }

    //***** unique code generate  ********
    public String codeGenerate(){

        Random random = new Random();

        List<String> codeNumeric = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","R","S","T","U","V","W","Y","Z","0","1","2","3","4","5","6","7","8","9");
        StringBuilder uniqueCode = new StringBuilder();
        for (int i=0; i<8; i++){
            int randomNumber = random.nextInt(33-0+1)+0;
            uniqueCode.append(codeNumeric.get(randomNumber));
        }
        String newCode = uniqueCode.toString();
        return newCode;
    }

    //***** Create adilen code Database'de var mı? Kontrolü yapar     ********
    public Boolean codeTest(String code){
        return offerRepository.existsByCode(code);
    }

    //***** get offer items   ****************
    public List<OfferItem> getOfferItems(Long offerId){
        return offerItemService.getOfferItems(offerId);
    }

    //*****     grand_total calculate   *****
    public Double grandTotalCalculate(Long offerId){
        List<OfferItem> offerItems = getOfferItems(offerId);
        Double grandTotal =0.0;
        for (int i=0; i<offerItems.size(); i++){
            grandTotal+=offerItems.get(i).getSubTotal()*(1- (offerItems.get(i).getDiscount()/100));
        }
        return grandTotal;
    }

    //  *****   stock amount decrease   ****

    public List<Integer> stockAmountDecrease(Long offerId){
        return offerItemService.stockAmountDecrease(offerId);
    }

    public long numberOfOffersPerDay() {
        LocalDateTime now = LocalDateTime.now();

         // Son 24 saatlik zaman dilimi
        LocalDateTime twentyFourHoursAgo = now.minusHours(24);

       return offerRepository.numberOfOffersPerDay(twentyFourHoursAgo);
    }


    public OfferDTO updateOffers(Long id, OfferUpdate offerUpdate) {

        Offer offer = getOffer(id);

        Set<Role> userRole = userService.getCurrentUser().getRoles();

        boolean isAdmin = false;
        boolean isSalesManager = false;
        boolean isSalesSpecialist = false;

        for (Role role : userRole) {
            if (RoleType.ROLE_ADMIN.equals(role.getType())) {
                isAdmin = true;
            } else if (RoleType.ROLE_SALES_MANAGER.equals(role.getType())) {
                isSalesManager = true;
            } else if(RoleType.ROLE_SALES_SPECIALIST.equals(role.getType())){
                isSalesSpecialist = true;
            }
        }

        if((isSalesSpecialist && (offer.getStatus().name().equals("CREATED") || offer.getStatus().name().equals("REJECTED"))) ||
                (isSalesManager && (offer.getStatus().name().equals("WAITING_FOR_APPROVAL"))) ||
                isAdmin){

            offer.setDiscount(offerUpdate.getDiscount());
            offer.setGrandTotal( offer.getSubTotal() * (1- (offerUpdate.getDiscount()/100) ) );
            offer.setStatus(offerUpdate.getStatus());
            offer.setCurrency(currencyService.getCurrencyById(offerUpdate.getCurrencyId()));
            offer.setUpdateAt(LocalDateTime.now());

        }else {
            throw new BadRequestException(ErrorMessage.NOT_PERMITTED_METHOD_MESSAGE);
        }

        Offer updateOffer = offerRepository.save(offer);
        //List<OfferItem> offerItemList = offerItemService.getOfferItems(id);


        OfferDTO offerDTO = offerMapper.offerToOfferDTO(updateOffer);

        return offerDTO;
        //List<OfferItemDTO> offerItemDTOs = offerItemMapper.map(offerItemList);

//        UpdateOfferResponse offerResponse = new UpdateOfferResponse();
//        offerResponse.setOfferDTO(offerDTO);
//        offerResponse.setOfferItemDTOList(offerItemDTOs);

        // return offerResponse;


    }


    public Offer getOffer(Long id) {
        Offer offer = offerRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_EXCEPTION))
        );
        return offer;
    }
}
