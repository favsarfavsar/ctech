package com.cookerytech.service;

import com.cookerytech.domain.Offer;
import com.cookerytech.domain.OfferItem;
import com.cookerytech.domain.Role;
import com.cookerytech.domain.enums.RoleType;
import com.cookerytech.dto.OfferItemDTO;
import com.cookerytech.dto.request.OfferItemsUpdate;
import com.cookerytech.exception.BadRequestException;
import com.cookerytech.exception.ResourceNotFoundException;
import com.cookerytech.exception.message.ErrorMessage;
import com.cookerytech.mapper.OfferItemMapper;
import com.cookerytech.mapper.OfferMapper;
import com.cookerytech.repository.OfferItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OfferItemService {

    private final OfferItemRepository offerItemRepository;
    private final UserService userService;

    private final OfferItemMapper offerItemMapper;

    private final OfferService offerService;



    public OfferItemService(OfferItemRepository offerItemRepository, UserService userService, OfferItemMapper offerItemMapper, OfferService offerService) {
        this.offerItemRepository = offerItemRepository;
        this.userService = userService;
        this.offerItemMapper = offerItemMapper;
        this.offerService = offerService;
    }


    public List<OfferItem> getOfferItems(Long offerId) {

        List<OfferItem> offerItems = offerItemRepository.findByOfferId(offerId);
        return offerItems;
    }

    //Satışı yapılan ürün miktarını return ediyor.
    public List<Integer> stockAmountDecrease(Long offerId) {
        List<OfferItem> offerItems = getOfferItems(offerId);
        List<Integer>salesAmount = new ArrayList<>();

        for (int i=0; i<offerItems.size(); i++){
            int quantity = offerItems.get(i).getQuantity();
            int oldStockAmount = offerItems.get(i).getModel().getStockAmount();
            int newStockAmount = oldStockAmount-quantity;
            offerItems.get(i).getModel().setStockAmount(newStockAmount);
            salesAmount.add(quantity);
        }
        return salesAmount;
    }

    public Boolean existsOfferItemsByProductId(Long productId) {
       return offerItemRepository.existsByProductId(productId);

    }

    public List<OfferItemDTO> updateOfferItems(Long id, OfferItemsUpdate offerItemsUpdate) {

        OfferItem offerItem = new OfferItem();
        Offer offer = new Offer();
        List<OfferItem> offerItemList = getOfferItems(id);

        if((offer.getStatus().name().equals("CREATED") || offer.getStatus().name().equals("REJECTED"))){

            offerItem.setQuantity(offerItemsUpdate.getQuantity());
            offerItem.setSellingPrice(offerItemsUpdate.getPrice());
            offerItem.setTax(offerItemsUpdate.getTax());
            offerItem.setDiscount(offerItemsUpdate.getDiscount());

        }else {
            throw new BadRequestException(ErrorMessage.NOT_PERMITTED_METHOD_MESSAGE);
        }

        List<OfferItemDTO> offerItemDTOs = offerItemMapper.map(offerItemList);

        return offerItemDTOs;

    }

    public OfferItemDTO deleteOfferItemsById(Long id) {

        OfferItem offerItem = getOfferItem(id);
        Offer offer = new Offer();

        if((offer.getStatus().name().equals("CREATED") || offer.getStatus().name().equals("REJECTED"))){

            offerItemRepository.delete(offerItem);

        }else {
            throw new BadRequestException(ErrorMessage.NOT_PERMITTED_METHOD_MESSAGE);
        }

        return offerItemMapper.OfferItemToOfferItemDTO(offerItem);


    }

    public OfferItem getOfferItem(Long id) {
        OfferItem offerItem = offerItemRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_EXCEPTION))
        );
        return offerItem;
    }
}
