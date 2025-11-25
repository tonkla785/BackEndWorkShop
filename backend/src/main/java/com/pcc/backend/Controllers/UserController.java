package com.pcc.backend.Controllers;

import com.pcc.backend.DTO.UserRequestDTO;
import com.pcc.backend.DTO.UserResponseDTO;
import com.pcc.backend.Entity.UserEntity;
import com.pcc.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user-controller")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get-all-user")
    public ResponseEntity<?> getAllUser() {
        try {
            List<UserEntity> data = userService.getAllUser();
            return ResponseEntity.ok(Map.of(
                    "responseStatus", 200,
                    "responseMessage", "Get user complete",
                    "data", data
            ));
        } catch (
                Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "responseStatus", 500,
                    "responseMessage", "Internal server error"
            ));
        }
    }

    @PostMapping("/find-user-by-field")
    public ResponseEntity<?> findUserByField(@RequestBody UserEntity userEntity) {
        try {
            List<UserEntity> searchData = userService.findUserByField(userEntity);
            return ResponseEntity.ok(Map.of(
                    "responseStatus", 200,
                    "responseMessage", "Search user complete",
                    "data", searchData
            ));
        } catch (
                Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "responseStatus", 500,
                    "responseMessage", "Internal server error"
            ));
        }
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        try {
            UserResponseDTO createdUser = userService.createUser(userRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "responseStatus", 200,
                    "responseMessage", "User created successfully",
                    "data", createdUser
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "responseStatus", 400,
                    "responseMessage", e.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "responseStatus", 500,
                    "responseMessage", "Internal server error"
            ));
        }
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        try {
            UserResponseDTO updatedUser = userService.updateUser(id, userRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "responseStatus", 200,
                    "responseMessage", "Update User successfully",
                    "data", updatedUser
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "responseStatus", 400,
                    "responseMessage", e.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "responseStatus", 500,
                    "responseMessage", "Internal server error"
            ));
        }
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok(Map.of(
                    "responseStatus", 200,
                    "responseMessage", "Delete user complete"
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "responseStatus", 404,
                    "responseMessage", e.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "responseStatus", 500,
                    "responseMessage", "Internal server error"
            ));
        }
    }
}
