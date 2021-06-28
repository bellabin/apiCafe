package com.example.demo.controller;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.Mapper.CategoryMapper;
import com.example.demo.Mapper.ProductMapper;
import com.example.demo.Models.Order;
import com.example.demo.DTO.OrderDTO;
import com.example.demo.Mapper.OrderMapper;
import com.example.demo.Models.Product;
import com.example.demo.Models.ProductCategory;
import com.example.demo.Models.User;
import com.example.demo.service.OrderService;
import com.example.demo.exception.ProductNotFound;
import com.example.demo.exception.ProductNameExisted;
import com.example.demo.exception.EmailExisted;


import com.example.demo.service.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;


@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService usrService;

    @GetMapping(value = "/orders")
    List<OrderDTO> getAll() {
        return orderService.getAllOrders();
    }


    public boolean isOrdIDExisted(int id) {
        List<Order> list = orderService.getAll();
        for (Order s: list ) {
            if (s.getOrderid().equals(id)) {
                return false;
            }
        }
        return true;
    }

    @PostMapping(value = "users/{id}/orders")
    ResponseEntity<String> createOrder(@PathVariable("id") @Min(1) int id,@Valid @RequestBody OrderDTO ordDTO) {
        User user = usrService.findUserById(id);


        @Valid
        Order ord = OrderMapper.DtoToEntity(ordDTO);
        ord.setOrdship(0);
        ord.setUserrr(user);


        List <Order> ulist = new ArrayList<Order>();
        user.getOrders().stream().forEach(
                (s)->{
                    ulist.add(s);
                }
        );
        Order ordADD = orderService.save(ord);
        ulist.add(ordADD);
        user.setOrders(ulist);

        User add = usrService.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(add.getUserid()).toUri();
        return ResponseEntity.ok().body("ID order:  " + ord.getOrderid() + " .Added");
    }



    @PutMapping(value = "users/{id}/orders/{idord}")
    ResponseEntity<String> updateOrder(@PathVariable("id") @Min(1) int id,
                                      @PathVariable("idord") @Min(1) int idord,
                                          @Valid @RequestBody OrderDTO orderDTO) {

        List<Order> list =  orderService.getAll();
        AtomicBoolean c = new AtomicBoolean(false);
        list.stream().forEach(
                (s) -> {
                    if(idord == s.getOrderid())
                    {
                        c.set(true);
                        @Valid
                        Order newOrd = OrderMapper.DtoToEntity(orderDTO);

                        newOrd.setUserrr(s.getUserrr());
                        newOrd.setOrderid(s.getOrderid());
                        newOrd.setOrdship(orderDTO.getOrdship());
                        orderService.save(newOrd);

                    }
                }
        );
        if(c.get() == false){
            throw  new ProductNotFound("Ord Not Found");
        }
        else return ResponseEntity.ok().body("newOrd");
    }


    @DeleteMapping(value = "/orders/{id}")
    ResponseEntity<String> deleteOrder(@PathVariable("id") @Min(1) int id) {
        List<Order> list =  orderService.getAll();
        AtomicBoolean c = new AtomicBoolean(false);

        list.stream().forEach(
                (s) -> {
                    if(s.getOrderid().equals(id))
                    {
                        orderService.delete(id);
                        c.set(true);
                    }
                }
        );
        if(c.get() == false){
            throw  new ProductNotFound("Product Not Found");
        }

        return ResponseEntity.ok().body("Deleted prodcate with id : " +id);
    }



    @GetMapping("/orders/get-by-name/{name}")
    public List<OrderDTO> getOrderByOrdship(@PathVariable int ordship) {
        return orderService.getOrderByOrdship(ordship);
    }



}
