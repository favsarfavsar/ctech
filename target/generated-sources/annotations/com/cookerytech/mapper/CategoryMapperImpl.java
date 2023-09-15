package com.cookerytech.mapper;

import com.cookerytech.domain.Category;
import com.cookerytech.dto.CategoryDTO;
import com.cookerytech.dto.request.CategoryRequest;
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
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDTO categoryToCategoryDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( category.getId() );
        categoryDTO.setTitle( category.getTitle() );
        categoryDTO.setDescription( category.getDescription() );
        categoryDTO.setBuiltIn( category.getBuiltIn() );
        categoryDTO.setSeq( category.getSeq() );
        categoryDTO.setSlug( category.getSlug() );
        categoryDTO.setIsActive( category.getIsActive() );
        categoryDTO.setMainCategoryId( category.getMainCategoryId() );
        categoryDTO.setCreateAt( category.getCreateAt() );

        return categoryDTO;
    }

    @Override
    public Category categorySaveRequestToCategory(CategoryRequest categoryRequest) {
        if ( categoryRequest == null ) {
            return null;
        }

        Category category = new Category();

        category.setTitle( categoryRequest.getTitle() );
        category.setIsActive( categoryRequest.getIsActive() );

        return category;
    }

    @Override
    public List<CategoryDTO> categoryListToCategoryDTOList(List<Category> categoryList) {
        if ( categoryList == null ) {
            return null;
        }

        List<CategoryDTO> list = new ArrayList<CategoryDTO>( categoryList.size() );
        for ( Category category : categoryList ) {
            list.add( categoryToCategoryDTO( category ) );
        }

        return list;
    }
}
