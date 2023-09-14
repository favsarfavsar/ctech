package com.cookerytech.mapper;

import com.cookerytech.domain.Currency;
import com.cookerytech.domain.Offer;
import com.cookerytech.domain.User;
import com.cookerytech.dto.OfferDTO;
import com.cookerytech.dto.response.OfferResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-15T02:07:10+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
@Component
public class OfferMapperImpl implements OfferMapper {

    @Override
    public OfferDTO offerToOfferDTO(Offer offer) {
        if ( offer == null ) {
            return null;
        }

        OfferDTO offerDTO = new OfferDTO();

        offerDTO.setUserId( offerUserId( offer ) );
        offerDTO.setCurrencyId( offerCurrencyId( offer ) );
        offerDTO.setId( offer.getId() );
        offerDTO.setCode( offer.getCode() );
        offerDTO.setStatus( offer.getStatus() );
        offerDTO.setSubTotal( offer.getSubTotal() );
        offerDTO.setDiscount( offer.getDiscount() );
        offerDTO.setGrandTotal( offer.getGrandTotal() );
        offerDTO.setDeliveryAt( offer.getDeliveryAt() );
        offerDTO.setCreateAt( offer.getCreateAt() );
        offerDTO.setUpdateAt( offer.getUpdateAt() );

        return offerDTO;
    }

    @Override
    public List<OfferDTO> offerListToOfferDTOList(List<Offer> offers) {
        if ( offers == null ) {
            return null;
        }

        List<OfferDTO> list = new ArrayList<OfferDTO>( offers.size() );
        for ( Offer offer : offers ) {
            list.add( offerToOfferDTO( offer ) );
        }

        return list;
    }

    @Override
    public OfferResponse offerToOfferResponse(Offer offer) {
        if ( offer == null ) {
            return null;
        }

        OfferResponse offerResponse = new OfferResponse();

        return offerResponse;
    }

    @Override
    public List<OfferResponse> offersToOfferResponses(List<Offer> offerList) {
        if ( offerList == null ) {
            return null;
        }

        List<OfferResponse> list = new ArrayList<OfferResponse>( offerList.size() );
        for ( Offer offer : offerList ) {
            list.add( offerToOfferResponse( offer ) );
        }

        return list;
    }

    @Override
    public Offer offerResponseToOffer(OfferResponse offerResponse) {
        if ( offerResponse == null ) {
            return null;
        }

        Offer offer = new Offer();

        return offer;
    }

    private Long offerUserId(Offer offer) {
        if ( offer == null ) {
            return null;
        }
        User user = offer.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long offerCurrencyId(Offer offer) {
        if ( offer == null ) {
            return null;
        }
        Currency currency = offer.getCurrency();
        if ( currency == null ) {
            return null;
        }
        Long id = currency.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
