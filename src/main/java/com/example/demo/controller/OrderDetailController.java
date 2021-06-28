package com.example.demo.controller;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Mapper.ProductMapper;
import com.example.demo.Mapper.UserMapper;
import com.example.demo.Models.*;
import com.example.demo.DTO.OrderDetailDTO;
import com.example.demo.Mapper.OrderDetailMapper;
import com.example.demo.service.*;
import com.example.demo.exception.ProductNotFound;
import com.example.demo.exception.ProductNameExisted;
import com.example.demo.exception.EmailExisted;
import org.springframework.web.bind.annotation.ModelAttribute;



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
public class OrderDetailController {
    @Autowired
    private OrderDetailService odService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/orderdetails")
    List<OrderDetailDTO> getAll() {
        return odService.getAllOrderDetails();
    }


    public boolean isOdIDExisted(int id) {
        //List<OrderDetailDTO> list = odService.getAllOrderDetails();
        List<OrderDetails> newlist = odService.getAll();
        for (OrderDetails s: newlist ) {
            if (s.getDetailid().equals(id)) {
                return false;
            }
        }
        return true;
    }


    @PostMapping(value = "users/{iduser}/orders/{idord}/orderdetails")
    ResponseEntity<String> createOrderDetail(@PathVariable("iduser") @Min(1) int iduser,
                                             @PathVariable("idord") @Min(1) int idord,
                                             @Valid @RequestBody OrderDetailDTO odDTO) {

        User usr = userService.findUserById(iduser);
        Order ord = orderService.findOrderById(idord);
        Product prod = productService.findProductByName(odDTO.getProductname());
        List<ProductCategory> cateList = new ArrayList<ProductCategory>();
        cateList.stream().forEach(
                (s)->{
                    if(s.getCatename().equals(prod.getCategoryyy().getCatename())){
                        prod.setCategoryyy(s);
                    }
                }
        );

        ord.setUserrr(usr);

        @Valid
        OrderDetails od = OrderDetailMapper.DtoToEntity(odDTO);
        od.setOrderrr(ord);
        od.setProducttt(prod);

        OrderDetails add = odService.save(od);
        orderService.save(ord);
        productService.save(prod);
        userService.save(usr);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(add.getDetailid()).toUri();
        return ResponseEntity.ok().body("ID detail:  " + add.getDetailid() + " .Added");
    }



    @PutMapping(value = "users/{iduser}/orders/{idord}/orderdetails/{id}")
    ResponseEntity<String> updateOrderDetail(
                                        @PathVariable("iduser") @Min(1) int iduser,
                                        @PathVariable("idord") @Min(1) int idord,
                                        @PathVariable("id") @Min(1) int id,
                                         @Valid @RequestBody OrderDetailDTO odDTO) {
        User usr = userService.findUserById(iduser);
        Order ord = orderService.findOrderById(idord);
        Product prod = productService.findProductByName(odDTO.getProductname());
        List<ProductCategory> cateList = new ArrayList<ProductCategory>();
        cateList.stream().forEach(
                (s)->{
                    if(s.getCatename().equals(prod.getCategoryyy().getCatename())){
                        prod.setCategoryyy(s);
                    }
                }
        );

        ord.setUserrr(usr);

        @Valid
        OrderDetails od = OrderDetailMapper.DtoToEntity(odDTO);

        od.setDetailid(id);
        od.setOrderrr(ord);
        od.setProducttt(prod);
        odService.save(od);
        //orderService.save(ord);
        //productService.save(prod);
        //userService.save(usr);
        return ResponseEntity.ok().body("od update");
    }

    @DeleteMapping(value = "/orderdetail/{id}")
    ResponseEntity<String> deleteProduct(@PathVariable("id") @Min(1) int id) {
        List<OrderDetails> list =  odService.getAll();
        AtomicBoolean c = new AtomicBoolean(false);

        list.stream().forEach(
                (s) -> {

                    if(s.getDetailid().equals(id))
                    {
                        odService.delete(s.getDetailid());
                        c.set(true);
                    }
                }
        );
        if(c.get() == false){
            throw  new ProductNotFound("detail Not Found");
        }

        return ResponseEntity.ok().body("Deleted prod with id : " +id);
    }



    @GetMapping("/orderdetails/get-by-name/{name}")
    public List<OrderDetailDTO> getAllOrderDetailsByDetailorderid(@PathVariable int ordid) {
        return odService.getAllOrderDetailsByDetailorderid(ordid);
    }





}
