package com.scpg.ikg.repo.abstracts;

import com.scpg.ikg.entities.concretes.Solution;
import com.scpg.ikg.entities.dtos.SolutionDetailDto;
import com.scpg.ikg.entities.dtos.SolutionUpdateDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISolutionDao extends JpaRepository<Solution, Integer> {

    Solution findById(int solutionId);


    @Query("select new com.scpg.ikg.entities.dtos.SolutionDetailDto(s.id,s.description,std.id,std.username,std.name,std.lastName,s.date,h.id) from Solution s inner join s.student std inner join s.homework h")
    List<SolutionDetailDto> getAllSolutionDetail();

    @Query("select new com.scpg.ikg.entities.dtos.SolutionDetailDto(s.id,s.description,std.id,std.username,std.name, std.lastName,s.date,h.id) from Solution s inner join s.student std inner join s.homework h where h.id=:homeworkId order by s.id desc")
    List<SolutionDetailDto> getSolutionDetailDtoByHomework(int homeworkId);

    @Query("select new com.scpg.ikg.entities.dtos.SolutionUpdateDto(s.id,h.id,std.id,s.date,s.description) from Solution s inner join s.student std inner join s.homework h where s.id=:solutionId")
    SolutionUpdateDto getSolutionUpdateDtoById(int solutionId);
}
