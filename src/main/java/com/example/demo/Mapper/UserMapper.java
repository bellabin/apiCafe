package com.example.demo.Mapper;

import com.example.demo.DTO.OrderDTO;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Models.Order;

import com.example.demo.Models.User;
import com.example.demo.Models.Order;
import com.example.demo.Mapper.OrderMapper;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static User DtoToEntity(UserDTO usrDTO) {
        User usr = new User();
        usr.setUserid(usrDTO.getUserid());
        usr.setUsername(usrDTO.getUsername());
        usr.setEmail(usrDTO.getUseremail());
        usr.setUserphone(usrDTO.getUserphone());
        usr.setUseraddress(usrDTO.getUseraddress());
        usr.setUserpass(usrDTO.getUserpass());
        List<Order> list = new ArrayList<Order>();
        usrDTO.getOrderDTO().stream().forEach(s -> {
            Order dto1 = OrderMapper.DtoToEntity(s);
            list.add(dto1);
        });
        usr.setOrders(list);

        return usr;
    }

    public static UserDTO EntityToDto(User user) {
        UserDTO usrDTO = new UserDTO();
        usrDTO.setUserid(user.getUserid());
        usrDTO.setUsername(user.getUsername());
        usrDTO.setUseremail(user.getEmail());
        usrDTO.setUserphone(user.getUserphone());
        usrDTO.setUseraddress(user.getUseraddress());
        usrDTO.setUserpass(user.getUserpass());
        List<OrderDTO> list = new ArrayList<OrderDTO>();
        user.getOrders().stream().forEach(s -> {
            OrderDTO dto1 = OrderMapper.EntityToDto(s);
            list.add(dto1);
        });
        usrDTO.setOrderDTO(list);

        return usrDTO;


    }
}
