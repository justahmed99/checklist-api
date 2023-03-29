package com.example.checklistapi.persistence.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "checklist")
@Data
public class CheckList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
