package com.example.springproject.controller;

import com.example.springproject.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final TransactionService transactionService;

    public ReportController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/summary")
    public ResponseEntity<Map<String, BigDecimal>> getFinancialSummary() {
        Map<String, BigDecimal> summary = transactionService.getFinancialSummary();
        return ResponseEntity.ok(summary);
    }


    @GetMapping("/monthly")
    public ResponseEntity<Map<String, Object>> getMonthlyReport(@RequestParam String month) {
        YearMonth yearMonth;
        try {
            yearMonth = YearMonth.parse(month);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().build();
        }

        Map<String, Object> report = transactionService.getMonthlyReport(yearMonth);
        return ResponseEntity.ok(report);
    }
}
