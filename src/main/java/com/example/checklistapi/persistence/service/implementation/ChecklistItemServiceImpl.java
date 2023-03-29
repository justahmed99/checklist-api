package com.example.checklistapi.persistence.service.implementation;

import com.example.checklistapi.dto.request.ChecklistItemRequest;
import com.example.checklistapi.persistence.pojo.CheckList;
import com.example.checklistapi.persistence.pojo.CheckListItem;
import com.example.checklistapi.persistence.repository.ChecklistItemRepository;
import com.example.checklistapi.persistence.service.ChecklistItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChecklistItemServiceImpl implements ChecklistItemService {

    private final ChecklistItemRepository repository;

    @Autowired
    public ChecklistItemServiceImpl(
            ChecklistItemRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public void insertCheckListItem(ChecklistItemRequest request, Integer checklistItemId, Integer checklistId) {
        CheckListItem checkListItem = new CheckListItem();
        if (checklistItemId != null)
            checkListItem = findById(checklistItemId);
        else {
            checkListItem.setStatus(false);
        }
        checkListItem.setChecklistId(checklistId);
        checkListItem.setItemName(request.getItemName());
        repository.save(checkListItem);
    }

    @Override
    public List<CheckListItem> getAllCheckListItem() {
        return null;
    }

    @Override
    public List<CheckListItem> getAllCheckListItemByChecklistId(Integer checklistId) {
        return repository.findByChecklistId(checklistId);
    }

    @Override
    public void deleteChecklistItem(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public CheckListItem findById(Integer id) {
        return repository.getById(id);
    }

    @Override
    public CheckListItem findByIdAndChecklistId(Integer id, Integer checklistId) {
        return repository.findByIdAndChecklistId(id, checklistId);
    }

    @Override
    public void updateStatus(Integer id) {
        CheckListItem checkList = findById(id);
        checkList.setStatus(!checkList.getStatus());
    }
}
