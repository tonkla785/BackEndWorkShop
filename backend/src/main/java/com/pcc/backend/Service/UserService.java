package com.pcc.backend.Service;

import com.pcc.backend.DTO.UserRequestDTO;
import com.pcc.backend.Entity.UserEntity;
import com.pcc.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAllUser(){
        List<UserEntity> dataList = userRepository.findAll();
        return  dataList;
    }
}
