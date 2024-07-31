package com.budgetbuddy.finance.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.budgetbuddy.finance.dto.BudgetDTO;
import com.budgetbuddy.finance.service.BudgetService;


/**
 * 가계부 관련 API
 */
@RestController
@RequestMapping(value = "/budget", produces = "application/json; charset=utf8")
public class BudgetController {

	@Autowired
	BudgetService budgetService;
	
	// 수입/지출 내역 조회
    @PostMapping("/list")
    public ResponseEntity<Map<String, Object>> selectBudgetList(@RequestParam Map<String,Object> params) {
    	
    	Map<String, Object> result = new HashMap<>();
    	try 
    	{
			List<BudgetDTO> budgetList = budgetService.selectBudgetList(params);
			result.put("data", budgetList);
		} 
    	catch (Exception e) 
    	{
    		result.put("error", e.getMessage());
		}

    	return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }
    
    
    // 월별 수입/지출 
    @PostMapping(value = "/monthly/total")
    public ResponseEntity<Map<String, Object>> getMonthlyTotal(@RequestParam Map<String,Object> params) {

        Map<String, Object> result = new HashMap<>();
        
        try 
        {
        	BudgetDTO budgetTotal = budgetService.getMonthlyTotal(params);
        	
        	result.put("monthly_expend_sum", budgetTotal.getMonthly_expend_sum());
        	result.put("monthly_income_sum", budgetTotal.getMonthly_income_sum());
        } 
        catch (Exception ex) 
        {
            result.put("error", ex.getMessage());
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

}
