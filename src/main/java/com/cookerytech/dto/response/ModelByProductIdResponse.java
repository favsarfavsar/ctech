package com.cookerytech.dto.response;

import com.cookerytech.domain.Model;
import com.cookerytech.domain.ModelPropertyValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModelByProductIdResponse {

    private Model model;

    private ModelPropertyValue modelPropertyValue;

    private Boolean isFavorite=false;
}
