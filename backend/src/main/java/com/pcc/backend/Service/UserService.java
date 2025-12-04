package com.pcc.backend.Service;

import com.pcc.backend.DTO.UserRequestDTO;
import com.pcc.backend.DTO.UserResponseDTO;
import com.pcc.backend.Entity.UserEntity;
import com.pcc.backend.Repository.UserJPARepository;
import com.pcc.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.sql.Date;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserJPARepository userJPARepository;

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

    //Get User Jpa
    public List<UserEntity> findAll() {
        try {
            return userJPARepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error while get user data", e);
        }
    }

    //Create User Jpa
    public UserResponseDTO createUserJpa(UserRequestDTO userRequestDTO) {
        try {
            validateUser(userRequestDTO);
            UserEntity userEntity = new UserEntity();
            userEntity.setFirstname(userRequestDTO.getFirstname());
            userEntity.setLastname(userRequestDTO.getLastname());
            userEntity.setAge(userRequestDTO.getAge());
            userEntity.setBirthday(userRequestDTO.getBirthday());
            userEntity.setGender(userRequestDTO.getGender());
            userEntity.setUpdateDate(java.sql.Date.valueOf(LocalDate.now()));
            UserEntity saved = userJPARepository.save(userEntity);

            UserResponseDTO dto = new UserResponseDTO();
            dto.setFirstname(saved.getFirstname());
            dto.setLastname(saved.getLastname());
            dto.setAge(saved.getAge());
            dto.setBirthday(saved.getBirthday());
            dto.setGender(saved.getGender());
            dto.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            return dto;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error while save user data", e);
        }
    }

    //Delete User Jpa
    public void deleteUserJpa(Long id) {
        try {
            UserEntity user = userJPARepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
            userJPARepository.delete(user);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Update User
    public UserResponseDTO updateUserJpa(long id, UserRequestDTO userRequestDTO) {
        try {
            UserEntity userEntity = userJPARepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
            validateUser(userRequestDTO);

            userEntity.setFirstname(userRequestDTO.getFirstname());
            userEntity.setLastname(userRequestDTO.getLastname());
            userEntity.setAge(userRequestDTO.getAge());
            userEntity.setBirthday(userRequestDTO.getBirthday());
            userEntity.setGender(userRequestDTO.getGender());
            userEntity.setUpdateDate(java.sql.Date.valueOf(LocalDate.now()));
            UserEntity saved = userJPARepository.save(userEntity);

            UserResponseDTO dto = new UserResponseDTO();
            dto.setFirstname(saved.getFirstname());
            dto.setLastname(saved.getLastname());
            dto.setAge(saved.getAge());
            dto.setBirthday(saved.getBirthday());
            dto.setGender(saved.getGender());
            dto.setUpdateDate(new Timestamp(System.currentTimeMillis()));
            return dto;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error while save user data", e);
        }
    }

    public List<UserEntity> findUserByFieldJpa(
            Long id,
            String firstname,
            String lastname,
            Date birthday,
            Integer age,
            String gender,
            Date updateDate
    ) {
        return userJPARepository.findByField(id, firstname, lastname, birthday, age, gender, updateDate);
    }

    //Valid Method
    private void validateUser(UserRequestDTO userRequestDTO) {
        if (ObjectUtils.isEmpty(userRequestDTO)) {
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
