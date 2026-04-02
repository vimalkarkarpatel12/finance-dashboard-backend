package com.finance.finance_dashboard.service;

import com.finance.finance_dashboard.dto.RecordRequest;
import com.finance.finance_dashboard.exception.ResourceNotFoundException;
import com.finance.finance_dashboard.model.FinancialRecord;
import com.finance.finance_dashboard.model.RecordType;
import com.finance.finance_dashboard.repository.RecordRepository;
import com.finance.finance_dashboard.specification.RecordSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    // CREATE
    public FinancialRecord create(RecordRequest request) {
        FinancialRecord record = new FinancialRecord();

        record.setAmount(request.getAmount());
        record.setType(request.getType());
        record.setCategory(request.getCategory());
        record.setDescription(request.getDescription());
        record.setDeleted(false);

        return recordRepository.save(record);
    }

    // GET ALL
    public Page<FinancialRecord> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return recordRepository.findByDeletedFalse(pageable);
    }

    // UPDATE
    public FinancialRecord update(Long id, RecordRequest request) {
        FinancialRecord record = recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));

        if (record.isDeleted()) {
            throw new ResourceNotFoundException("Record not found");
        }

        record.setAmount(request.getAmount());
        record.setType(request.getType());
        record.setCategory(request.getCategory());
        record.setDescription(request.getDescription());

        return recordRepository.save(record);
    }

    // SOFT DELETE
    public void delete(Long id) {
        FinancialRecord record = recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));

        if (record.isDeleted()) {
            throw new ResourceNotFoundException("Record already deleted");
        }

        record.setDeleted(true);
        recordRepository.save(record);
    }

    // FILTER (Specification + Soft Delete)
    public Page<FinancialRecord> filter(
            String type,
            String category,
            LocalDateTime startDate,
            LocalDateTime endDate,
            int page,
            int size
    ) {

        Specification<FinancialRecord> spec = Specification
                .where(RecordSpecification.notDeleted())
                .and(RecordSpecification.hasType(
                        type != null ? RecordType.valueOf(type.toUpperCase()) : null))
                .and(RecordSpecification.hasCategory(category))
                .and(RecordSpecification.dateBetween(startDate, endDate));

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        return recordRepository.findAll(spec, pageable);
    }

    // SEARCH (Soft Delete Applied)
    public List<FinancialRecord> search(String keyword) {
        return recordRepository
                .findByCategoryContainingIgnoreCaseAndDeletedFalse(keyword);
    }


}
