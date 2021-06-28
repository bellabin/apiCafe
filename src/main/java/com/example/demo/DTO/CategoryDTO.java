package com.example.demo.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class CategoryDTO {

    private Integer cateid;

    @NotBlank(message = "Name is required")
    private String categoryname;

    private List<ProductDTO> products;


}
