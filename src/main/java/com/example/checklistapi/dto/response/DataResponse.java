package com.example.checklistapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.repository.NoRepositoryBean;

@Data
@AllArgsConstructor
@NoRepositoryBean
public class DataResponse<T> {
    private T data;
    private String message;
}
