package com.example.checklistapi.persistence.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "checklist_item")
@Data
public class CheckListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String itemName;
    private Integer checklistId;
    private Boolean status = false;
}
