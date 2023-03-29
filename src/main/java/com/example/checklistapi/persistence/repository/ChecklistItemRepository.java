package com.example.checklistapi.persistence.repository;

import com.example.checklistapi.persistence.pojo.CheckListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChecklistItemRepository extends JpaRepository<CheckListItem, Integer> {

    @Query("FROM CheckListItem WHERE checklistId = :checklistId")
    List<CheckListItem> findByChecklistId(@Param("checklistId") Integer checklistId);

    @Query("FROM CheckListItem WHERE id = :id AND checklistId = :checklistId")
    CheckListItem findByIdAndChecklistId(
            @Param("id") Integer id,
            @Param("checklistId") Integer checklistId
    );
}
