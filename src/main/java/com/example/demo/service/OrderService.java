package com.example.demo.service;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.Mapper.OrderMapper;
import com.example.demo.Mapper.ProductMapper;
import com.example.demo.Models.Order;
import com.example.demo.exception.ProductNameExisted;
import com.example.demo.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service ("order service")
@AllArgsConstructor
public class OrderService implements order{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> list = orderRepository.findAll();
        List<OrderDTO> listDTO = new ArrayList<OrderDTO>();
        list.stream().forEach(s -> {
            OrderDTO dto = OrderMapper.EntityToDto(s);
            listDTO.add(dto);
        });
        return listDTO;
    }



    @Override
    public Order save(Order ord) {
        return orderRepository.save(ord);
    }

    @Override
    public void delete(int id) { orderRepository.deleteById(id);
    }



    @Override
    public List<OrderDTO> getOrderByOrdship(Integer ordship) {

        List<Order> ord = orderRepository.findAll();
        List<OrderDTO> dto = new ArrayList<OrderDTO>();
        ord.stream().forEach(
                (s) -> {
                    OrderDTO odto = OrderMapper.EntityToDto(s);
                    dto.add(odto);
                }
        );
        return dto;
    }

    @Override
    public Order findOrderById(int id){
        return orderRepository.findOrderByOrderid(id);
    }


    public Order createOrder(Order ord) {
        Order order = orderRepository.findOrderByOrderid(ord.getOrderid());
        //List<Order> list =  orderRepository.findAll();
        return orderRepository.save(order);
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }


}
