package com.pcc.backend.Repository;

import com.pcc.backend.Entity.UserEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface UserJPARepository extends JpaRepository<UserEntity,Long>, JpaSpecificationExecutor<UserEntity> {
    default List<UserEntity> findByField(
            Long id,
            String firstname,
            String lastname,
            Date birthday,
            Integer age,
            String gender,
            Date updateDate
    ) {
        Specification<UserEntity> spec = (root, query, cb) -> {
            var predicates = cb.conjunction();

            if (id != null && id > 0) {
                predicates = cb.and(predicates, cb.equal(root.get("id"), id));
            }

            if (firstname != null && !firstname.isBlank()) {
                predicates = cb.and(predicates,
                        cb.or(
                                cb.like(root.get("firstname"), "%" + firstname + "%"),
                                cb.like(root.get("lastname"), "%" + firstname + "%")
                        ));
            }

            if (lastname != null && !lastname.isBlank()) {
                predicates = cb.and(predicates, cb.equal(root.get("lastname"), lastname));
            }

            if (birthday != null) {
                predicates = cb.and(predicates, cb.equal(root.get("birthday"), birthday));
            }

            if (age != null && age > 0) {
                predicates = cb.and(predicates, cb.equal(root.get("age"), age));
            }

            if (gender != null && !gender.isBlank()) {
                predicates = cb.and(predicates, cb.equal(root.get("gender"), gender));
            }

            if (updateDate != null) {
                predicates = cb.and(predicates, cb.equal(root.get("updateDate"), updateDate));
            }

            return predicates;
        };

        return findAll(spec);
    }
}
