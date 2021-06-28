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
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class OrderDTO {

//    @NotBlank(message = "orderID is required")
//    @NotNull
//    private Integer orderid;

//    @NotBlank(message = "orderuserID is required")
//    @NotNull
    //private Integer orduserid;

//    @NotBlank(message = "Price is required")
//    @NotNull
    private Float ordamount;

//    @NotBlank(message = "Address is required")
//    @NotNull
    private String ordaddress;

//    @NotBlank(message = "Phone num is required")
//    @NotNull
    private String ordphone;

//    @NotBlank(message = "Email is required")
//    @NotNull
    private String ordemail;

//    @NotBlank(message = "Status num is required")
//    @NotNull
    private Integer ordship;

    private List<OrderDetailDTO> orderDetailDTOs;



}
