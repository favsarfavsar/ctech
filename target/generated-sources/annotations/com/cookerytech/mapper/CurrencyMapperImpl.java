package com.cookerytech.mapper;

import com.cookerytech.domain.Currency;
import com.cookerytech.dto.CurrencyDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-15T01:46:47+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
@Component
public class CurrencyMapperImpl implements CurrencyMapper {

    @Override
    public CurrencyDTO currencyToCurrencyDTO(Currency currency) {
        if ( currency == null ) {
            return null;
        }

        CurrencyDTO currencyDTO = new CurrencyDTO();

        currencyDTO.setId( currency.getId() );
        currencyDTO.setCode( currency.getCode() );
        currencyDTO.setValue( currency.getValue() );
        currencyDTO.setSymbol( currency.getSymbol() );

        return currencyDTO;
    }
}
