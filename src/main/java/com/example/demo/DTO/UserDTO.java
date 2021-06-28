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
public class UserDTO {

    //@NotBlank(message = "id is required")
    //@NotNull
    private Integer userid;

    //@NotBlank(message = "Name is required")
    //@NotNull
    private String username;


    //@NotBlank(message = "Price is required")
    //@NotNull
    private String useremail;

    //@NotBlank(message = "Stock is required")
    //@NotNull
    private String userphone;

    //@NotBlank(message = "address is required")
    //@NotNull
    private String useraddress;

    //@NotBlank(message = "pass is required")
    //@NotNull
    private String userpass;


    private List<OrderDTO> orderDTO;


}
