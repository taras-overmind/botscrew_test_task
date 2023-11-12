package com.botscrew.test_task.repository;

import com.botscrew.test_task.model.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {

    @Query(value = "select lector.name from lector", nativeQuery = true)
    List<String> getLectorNames();




}
