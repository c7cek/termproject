package com.scpg.ikg.repo.abstracts;

import com.scpg.ikg.entities.concretes.Student;
import com.scpg.ikg.entities.dtos.StudentDetailDto;
import com.scpg.ikg.entities.dtos.StudentEditDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IStudentDao extends JpaRepository<com.scpg.ikg.entities.concretes.Student, Integer> {
    List<com.scpg.ikg.entities.concretes.Student> findByName(String studentName);

    com.scpg.ikg.entities.concretes.Student findById(int studentId);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);


    @Query("select new com.scpg.ikg.entities.dtos.StudentDetailDto(s.id,s.name,s.lastName) from Student s where s.username=:username")
    com.scpg.ikg.entities.dtos.StudentDetailDto getStudentDetailDto(String username);

    @Query("select  new com.scpg.ikg.entities.dtos.StudentEditDto(s.id,s.name,s.lastName,s.username,s.email) from Student s where s.username=:username")
    com.scpg.ikg.entities.dtos.StudentEditDto getStudentDtoByUsername(String username);
}
