package com.scpg.ikg;

import com.scpg.ikg.business.abstracts.IRoleService;
import com.scpg.ikg.business.abstracts.IUserService;
import com.scpg.ikg.core.entities.Role;
import com.scpg.ikg.core.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class IkgApplication {

    public static void main(String[] args) {
        SpringApplication.run(IkgApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(IUserService userService, IRoleService roleService) {
        return args -> {

            userService.add(new User(0, "c7cek", "mustafaciceknote8@gmail.com", "12345", true, new ArrayList<>()));
            Role admin = new Role();
            admin.setId(0);
            admin.setName("admin");
            roleService.add(admin);
            userService.addRoleToUser("c7cek", "admin");
        };
    }

}
