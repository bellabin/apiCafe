package com.example.demo.service;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.Models.Product;
import com.example.demo.Models.ProductCategory;
import com.example.demo.exception.ProductNameExisted;
import com.example.demo.repository.ProductCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.Mapper.CategoryMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("cate service")
@AllArgsConstructor
public class CategoryService implements category{

    @Autowired
    private ProductCategoryRepository cateRepository;


    @Override
    public List<CategoryDTO> getAllCategory() {
        List<ProductCategory> list = cateRepository.findAll();
        List<CategoryDTO> listDTO = new ArrayList<CategoryDTO>();
        list.stream().forEach(s -> {
            CategoryDTO dto = CategoryMapper.EntityToDto(s);
            listDTO.add(dto);
        });
        return listDTO;
    }

    @Override
    public ProductCategory findProductcategoryById(int id) {
        return cateRepository.findById(id);
    }

    @Override
    public ProductCategory save(ProductCategory prodcate) {
        return cateRepository.save(prodcate);
    }

    @Override
    public void delete(int id) {
        cateRepository.deleteById(id);
    }

    @Override
    public CategoryDTO getProductCategoryByCatename(String name) {
        ProductCategory category = cateRepository.findProductCategoryByCatename(name);
        CategoryDTO dto = CategoryMapper.EntityToDto(category);
        return dto;
    }



    public ProductCategory createProductCategory(ProductCategory prod) {
        ProductCategory product = cateRepository.findProductCategoryByCatename(prod.getCatename());
        List<ProductCategory> list =  cateRepository.findAll();

        list.stream().forEach(
                (s) -> {
                    if (product.getCatename().equals(s.getCatename())) {
                        throw new ProductNameExisted("Name Has Already Existed");
                    }
                }
        );
        return cateRepository.save(prod);
    }


    public List<ProductCategory> findAllCategories() {
        return cateRepository.findAll();
    }

    public ProductCategory findProductCategoryByCatename (String name){
        return cateRepository.findProductCategoryByCatename(name);
    }



}
