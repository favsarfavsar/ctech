package com.cookerytech.mapper;

import com.cookerytech.domain.Model;
import com.cookerytech.dto.ModelDTO;
import com.cookerytech.dto.response.ModelResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-15T03:41:10+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
@Component
public class ModelMapperImpl implements ModelMapper {

    @Override
    public ModelResponse modelToModelResponse(Model model) {
        if ( model == null ) {
            return null;
        }

        ModelResponse modelResponse = new ModelResponse();

        return modelResponse;
    }

    @Override
    public ModelDTO modelToModelDTO(Model model) {
        if ( model == null ) {
            return null;
        }

        ModelDTO modelDTO = new ModelDTO();

        modelDTO.setImageIds( ModelMapper.getImageId( model.getImages() ) );
        modelDTO.setCurrencyId( ModelMapper.getCurrencyId( model.getCurrency() ) );
        modelDTO.setProductId( ModelMapper.getProductId( model.getProduct() ) );
        modelDTO.setId( model.getId() );
        modelDTO.setTitle( model.getTitle() );
        modelDTO.setSku( model.getSku() );
        modelDTO.setStockAmount( model.getStockAmount() );
        modelDTO.setInBoxQuantity( model.getInBoxQuantity() );
        modelDTO.setSeq( model.getSeq() );
        modelDTO.setBuyingPrice( model.getBuyingPrice() );
        modelDTO.setTaxRate( model.getTaxRate() );
        modelDTO.setIsActive( model.getIsActive() );
        modelDTO.setBuiltIn( model.getBuiltIn() );
        modelDTO.setCreateAt( model.getCreateAt() );
        modelDTO.setUpdateAt( model.getUpdateAt() );

        return modelDTO;
    }

    @Override
    public List<ModelDTO> map(List<Model> modelList) {
        if ( modelList == null ) {
            return null;
        }

        List<ModelDTO> list = new ArrayList<ModelDTO>( modelList.size() );
        for ( Model model : modelList ) {
            list.add( modelToModelDTO( model ) );
        }

        return list;
    }
}
