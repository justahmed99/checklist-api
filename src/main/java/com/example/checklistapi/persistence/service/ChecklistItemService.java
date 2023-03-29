package com.example.checklistapi.persistence.service;

import com.example.checklistapi.dto.request.ChecklistItemRequest;
import com.example.checklistapi.dto.request.ChecklistRequest;
import com.example.checklistapi.persistence.pojo.CheckList;
import com.example.checklistapi.persistence.pojo.CheckListItem;

import java.util.List;

public interface ChecklistItemService {
    void insertCheckListItem(ChecklistItemRequest request, Integer checklistItemId, Integer checklistId);
    List<CheckListItem> getAllCheckListItem();
    List<CheckListItem> getAllCheckListItemByChecklistId(Integer checklistId);
    void deleteChecklistItem(Integer id);
    CheckListItem findById(Integer id);
    CheckListItem findByIdAndChecklistId(Integer id, Integer checklistId);

    void updateStatus(Integer id);
}
