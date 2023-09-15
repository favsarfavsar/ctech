package com.cookerytech.mapper;

import com.cookerytech.domain.Brand;
import com.cookerytech.dto.BrandDTO;
import com.cookerytech.dto.request.BrandSaveRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-15T03:41:10+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
@Component
public class BrandMapperImpl implements BrandMapper {

    @Override
    public BrandDTO brandToBrandDTO(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        BrandDTO brandDTO = new BrandDTO();

        brandDTO.setId( brand.getId() );
        brandDTO.setName( brand.getName() );
        brandDTO.setProfitRate( brand.getProfitRate() );
        brandDTO.setIsActive( brand.getIsActive() );
        brandDTO.setBuiltIn( brand.getBuiltIn() );
        brandDTO.setCreateAt( brand.getCreateAt() );
        brandDTO.setUpdateAt( brand.getUpdateAt() );

        return brandDTO;
    }

    @Override
    public Brand brandSaveRequestToBrand(BrandSaveRequest brandSaveRequest) {
        if ( brandSaveRequest == null ) {
            return null;
        }

        Brand brand = new Brand();

        brand.setName( brandSaveRequest.getName() );
        brand.setProfitRate( brandSaveRequest.getProfitRate() );
        brand.setIsActive( brandSaveRequest.getIsActive() );

        return brand;
    }
}
