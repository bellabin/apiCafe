package com.example.demo.repository;

import com.example.demo.DTO.OrderDetailDTO;
import com.example.demo.Models.OrderDetails;
import com.example.demo.Models.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetails, Integer> {


    List<OrderDetails> findAll();
    //OrderDetails findById();
    OrderDetails findOrderDetailsByDetailid(int id);

    //Optional<OrderDetails> findOrderDetailsById(int id);
    //OrderDetails findOrderDetailsByDetailorderid(int id);

}
