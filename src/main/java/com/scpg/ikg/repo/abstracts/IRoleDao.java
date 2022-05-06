package com.scpg.ikg.repo.abstracts;

import com.scpg.ikg.core.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRoleDao extends JpaRepository <Role, Integer> {

    boolean existsByName(String roleName);

    Role findByName(String roleName);


    List<Role> getAllByUsers_Username(String username);
}
