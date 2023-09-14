package com.cookerytech.service;

import com.cookerytech.domain.*;
import com.cookerytech.dto.BrandDTO;
import com.cookerytech.dto.ModelDTO;
import com.cookerytech.dto.request.ModelCreatRequest;
import com.cookerytech.dto.request.ModelUpdateRequest;
import com.cookerytech.dto.response.ModelResponse;
import com.cookerytech.exception.BadRequestException;
import com.cookerytech.exception.ConflictException;
import com.cookerytech.exception.ResourceNotFoundException;
import com.cookerytech.exception.message.ErrorMessage;
import com.cookerytech.mapper.ModelMapper;
import com.cookerytech.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelService {

    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;
    private final ProductService productService;

    private final CurrencyService currencyService;

    private  final  ImageService imageService;



    public Model getModelById(Long id){
       return modelRepository.findById(id).
               orElseThrow(()-> new RuntimeException(String.format(ErrorMessage.MODEL_NOT_FOUND_EXCEPTION, id)));
    }

    public void updateModelById(Long id, ModelUpdateRequest modelUpdateRequest) {
        Model model = getModelById(id);

        if (model.getBuiltIn()){
            throw new BadRequestException(ErrorMessage.NOT_PERMITTED_METHOD_MESSAGE);
        }

        model.setTitle(modelUpdateRequest.getTitle());
        model.setSku(modelUpdateRequest.getSku());
        model.setStockAmount(modelUpdateRequest.getStockAmount());
        model.setInBoxQuantity(modelUpdateRequest.getInBoxQuantity());
        model.setSeq(modelUpdateRequest.getSeq());
        model.setBuyingPrice(modelUpdateRequest.getBuyingPrice());
        model.setTaxRate(modelUpdateRequest.getTaxRate());
        model.setIsActive(modelUpdateRequest.getIsActive());
        model.setUpdateAt(LocalDateTime.now());
        model.setProduct(productService.getById(modelUpdateRequest.getProductId()));
        model.setCurrency(currencyService.getCurrency(modelUpdateRequest.getCurrencyCode()));

    }


    public ModelResponse deleteModelById(Long id) {
        Model model = getModelById(id);
        if (model.getBuiltIn()){
            throw new BadRequestException(ErrorMessage.NOT_PERMITTED_METHOD_MESSAGE);
        }
        // ??  If the model has any related records in offer_items table,
        //  it can not be deleted and endpoint returns an error otherwise returns the model that just deleted

        // ?? – If any model is deleted, related records in model_property_values, cart_items should be deleted.

        modelRepository.delete(model);
        return modelMapper.modelToModelResponse(model);

    }

    public ModelDTO creatModel(ModelCreatRequest modelCreatRequest) {
        Boolean existsBySku= modelRepository.existsBySku(modelCreatRequest.getSku());

        if(existsBySku) {
            throw new ConflictException(ErrorMessage.SKU_ALREADY_EXİST);
        }
        LocalDateTime now = LocalDateTime.now();

       // String imageId =imageService.saveImage(modelCreatRequest.getImages());
       Set<String> imageIds = modelCreatRequest.getImages().stream().map(img->imageService.saveImage2(img)).collect(Collectors.toSet());

      //  Image savedImage = imageService.getImageById(imageId);
       Set<Image> savedImages = imageIds.stream().map(id->imageService.getImageById(id)).collect(Collectors.toSet());

        //Set<Image> setImage= new HashSet<>();
       // setImage.add(savedImage);

       // Set<Image> setImage= savedImageIds.stream().map(imageId->imageService.getImageById(imageId)).collect(Collectors.toSet());
        //Gelen currencyId ye göre currency getirilecek
        Currency currency=currencyService.getCurrencyById(modelCreatRequest.getCurrencyId());

        //Gelen productId ye göre product getirilecek
        Product product= productService.getById(modelCreatRequest.getProductId());



        Model model=new Model();
        model.setTitle(modelCreatRequest.getTitle());
        model.setSku(modelCreatRequest.getSku());
        model.setStockAmount(modelCreatRequest.getStockAmount());
        model.setInBoxQuantity(modelCreatRequest.getInBoxQuantity());
        model.setSeq(model.getSeq());
        model.setImages(savedImages);
        model.setBuyingPrice(modelCreatRequest.getBuyingPrice());
        model.setTaxRate(modelCreatRequest.getTaxRate());
        model.setIsActive(modelCreatRequest.getIsActive());
        model.setCurrency(currency);
        model.setProduct(product);
        model.setCreateAt(now);
        Model savedModel= modelRepository.save(model);
        return modelMapper.modelToModelDTO(savedModel);



    }


    public Page<ModelDTO> getModelDTOPage(Pageable pageable) {

        Page<Model> modelPage = modelRepository.getActiveModels(pageable);

        return modelPage.map(model -> modelMapper.modelToModelDTO(model));

    }

    public List<ModelDTO> getModelsByProductId(Long productId) {
        List<Model> modelList= modelRepository.findAllByProductId(productId);
        return  modelMapper.map(modelList);

    }

    @Transactional
    public List<Long> getModelIdsByProductId(Long productId) {
        List<Model> modelList= modelRepository.findAllByProductId(productId);
        return modelList.stream().map(model -> model.getId()).collect(Collectors.toList());
    }
}


