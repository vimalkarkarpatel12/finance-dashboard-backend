package com.finance.finance_dashboard.specification;

import com.finance.finance_dashboard.model.FinancialRecord;
import com.finance.finance_dashboard.model.RecordType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class RecordSpecification {

    public static Specification<FinancialRecord> hasType(RecordType type) {
        return (root, query, cb) ->
                type == null ? null : cb.equal(root.get("type"), type);
    }

    public static Specification<FinancialRecord> hasCategory(String category) {
        return (root, query, cb) ->
                category == null ? null : cb.equal(root.get("category"), category);
    }

    public static Specification<FinancialRecord> dateBetween(LocalDateTime start, LocalDateTime end) {
        return (root, query, cb) -> {
            if (start == null || end == null) return null;
            return cb.between(root.get("createdAt"), start, end);
        };
    }

    public static Specification<FinancialRecord> notDeleted() {
        return (root, query, cb) -> cb.isFalse(root.get("deleted"));
    }


}
