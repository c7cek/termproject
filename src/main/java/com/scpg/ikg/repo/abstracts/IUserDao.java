package com.scpg.ikg.repo.abstracts;

import com.scpg.ikg.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDao extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User findByEmailAndIdNot(String email, int id);

    User findByUsernameAndIdNot(String username, int id);


    User getByEmail(String email);
}
