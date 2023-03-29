package com.example.checklistapi.controller;

import com.example.checklistapi.dto.request.ChecklistItemRequest;
import com.example.checklistapi.dto.request.ChecklistRequest;
import com.example.checklistapi.dto.response.DataResponse;
import com.example.checklistapi.persistence.pojo.CheckListItem;
import com.example.checklistapi.persistence.service.ChecklistItemService;
import com.example.checklistapi.persistence.service.ChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/api/checklist")
public class ChecklistController {

    private final ChecklistService serviceService;
    private final ChecklistItemService checklistItemService;

    @Autowired
    public ChecklistController(
            ChecklistService serviceService,
            ChecklistItemService checklistItemService
    ) {
        this.serviceService = serviceService;
        this.checklistItemService = checklistItemService;
    }

    // Get All Checklist
    @GetMapping("/")
    public ResponseEntity<DataResponse> getAllChecklist(@RequestBody ChecklistRequest request) {
        try {
            return new ResponseEntity<>(new DataResponse(serviceService.getAllCheckList(), ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create new checklist
    @PostMapping("/")
    public ResponseEntity<DataResponse> insertChecklist(@RequestBody ChecklistRequest request) {
        try {
            serviceService.insertCheckList(request);
            return new ResponseEntity<>(new DataResponse(null, "Checklist created"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Delete checklist by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse> deleteChecklist(@PathVariable(name = "id") Integer id) {
        try {
            if (serviceService.findById(id) == null) {
                return new ResponseEntity<>(new DataResponse(null, "Checklist not found"), HttpStatus.NOT_FOUND);
            }
            serviceService.deleteChecklist(id);
            return new ResponseEntity<>(new DataResponse(null, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create new checklist item in checklist
    @PostMapping("/{checklistId}/item")
    public ResponseEntity<DataResponse> insertChecklistItem(@RequestBody ChecklistItemRequest request, @RequestParam("checklistId") Integer checklistId) {
        try {
            if (serviceService.findById(checklistId) == null) {
                return new ResponseEntity<>(new DataResponse(null, "Checklist not found"), HttpStatus.NOT_FOUND);
            }

            checklistItemService.insertCheckListItem(request, null, checklistId);
            return new ResponseEntity<>(new DataResponse(null, "Checklist item created"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get checklist item in checklist by checklist id
    @GetMapping("/{checklistId}")
    public ResponseEntity<DataResponse> getAllChecklistItemByChecklistId(@RequestParam("checklistId") Integer checklistId) {
        try {
            return new ResponseEntity<>(new DataResponse(checklistItemService.getAllCheckListItemByChecklistId(checklistId), ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete item by checklist item id
    @DeleteMapping("/{checklistId}/item/{checklistItemId}")
    public ResponseEntity<DataResponse> deleteChecklistItem(
            @PathVariable(name = "checklistId") Integer checklistId,
            @PathVariable(name = "checklistItemId") Integer checklistItemId) {
        try {
            if (checklistItemService.findByIdAndChecklistId(checklistItemId, checklistId) == null) {
                return new ResponseEntity<>(new DataResponse(null, "Checklist not found"), HttpStatus.NOT_FOUND);
            }
            serviceService.deleteChecklist(checklistItemId);
            return new ResponseEntity<>(new DataResponse(null, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update status checklist item by checklist item id
    @PutMapping("/{checklistId}/item/{checklistItemId}")
    public ResponseEntity<DataResponse> updateStatusChecklistItem(
            @PathVariable(name = "checklistId") Integer checklistId,
            @PathVariable(name = "checklistItemId") Integer checklistItemId
    ) {
        try {
            if (checklistItemService.findByIdAndChecklistId(checklistItemId, checklistId) == null) {
                return new ResponseEntity<>(new DataResponse(null, "Checklist not found"), HttpStatus.NOT_FOUND);
            }
            checklistItemService.updateStatus(checklistItemId);
            return new ResponseEntity<>(new DataResponse(null, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Rename item by checlist item id
    @PutMapping("/{checklistId}/item/{checklistItemId}")
    public ResponseEntity<DataResponse> renameChecklistItem(
            @PathVariable(name = "checklistId") Integer checklistId,
            @PathVariable(name = "checklistItemId") Integer checklistItemId,
            @RequestBody ChecklistItemRequest request
    ) {
        try {
            if (checklistItemService.findByIdAndChecklistId(checklistItemId, checklistId) == null) {
                return new ResponseEntity<>(new DataResponse(null, "Checklist not found"), HttpStatus.NOT_FOUND);
            }
            checklistItemService.insertCheckListItem(request, checklistItemId, checklistId);
            return new ResponseEntity<>(new DataResponse(null, ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
