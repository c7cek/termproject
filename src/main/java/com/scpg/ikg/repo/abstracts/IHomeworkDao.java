package com.scpg.ikg.repo.abstracts;

import com.scpg.ikg.entities.concretes.Homework;
import com.scpg.ikg.entities.concretes.Student;
import com.scpg.ikg.entities.dtos.SolutionDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IHomeworkDao extends JpaRepository<Homework, Integer> {

    Homework findById(int homeworkId);

    List<Homework> getAllByStudents_Username(String username);



}
