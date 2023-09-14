package com.cookerytech.mapper;

import com.cookerytech.domain.ProductPropertyKey;
import com.cookerytech.dto.ProductPropertyKeyDTO;
import com.cookerytech.dto.request.ProductPropertyRequest;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-15T02:07:11+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
@Component
public class ProductPropertyKeyMapperImpl implements ProductPropertyKeyMapper {

    @Override
    public ProductPropertyKeyDTO productPropertyKeyToProductPropertyKeyDTO(ProductPropertyKey productPropertyKey) {
        if ( productPropertyKey == null ) {
            return null;
        }

        ProductPropertyKeyDTO productPropertyKeyDTO = new ProductPropertyKeyDTO();

        productPropertyKeyDTO.setProductId( ProductPropertyKeyMapper.getProductId( productPropertyKey.getProductId() ) );
        productPropertyKeyDTO.setId( productPropertyKey.getId() );
        productPropertyKeyDTO.setName( productPropertyKey.getName() );
        productPropertyKeyDTO.setSeq( productPropertyKey.getSeq() );
        productPropertyKeyDTO.setBuiltIn( productPropertyKey.getBuiltIn() );

        return productPropertyKeyDTO;
    }

    @Override
    public ProductPropertyKey productPropertyKeyRequestToProductPropertyKey(ProductPropertyRequest productPropertyRequest) {
        if ( productPropertyRequest == null ) {
            return null;
        }

        ProductPropertyKey productPropertyKey = new ProductPropertyKey();

        productPropertyKey.setName( productPropertyRequest.getName() );
        productPropertyKey.setSeq( productPropertyRequest.getSeq() );

        return productPropertyKey;
    }

    @Override
    public List<ProductPropertyKeyDTO> map(List<ProductPropertyKey> productPropertyKeys) {
        if ( productPropertyKeys == null ) {
            return null;
        }

        List<ProductPropertyKeyDTO> list = new ArrayList<ProductPropertyKeyDTO>( productPropertyKeys.size() );
        for ( ProductPropertyKey productPropertyKey : productPropertyKeys ) {
            list.add( productPropertyKeyToProductPropertyKeyDTO( productPropertyKey ) );
        }

        return list;
    }
}
