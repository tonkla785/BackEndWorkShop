package com.pcc.backend.Service;

import com.pcc.backend.DTO.UserRequestDTO;
import com.pcc.backend.DTO.UserResponseDTO;
import com.pcc.backend.Entity.UserEntity;
import com.pcc.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Get all user
    public List<UserEntity> getAllUser() {
        try {
            List<UserEntity> dataList = userRepository.findAll();
            return dataList;
        } catch (Exception e) {
            throw new RuntimeException("Error while get user data", e);
        }
    }

    //Find user by field
    public List<UserEntity> findUserByField(UserEntity userEntity) {
        try {
            return userRepository.findByField(
                    userEntity.getId(),
                    userEntity.getFirstname(),
                    userEntity.getLastname(),
                    userEntity.getBirthday(),
                    userEntity.getAge(),
                    userEntity.getGender(),
                    userEntity.getUpdateDate()
            );
        } catch (Exception e) {
            throw new RuntimeException("Error while searching", e);
        }
    }

    //Create user
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        try {
            validateUser(userRequestDTO);
            return userRepository.createUser(userRequestDTO);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error while saving", e);
        }
    }

    //Update user
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        try {
            validateUser(userRequestDTO);
            return userRepository.updateUser(id, userRequestDTO);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error while saving", e);
        }
    }

    //Delete User
    public void deleteUser(Long id) {
        try {
            boolean isDeleted = userRepository.deleteUser(id);
            if (!isDeleted) {
                throw new IllegalArgumentException("User ID not found: " + id);
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting", e);
        }
    }

    //Valid Method
    private void validateUser(UserRequestDTO userRequestDTO) {
        if (userRequestDTO == null) {
            throw new IllegalArgumentException("Please enter user data");
        }

        if (userRequestDTO.getFirstname() == null || userRequestDTO.getFirstname().isBlank()) {
            throw new IllegalArgumentException("Firstname not null");
        }

        if (userRequestDTO.getLastname() == null || userRequestDTO.getLastname().isBlank()) {
            throw new IllegalArgumentException("Lastname not null");
        }

        if (userRequestDTO.getBirthday() == null) {
            throw new IllegalArgumentException("Birthday not null");
        }

        if (userRequestDTO.getAge() == null || userRequestDTO.getAge() < 0) {
            throw new IllegalArgumentException("Age not null");
        }

        if (userRequestDTO.getGender() == null || userRequestDTO.getGender().isBlank()) {
            throw new IllegalArgumentException("Gender not null");
        }
    }

}
