package com.example.demo.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class OrderDetailDTO {


//    @NotBlank(message = "detailid is required")
//    @NotNull
//    private Integer detailid;

//    @NotBlank(message = "detailorderid is required")
//    @NotNull
//    private Integer detailorderid;


//    @NotBlank(message = "detailname is required")
//    @NotNull
    private String detailname;

//    @NotBlank(message = "detailprice num is required")
//    @NotNull
    private float detailprice;

//    @NotBlank(message = "quantity is required")
//    @NotNull
    private Integer detailquantity;

    private String productname;




}
