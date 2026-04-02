package com.finance.finance_dashboard.service;

import com.finance.finance_dashboard.model.FinancialRecord;
import com.finance.finance_dashboard.model.RecordType;
import com.finance.finance_dashboard.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final RecordRepository recordRepository;


    public double totalIncome() {
        return recordRepository.sumByType(RecordType.INCOME);
    }

    public double totalExpense() {
        return recordRepository.sumByType(RecordType.EXPENSE);
    }


    public double netBalance() {
        return totalIncome() - totalExpense();
    }

    public Map<String, Double> categoryTotals() {
        return recordRepository.sumByCategory()
                .stream()
                .collect(Collectors.toMap(
                        obj -> (String) obj[0],
                        obj -> ((Number) obj[1]).doubleValue()
                ));
    }


    public List<FinancialRecord> recentActivity() {
        return recordRepository.findTop5ByDeletedFalseOrderByCreatedAtDesc();
    }


    public Map<Integer, Double> monthlyTrends() {
        return recordRepository.monthlySummary()
                .stream()
                .collect(Collectors.toMap(
                        obj -> ((Number) obj[0]).intValue(),
                        obj -> ((Number) obj[1]).doubleValue()
                ));
    }

}
