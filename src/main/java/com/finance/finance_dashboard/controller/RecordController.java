package com.finance.finance_dashboard.controller;

import com.finance.finance_dashboard.dto.RecordRequest;
import com.finance.finance_dashboard.model.FinancialRecord;
import com.finance.finance_dashboard.service.RecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/records")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public FinancialRecord create(@RequestBody @Valid RecordRequest request) {
        return recordService.create(request);
    }

    // GET ALL (Pagination)
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','VIEWER')")
    public Page<FinancialRecord> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return recordService.getAll(page, size);
    }

    // UPDATE
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public FinancialRecord update(@PathVariable Long id,
                                  @RequestBody @Valid RecordRequest request) {
        return recordService.update(id, request);
    }

    // DELETE (Soft Delete)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Long id) {
        recordService.delete(id);
        return "Deleted successfully";
    }

    // FILTER
    @GetMapping("/filter")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','VIEWER')")
    public Page<FinancialRecord> filter(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {

        LocalDateTime start = startDate != null ? LocalDateTime.parse(startDate) : null;
        LocalDateTime end = endDate != null ? LocalDateTime.parse(endDate) : null;

        return recordService.filter(type, category, start, end, page, size);
    }

    // SEARCH
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','VIEWER')")
    public List<FinancialRecord> search(@RequestParam String keyword) {
        return recordService.search(keyword);
    }

}
