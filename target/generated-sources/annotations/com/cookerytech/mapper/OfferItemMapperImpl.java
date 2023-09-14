package com.cookerytech.mapper;

import com.cookerytech.domain.OfferItem;
import com.cookerytech.dto.OfferItemDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-08T04:21:16+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
@Component
public class OfferItemMapperImpl implements OfferItemMapper {

    @Override
    public OfferItemDTO OfferItemToOfferItemDTO(OfferItem offerItem) {
        if ( offerItem == null ) {
            return null;
        }

        OfferItemDTO offerItemDTO = new OfferItemDTO();

        offerItemDTO.setOfferId( OfferItemMapper.getofferId( offerItem.getOffer() ) );
        offerItemDTO.setModelId( OfferItemMapper.getmodelId( offerItem.getModel() ) );
        offerItemDTO.setProductId( OfferItemMapper.getProductrId( offerItem.getProduct() ) );
        offerItemDTO.setId( offerItem.getId() );
        offerItemDTO.setSku( offerItem.getSku() );
        offerItemDTO.setQuantity( offerItem.getQuantity() );
        offerItemDTO.setSellingPrice( offerItem.getSellingPrice() );
        offerItemDTO.setTax( offerItem.getTax() );
        offerItemDTO.setSubTotal( offerItem.getSubTotal() );
        offerItemDTO.setDiscount( offerItem.getDiscount() );
        offerItemDTO.setCreateAt( offerItem.getCreateAt() );
        offerItemDTO.setUpdateAt( offerItem.getUpdateAt() );

        return offerItemDTO;
    }

    @Override
    public List<OfferItemDTO> map(List<OfferItem> offerItems) {
        if ( offerItems == null ) {
            return null;
        }

        List<OfferItemDTO> list = new ArrayList<OfferItemDTO>( offerItems.size() );
        for ( OfferItem offerItem : offerItems ) {
            list.add( OfferItemToOfferItemDTO( offerItem ) );
        }

        return list;
    }
}
