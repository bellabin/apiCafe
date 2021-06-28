package com.example.demo.service;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.Models.Product;
import com.example.demo.Models.ProductCategory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface category {
    List<CategoryDTO> getAllCategory();
    ProductCategory findProductcategoryById(int id);
    ProductCategory save(ProductCategory prod);
    void delete(int id);

    CategoryDTO getProductCategoryByCatename(String name);
    ProductCategory findProductCategoryByCatename (String name);
}
