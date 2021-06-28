package com.example.demo.controller;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.Mapper.CategoryMapper;
import com.example.demo.Models.Product;
import com.example.demo.Models.ProductCategory;
import com.example.demo.Models.User;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.exception.ProductNotFound;
import com.example.demo.exception.ProductNameExisted;
import com.example.demo.exception.EmailExisted;

import org.springframework.web.bind.annotation.ModelAttribute;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;


@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;



    @GetMapping(value = "/users")
    List<UserDTO> getAll() {
        return userService.getAllUser();
    }

    public boolean isUserIDExisted(int id) {
        List<UserDTO> list = userService.getAllUser();
        for (UserDTO s: list ) {
            if (s.getUserid().equals(id)) {
                return false;
            }
        }
        return true;
    }

    @GetMapping(value = "/users/{id}")
    ResponseEntity<User> getById(@PathVariable("id") @Min(1) int id) {
        User product = userService.findUserById(id);
        if(!isUserIDExisted(id)){
            throw new ProductNotFound("user not found");
        }
        return ResponseEntity.ok().body(product);
    }



    public boolean isEmailExisted(String email) {
        List<UserDTO> list = userService.getAllUser();
        for (UserDTO i: list ) {
            if (i.getUseremail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    @PostMapping(value = "users")
    ResponseEntity<String> createUser(@Valid @RequestBody UserDTO usrDTO) {
        @Valid
        User usr = UserMapper.DtoToEntity(usrDTO);
        if (!isEmailExisted(usr.getEmail())) {
            throw new EmailExisted("This email already existed");
        }

        User add = userService.save(usr);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(add.getUserid()).toUri();
        return ResponseEntity.ok().body("User with id :  " + add.getUserid() + " .Added");
    }



    @PutMapping(value = "/users/{id}")
    ResponseEntity<String> updateUser(@PathVariable("id") @Min(1) int id,
                                          @Valid @RequestBody UserDTO usrdDTO) {
        List<User> list =  userService.findAllUser();
        AtomicBoolean c = new AtomicBoolean(false);
        list.stream().forEach(
                (s) -> {
                    if(id == s.getUserid())
                    {
                        c.set(true);
                        @Valid
                        User newProd = UserMapper.DtoToEntity(usrdDTO);

                        newProd.setUserid(s.getUserid());
                        userService.save(newProd);

                    }
                }
        );
        if(c.get() == false){
            throw  new ProductNotFound("user Not Found");
        }
        else return ResponseEntity.ok().body("newUsr");
    }

    @DeleteMapping(value = "/users/{id}")
    ResponseEntity<String> deleteUser(@PathVariable("id") @Min(1) int id) {

        List<User> list =  userService.findAllUser();
        AtomicBoolean c = new AtomicBoolean(false);

        list.stream().forEach(
                (s) -> {
                    if(id == s.getUserid())
                    {
                        User usr = s;
                        userService.delete(usr.getUserid());
                        c.set(true);
                    }
                }
        );
        if(c.get() == false){
            throw  new ProductNotFound("Product Not Found");
        }

        return ResponseEntity.ok().body("Deleted user with id : " +id);
    }






}
