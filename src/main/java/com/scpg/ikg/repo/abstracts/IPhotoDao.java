package com.scpg.ikg.repo.abstracts;

import com.scpg.ikg.entities.concretes.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPhotoDao extends JpaRepository<Photo,Integer> {
}
