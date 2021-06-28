package com.example.demo.Mapper;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.Models.Order;
import com.example.demo.Models.OrderDetails;
import com.example.demo.DTO.OrderDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


//@Component
public class OrderMapper {



    public static Order DtoToEntity(OrderDTO ordDTO) {
        Order ord = new Order();
        //ord.setOrderid(ordDTO.getOrderid());
        ord.setOrdamount(ordDTO.getOrdamount());
        ord.setOrdaddress(ordDTO.getOrdaddress());
        ord.setOrdphone(ordDTO.getOrdphone());
        ord.setOrdemail(ordDTO.getOrdemail());
        ord.setOrdship(ord.getOrdship());
        List<OrderDetails> list = new ArrayList<OrderDetails>();
        ordDTO.getOrderDetailDTOs().stream().forEach(s -> {
            OrderDetails dto1 = OrderDetailMapper.DtoToEntity(s);
            list.add(dto1);
        });
        ord.setOrderDetails(list);
        return ord;
    }

    public static OrderDTO EntityToDto(Order ord) {
        OrderDTO ordDTO = new OrderDTO();
        //ordDTO.setOrderid(ord.getOrderid());
        ordDTO.setOrdamount(ord.getOrdamount());
        ordDTO.setOrdaddress(ord.getOrdaddress());
        ordDTO.setOrdphone(ord.getOrdphone());
        ordDTO.setOrdemail(ord.getOrdemail());
        ordDTO.setOrdship(ord.getOrdship());
        List<OrderDetailDTO> listDTO = new ArrayList<OrderDetailDTO>();
        ord.getOrderDetails().stream().forEach(s -> {
            OrderDetailDTO dto1 = OrderDetailMapper.EntityToDto(s);
            listDTO.add(dto1);
        });
        ordDTO.setOrderDetailDTOs(listDTO);
        return ordDTO;
    }
}
