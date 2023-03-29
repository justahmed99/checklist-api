package com.example.checklistapi.persistence.service.implementation;

import com.example.checklistapi.dto.request.ChecklistRequest;
import com.example.checklistapi.persistence.pojo.CheckList;
import com.example.checklistapi.persistence.repository.ChecklistRepository;
import com.example.checklistapi.persistence.service.ChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChecklistServiceImpl implements ChecklistService {
    private final ChecklistRepository repository;

    @Autowired
    public ChecklistServiceImpl(
            ChecklistRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public void insertCheckList(ChecklistRequest request) {
        CheckList checkList = new CheckList();
        checkList.setName(request.getName());
        repository.save(checkList);
    }

    @Override
    public List<CheckList> getAllCheckList() {
        return repository.findAll();
    }

    @Override
    public void deleteChecklist(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public CheckList findById(Integer id) {
        return repository.getById(id);
    }
}
