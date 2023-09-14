package com.cookerytech.controller;

import com.cookerytech.dto.ModelDTO;
import com.cookerytech.dto.request.ModelCreatRequest;
import com.cookerytech.dto.request.ModelUpdateRequest;
import com.cookerytech.dto.response.ModelResponse;
import com.cookerytech.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products/models")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;

    @PutMapping("/{id}")
    public ResponseEntity<ModelUpdateRequest> updateModelById(@PathVariable("id") Long id, @RequestBody ModelUpdateRequest modelUpdateRequest){
        modelService.updateModelById(id, modelUpdateRequest);
        return ResponseEntity.ok(modelUpdateRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ModelResponse> deleteModelById(@PathVariable("id") Long id){
        ModelResponse modelResponse = modelService.deleteModelById(id);
        return ResponseEntity.ok(modelResponse);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('PRODUCT_MANAGER')")
    public ResponseEntity<ModelDTO> creatModel(@Valid @ModelAttribute ModelCreatRequest modelCreatRequest){
        ModelDTO createdModel= modelService.creatModel(modelCreatRequest);
        return ResponseEntity.ok(createdModel);
    }




}
