package com.example.demo.service;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.Models.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface product {
    List<ProductDTO> getAllProducts();
    Product save(Product prod);
    void delete(int id);


    ProductDTO getProductByProductname(String name);
}
