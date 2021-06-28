package com.example.demo.service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Models.Order;
import com.example.demo.Models.User;
import com.example.demo.exception.ProductNameExisted;
import com.example.demo.repository.UserRepository;
import com.example.demo.Mapper.UserMapper;
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

@Service
@AllArgsConstructor
public class UserService implements user{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUser() {

        List<User> list= userRepository.findAll();
        List<UserDTO> dto = new ArrayList<UserDTO>();
        list.stream().forEach(
                (s) -> {
                    UserDTO dto1 = UserMapper.EntityToDto(s);
                    dto.add(dto1);
                }
        );


        return dto;
    }

    @Override
    public User findUserById(int id){
        return userRepository.findUserByUserid(id);
    }

    @Override
    public User save(User usr) {
        return userRepository.save(usr);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }



    public User createUser (User usr) {
        Optional<User> usrOptional = userRepository.findUserByEmail(usr.getEmail());
        if (usrOptional.isPresent()) {
            throw new ProductNameExisted("Email Has Already Existed");
        }
        return userRepository.save(usr);
    }

    public User updateUser(User usr) {
        return userRepository.save(usr);
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findUserByPhone(String phone) {
        return userRepository.findUserByUserphone(phone);
    }



}
