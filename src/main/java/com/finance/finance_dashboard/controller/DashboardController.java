package com.finance.finance_dashboard.controller;

import com.finance.finance_dashboard.model.FinancialRecord;
import com.finance.finance_dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/summary")
    public Map<String, Double> getSummary() {
        return Map.of(
                "totalIncome", dashboardService.totalIncome(),
                "totalExpense", dashboardService.totalExpense(),
                "netBalance", dashboardService.netBalance()
        );
    }


    @GetMapping("/category")
    public Map<String, Double> categoryTotals() {
        return dashboardService.categoryTotals();
    }


    @GetMapping("/recent")
    public List<FinancialRecord> recent() {
        return dashboardService.recentActivity();
    }


    @GetMapping("/monthly")
    public Map<Integer, Double> monthly() {
        return dashboardService.monthlyTrends();
    }

}
