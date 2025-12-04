package com.pcc.backend.Repository;

import com.pcc.backend.DTO.UserRequestDTO;
import com.pcc.backend.DTO.UserResponseDTO;
import com.pcc.backend.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<UserEntity> findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM testdb.users");
        List<UserEntity> list = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(UserEntity.class));
        return list;
    }

    public List<UserEntity> findByField(Long id,String firstname, String lastname, Date birthday, Integer age, String gender, Date updatedate) {
        StringBuilder sql = new StringBuilder("SELECT * FROM testdb.users WHERE 1 = 1"); //where
        List<Object> params = new ArrayList<>();

        if (!ObjectUtils.isEmpty(id) && id >0) {
            sql.append(" AND id = ?");
            params.add(id);
        }

        if (!ObjectUtils.isEmpty(firstname)) {
            sql.append(" AND (firstname LIKE ? OR lastname LIKE ?)");
            params.add("%" + firstname + "%");
            params.add("%" + firstname + "%");
        }

        if (!ObjectUtils.isEmpty(lastname)) {
            sql.append(" AND lastname = ?");
            params.add(lastname);
        }

        if (!ObjectUtils.isEmpty(birthday)) {
            sql.append(" AND DATE(birthday) = ?");
            params.add(birthday);
        }

        if (!ObjectUtils.isEmpty(age) && age > 0) {
            sql.append(" AND age = ?");
            params.add(age);
        }

        if (!ObjectUtils.isEmpty(gender)) {
            sql.append(" AND gender = ?");
            params.add(gender);
        }

        if (!ObjectUtils.isEmpty(updatedate)) {
            sql.append(" AND DATE(update_date) = ?");
            params.add(updatedate);
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(UserEntity.class));
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        String sql = "INSERT INTO testdb.users (firstname, lastname, birthday, age, gender, update_date) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                userRequestDTO.getFirstname(),
                userRequestDTO.getLastname(),
                userRequestDTO.getBirthday(),
                userRequestDTO.getAge(),
                userRequestDTO.getGender(),
                new Timestamp(System.currentTimeMillis())
        );

        UserResponseDTO createdUser = new UserResponseDTO();
        createdUser.setFirstname(userRequestDTO.getFirstname());
        createdUser.setLastname(userRequestDTO.getLastname());
        createdUser.setBirthday(userRequestDTO.getBirthday());
        createdUser.setAge(userRequestDTO.getAge());
        createdUser.setGender(userRequestDTO.getGender());
        createdUser.setUpdateDate(new Timestamp(System.currentTimeMillis()));

        return createdUser;
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        String sql = "UPDATE testdb.users SET firstname = ?, lastname = ?, birthday = ?, age = ?, gender = ?, update_date = ? " +
                "WHERE id = ?";

        jdbcTemplate.update(
                sql,
                userRequestDTO.getFirstname(),
                userRequestDTO.getLastname(),
                userRequestDTO.getBirthday(),
                userRequestDTO.getAge(),
                userRequestDTO.getGender(),
                new Timestamp(System.currentTimeMillis()),
                id
        );

        UserResponseDTO updatedUser = new UserResponseDTO();
        updatedUser.setFirstname(userRequestDTO.getFirstname());
        updatedUser.setLastname(userRequestDTO.getLastname());
        updatedUser.setBirthday(userRequestDTO.getBirthday());
        updatedUser.setAge(userRequestDTO.getAge());
        updatedUser.setGender(userRequestDTO.getGender());
        updatedUser.setUpdateDate(new Timestamp(System.currentTimeMillis()));

        return updatedUser;
    }

    public boolean deleteUser(Long id) {
        String sql = "DELETE FROM testdb.users WHERE id = ?";
        int deleted = jdbcTemplate.update(sql, id);
        return deleted > 0;
    }

}
