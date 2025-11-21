package com.pcc.backend.Controllers;

import com.pcc.backend.DTO.UserRequestDTO;
import com.pcc.backend.Entity.UserEntity;
import com.pcc.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user-controller")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get-user")
    public List<UserEntity> getAllUser(){
        return userService.getAllUser();
    }
}
