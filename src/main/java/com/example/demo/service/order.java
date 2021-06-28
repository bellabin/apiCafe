package com.example.demo.service;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.Models.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface order {
    List<OrderDTO> getAllOrders();
    Order findOrderById(int id);
    Order save(Order ord);
    void delete(int id);

    List<OrderDTO> getOrderByOrdship(Integer name);

}
