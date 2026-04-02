package com.finance.finance_dashboard.dto;

import com.finance.finance_dashboard.model.RecordType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RecordRequest {

    @NotNull
    private Double amount;

    private String description;

    @NotNull
    private RecordType type;

    private String category;


}
