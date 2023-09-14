package com.cookerytech.mapper;

import com.cookerytech.domain.Product;
import com.cookerytech.dto.ProductDTO;
import com.cookerytech.dto.response.ProductResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-15T01:46:47+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO productToProductDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setBrandId( ProductMapper.getBrandIds( product.getBrand() ) );
        productDTO.setCategoryId( ProductMapper.getCategoryId( product.getCategory() ) );
        productDTO.setId( product.getId() );
        productDTO.setTitle( product.getTitle() );
        productDTO.setShortDesc( product.getShortDesc() );
        productDTO.setLongDesc( product.getLongDesc() );
        productDTO.setSlug( product.getSlug() );
        productDTO.setSeq( product.getSeq() );
        productDTO.setIsNew( product.getIsNew() );
        productDTO.setIsFeatured( product.getIsFeatured() );
        productDTO.setIsActive( product.getIsActive() );
        productDTO.setBuiltIn( product.getBuiltIn() );
        productDTO.setCreateAt( product.getCreateAt() );
        productDTO.setUpdateAt( product.getUpdateAt() );

        return productDTO;
    }

    @Override
    public List<ProductDTO> map(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductDTO> list = new ArrayList<ProductDTO>( products.size() );
        for ( Product product : products ) {
            list.add( productToProductDTO( product ) );
        }

        return list;
    }

    @Override
    public List<ProductResponse> productToProductResponse(List<Product> productList) {
        if ( productList == null ) {
            return null;
        }

        List<ProductResponse> list = new ArrayList<ProductResponse>( productList.size() );
        for ( Product product : productList ) {
            list.add( productToProductResponse1( product ) );
        }

        return list;
    }

    protected ProductResponse productToProductResponse1(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponse productResponse = new ProductResponse();

        productResponse.setIsFeatured( product.getIsFeatured() );
        productResponse.setIsActive( product.getIsActive() );

        return productResponse;
    }
}
