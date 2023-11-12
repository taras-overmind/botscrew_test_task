package com.botscrew.test_task.repository;

import com.botscrew.test_task.model.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {
    @Override
    List<Lector> findAll();




}
