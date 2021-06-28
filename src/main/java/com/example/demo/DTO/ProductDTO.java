package com.example.demo.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ProductDTO {

//    @NotBlank(message = "id is required")
//    @NotNull
    //private Integer id;

//    @NotBlank(message = "Name is required")
//    @NotNull
    private String productname;


    //private Integer prodcate;

//    @NotBlank(message = "Price is required")
//    @NotNull
    private String productprice;

//    @NotBlank(message = "thumb is required")
//    @NotNull
    private String productthumb;

    private String productimg;

//    @NotBlank(message = "img is required")
//    @NotNull
//    private String productimage;

//    @NotBlank(message = "Stock is required")
//    @NotNull
    private int productstock;

    private List<OrderDetailDTO> detailDTO;

}
