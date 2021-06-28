package com.example.demo.Mapper;

import com.example.demo.DTO.OrderDetailDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.Models.OrderDetails;
import com.example.demo.Models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {
    public static Product DtoToEntity(ProductDTO prodDTO) {
        Product prod = new Product();
        //prod.setProductid(prodDTO.getId());
        prod.setProductname(prodDTO.getProductname());
        //prod.setProdcate(prodDTO.getProdcate());
        prod.setProductprice(prodDTO.getProductprice());
        prod.setProductthumb(prodDTO.getProductthumb());
        prod.setProductimg(prodDTO.getProductimg());
        prod.setProductstock(prodDTO.getProductstock());
        List<OrderDetails> list = new ArrayList<OrderDetails>();
        prodDTO.getDetailDTO().stream().forEach(s -> {
            OrderDetails dto1 = OrderDetailMapper.DtoToEntity(s);
            list.add(dto1);
        });
        prod.setDetails(list);
        return prod;
    }

    public static ProductDTO EntityToDto(Product prod) {
        ProductDTO prodDTO =  new ProductDTO();
        //prodDTO.setId(prod.getProductid());
        prodDTO.setProductname(prod.getProductname());
        //prodDTO.setProdcate((prod.getProdcate()));
        prodDTO.setProductprice(prod.getProductprice());
        prodDTO.setProductthumb(prod.getProductthumb());
        prodDTO.setProductimg(prod.getProductimg());
        prodDTO.setProductstock(prod.getProductstock());
        List<OrderDetailDTO> list = new ArrayList<OrderDetailDTO>();
        prod.getDetails().stream().forEach(s -> {
            OrderDetailDTO dto1 = OrderDetailMapper.EntityToDto(s);
            list.add(dto1);
        });
        prodDTO.setDetailDTO(list);
        return prodDTO;
    }
}
