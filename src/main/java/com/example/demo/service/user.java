package com.example.demo.service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Models.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface user {
    List<UserDTO> getAllUser();
    User findUserById(int id);
    User save(User usr);
    void delete(int id);



}
