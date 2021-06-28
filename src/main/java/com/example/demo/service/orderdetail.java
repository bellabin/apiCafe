package com.example.demo.service;

import com.example.demo.DTO.OrderDetailDTO;
import com.example.demo.Models.OrderDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface orderdetail {
    List<OrderDetailDTO> getAllOrderDetails();
    OrderDetails findOrderDetailsById(int id);
    OrderDetails save(OrderDetails ord);
    void delete(int id);

    List<OrderDetailDTO> getAllOrderDetailsByDetailorderid(int detailorderid);

}
