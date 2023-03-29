package com.example.checklistapi.persistence.service;

import com.example.checklistapi.dto.request.ChecklistRequest;
import com.example.checklistapi.persistence.pojo.CheckList;

import java.util.List;

public interface ChecklistService {
    void insertCheckList(ChecklistRequest request);
    List<CheckList> getAllCheckList();
    void deleteChecklist(Integer id);

    CheckList findById(Integer id);
}
