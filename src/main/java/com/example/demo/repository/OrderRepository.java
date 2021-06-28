package com.example.demo.repository;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.Models.Order;
import com.example.demo.Models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {


    Order findOrderByOrderid(int id);
    Order findById(int id);
}
