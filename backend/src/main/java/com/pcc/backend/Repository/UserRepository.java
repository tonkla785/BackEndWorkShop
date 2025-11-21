package com.pcc.backend.Repository;

import com.pcc.backend.DTO.UserRequestDTO;
import com.pcc.backend.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
}
