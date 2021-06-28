package com.example.demo.service;

import com.example.demo.DTO.ProductDTO;
import  com.example.demo.Models.Product;
import com.example.demo.Mapper.ProductMapper;
import com.example.demo.exception.ProductNameExisted;
import com.example.demo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service ("product service")
@AllArgsConstructor
public class ProductService implements product{

    @Autowired
    private ProductRepository ProductRepository;



    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> list =  ProductRepository.findAll();
        List<ProductDTO> listDTO = new ArrayList<>();
        list.stream().forEach(
                (s) -> {
                    ProductDTO dto = ProductMapper.EntityToDto(s);
                    listDTO.add(dto);
                }
        );
        return listDTO;
    }



    @Override
    public Product save(Product prod) {
        return ProductRepository.save(prod);
    }

    @Override
    public void delete(int id) {
        ProductRepository.deleteById(id);
    }


    @Override
    public ProductDTO getProductByProductname(String name) {
        Product product = ProductRepository.findProductByProductname(name);
        ProductDTO dto = ProductMapper.EntityToDto(product);
        return dto;
    }



    public Product createProduct(Product prod) {
        Product product = ProductRepository.findProductByProductname(prod.getProductname());
        List<Product> list =  ProductRepository.findAll();

        list.stream().forEach(
                (s) -> {
                    if (product.getProductname().equals(s.getProductname())) {
                        throw new ProductNameExisted("Name Has Already Existed");
                    }
                }
        );


        return ProductRepository.save(prod);
    }

    public Product updateProduct(Product prod) {
        return ProductRepository.save(prod);
    }

    public List<Product> findAllProducts() {
        return ProductRepository.findAll();
    }

    public void deleteById(int id) {
        ProductRepository.deleteById(id);
    }

    public Product findProductId(int id){
        return ProductRepository.findProductByProductid(id);
    }
    public Product findProductByName(String name)
    {
        return ProductRepository.findProductByProductname(name);
    }


}
