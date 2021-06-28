package com.example.demo.service;

import com.example.demo.DTO.OrderDetailDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.Mapper.OrderDetailMapper;
import com.example.demo.Mapper.ProductMapper;
import com.example.demo.Models.OrderDetails;
import com.example.demo.Models.Product;
import com.example.demo.exception.ProductNameExisted;
import com.example.demo.repository.OrderDetailRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service ("detail service")
@AllArgsConstructor
public class OrderDetailService implements orderdetail{

    @Autowired
    private OrderDetailRepository odRepository;

    @Override
    public List<OrderDetailDTO> getAllOrderDetails() {
        List<OrderDetails> list =  odRepository.findAll();
        List<OrderDetailDTO> listDTO = new ArrayList<OrderDetailDTO>();
        list.stream().forEach(
                (s) -> {
                    OrderDetailDTO dto = OrderDetailMapper.EntityToDto(s);
                    listDTO.add(dto);
                }
        );
        return listDTO;
    }


    @Override
    public OrderDetails findOrderDetailsById (int id){

        return odRepository.findOrderDetailsByDetailid(id);
    }


    @Override
    public OrderDetails save(OrderDetails prod) {
        return odRepository.save(prod);
    }

    @Override
    public void delete(int id) {
        odRepository.deleteById(id);
    }


    @Override
    public List<OrderDetailDTO> getAllOrderDetailsByDetailorderid(int detailorderid) {
        List<OrderDetailDTO> dto = new ArrayList<OrderDetailDTO>();
        List<OrderDetails> list = odRepository.findAll();
        list.stream().forEach(
                (s) -> {
                    OrderDetailDTO oddto = OrderDetailMapper.EntityToDto(s);
                    if(s.getOrderrr().getOrderid().equals(detailorderid)){
                        dto.add(oddto);
                    }
                }
        );
        return dto;
    }

    public List<OrderDetails> getAll(){
        return odRepository.findAll();
    }



}
