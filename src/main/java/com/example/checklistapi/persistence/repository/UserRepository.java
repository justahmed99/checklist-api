package com.example.checklistapi.persistence.repository;

import com.example.checklistapi.persistence.pojo.UserChecklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserChecklist, Integer> {
    @Query("FROM UserChecklist WHERE username = :username")
    UserChecklist findByUsername(@Param("username") String username);
}