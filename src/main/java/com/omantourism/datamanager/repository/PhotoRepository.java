package com.omantourism.datamanager.repository;

import com.omantourism.datamanager.Model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,Integer> {
}
