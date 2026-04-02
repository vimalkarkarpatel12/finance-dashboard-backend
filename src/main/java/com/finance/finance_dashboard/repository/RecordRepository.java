package com.finance.finance_dashboard.repository;

import com.finance.finance_dashboard.model.FinancialRecord;
import com.finance.finance_dashboard.model.RecordType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecordRepository extends JpaRepository<FinancialRecord, Long>,
        JpaSpecificationExecutor<FinancialRecord> {

    @Query("""
        SELECT COALESCE(SUM(r.amount), 0)
        FROM FinancialRecord r
        WHERE r.type = :type AND r.deleted = false
    """)
    double sumByType(@Param("type") RecordType type);

    @Query("""
        SELECT r.category, SUM(r.amount)
        FROM FinancialRecord r
        WHERE r.deleted = false
        GROUP BY r.category
    """)
    List<Object[]> sumByCategory();

    List<FinancialRecord> findTop5ByDeletedFalseOrderByCreatedAtDesc();

    @Query("""
        SELECT FUNCTION('MONTH', r.createdAt), SUM(r.amount)
        FROM FinancialRecord r
        WHERE r.deleted = false
        GROUP BY FUNCTION('MONTH', r.createdAt)
    """)
    List<Object[]> monthlySummary();

    Page<FinancialRecord> findAll(Pageable pageable);

    Page<FinancialRecord> findByDeletedFalse(Pageable pageable);

    List<FinancialRecord> findByCategoryContainingIgnoreCaseAndDeletedFalse(String category);
}
