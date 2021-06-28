package com.example.demo.Mapper;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.Models.ProductCategory;
import com.example.demo.Models.Product;
import com.example.demo.DTO.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


//@Component
public class CategoryMapper {



    public static ProductCategory DtoToEntity(CategoryDTO pcate) {
        ProductCategory pcatedto = new ProductCategory();
        pcatedto.setCateid(pcate.getCateid());
        pcatedto.setCatename(pcate.getCategoryname());
        List<Product> list = new ArrayList<Product>();
        pcate.getProducts().stream().forEach(s -> {
            Product dto1 = ProductMapper.DtoToEntity(s);
            list.add(dto1);
        });
        pcatedto.setProducts(list);
        return pcatedto;
    }

    public static CategoryDTO EntityToDto(ProductCategory pcate) {
        CategoryDTO pcatedto = new CategoryDTO();
        pcatedto.setCateid(pcate.getCateid());
        pcatedto.setCategoryname(pcate.getCatename());
        List<ProductDTO> listDTO = new ArrayList<ProductDTO>();
        pcate.getProducts().stream().forEach(s -> {
            ProductDTO dto1 = ProductMapper.EntityToDto(s);
            listDTO.add(dto1);
        });
        pcatedto.setProducts(listDTO);
        return pcatedto;
    }
}
