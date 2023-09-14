package com.cookerytech.service;

import com.cookerytech.domain.ProductPropertyKey;
import com.cookerytech.repository.ModelPropertyValueRepository;
import org.springframework.stereotype.Service;

@Service
public class ModelPropertyValueService {

    private final ModelPropertyValueRepository modelPropertyValueRepository;


    public ModelPropertyValueService(ModelPropertyValueRepository modelPropertyValueRepository) {
        this.modelPropertyValueRepository = modelPropertyValueRepository;
    }
    public boolean existByProductPropertyKey(ProductPropertyKey productPropertyKey) {  //A10
        return  modelPropertyValueRepository.existsByProductPropertyKey(productPropertyKey);
    }

}
