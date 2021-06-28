package com.example.demo.Mapper;

import com.example.demo.DTO.OrderDetailDTO;
import com.example.demo.Models.Order;
import com.example.demo.Models.OrderDetails;


public class OrderDetailMapper {
    public static OrderDetails DtoToEntity(OrderDetailDTO ordDDTO) {
        OrderDetails ordD = new OrderDetails();
        //ordD.setDetailid(ordDDTO.getDetailid());
        ordD.setDetailname(ordDDTO.getDetailname());
        ordD.setDetailprice(ordDDTO.getDetailprice());
        ordD.setDetailquantity(ordDDTO.getDetailquantity());
        return ordD;
    }

    public static OrderDetailDTO EntityToDto(OrderDetails ordD) {
        OrderDetailDTO ordDDTO = new OrderDetailDTO();
        //ordDDTO.setDetailid(ordD.getDetailid());
        ordDDTO.setDetailname(ordD.getDetailname());
        ordDDTO.setDetailprice(ordD.getDetailprice());
        ordDDTO.setDetailquantity(ordD.getDetailquantity());
        ordDDTO.setProductname(ordD.getProducttt().getProductname());
        return ordDDTO;
    }
}

