package com.example.checklistapi.persistence.repository;

import com.example.checklistapi.persistence.pojo.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChecklistRepository extends JpaRepository<CheckList, Integer> {
}
