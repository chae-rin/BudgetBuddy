package com.budgetbuddy.finance.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	@GetMapping("/list")
    public ResponseEntity<Map<String, Object>> selectBudgetList(@RequestParam Map<String,Object> params) {
		
    	Map<String, Object> result = new HashMap<>();
    	try 
    	{
    		// 수입/지출 전체내역 조회
			List<BudgetDTO> budgetList = budgetService.selectBudgetList(params);
			
			// 날짜 별로 값 분기처리
			/* key : '2024-07-31', value : {{id:01, detail:test...}, {id:02, detail:test...}...}
			 * key : '2024-07-29', value : {{id:01, detail:test...}, {id:02, detail:test...}...}
			 * */
			Map<String, Object> map = new LinkedHashMap<>();
			List<Object> list = new ArrayList<>();
			
			int lastIdx = budgetList.size()-1;
			for( int i=0; i<budgetList.size(); i++ )
			{
				String date = budgetList.get(i).getRecord_date();
				String nextDate = (i < lastIdx) ? budgetList.get(i+1).getRecord_date() : date;
				
				list.add(budgetList.get(i));
				
				if( !date.equals(nextDate) || (i==lastIdx) )
				{
					map.put(date, list);
					list = new ArrayList<>();
				}
			}
			
			result.put("data", map);
		} 
    	catch (Exception e) 
    	{
    		e.printStackTrace();
    		result.put("error", e.getMessage());
		}

    	return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }
    
    
    // 월별 수입/지출 합계 조회
    @GetMapping(value = "/monthly/total")
    public ResponseEntity<Map<String, Object>> getMonthlyTotal(@RequestParam Map<String,Object> params) {

        Map<String, Object> result = new HashMap<>();
        
        try 
        {
        	BudgetDTO budgetTotal = budgetService.getMonthlyTotal(params);
        	
        	result.put("monthly_expend_sum", budgetTotal.getMonthly_expend_sum());
        	result.put("monthly_income_sum", budgetTotal.getMonthly_income_sum());
        } 
        catch (Exception e) 
        {
        	e.printStackTrace();
            result.put("error", e.getMessage());
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }
    
    
    // 수입/지출 내역 등록
    @PostMapping(value = "")
    public ResponseEntity<Map<String, Object>> regBudgetInfo(@RequestParam Map<String, String> params) {

        Map<String, Object> response = new HashMap<>();
        
        try 
        {
        	String category = (params.get("recordType").equals("0")) ? params.get("expendCategory") : params.get("incomeCategory");
        	params.put("recordCategory", category);
        	
        	System.out.println("insert..." + params);
            
        	
        	int regResult = budgetService.regBudgetInfo( params );
            String result = (regResult == 0)? "FAIL" : "SUCCESS";
           
            response.put("data", result);
        } 
        catch (Exception e) 
        {
        	e.printStackTrace();
        	response.put("error", e.getMessage());
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
    
    
    // 수입/지출 정보 조회
 	@GetMapping("/info")
     public ResponseEntity<Map<String, Object>> getBudgetInfo(@RequestParam Map<String,Object> params) {
 		
     	Map<String, Object> result = new HashMap<>();
     	try 
     	{
 			BudgetDTO budgetList = budgetService.getBudgetInfo(params);
 			result.put("data", budgetList);
 		} 
     	catch (Exception e) 
     	{
     		e.printStackTrace();
     		result.put("error", e.getMessage());
 		}

     	return ResponseEntity
                 .status(HttpStatus.OK)
                 .body(result);
     }
 	
 	
 	 // 수입/지출 내역 수정
    @PutMapping(value = "/{recordId}")
    public ResponseEntity<Map<String, Object>> updBudgetInfo(@PathVariable String recordId, @RequestParam Map<String, Object> params) {

        Map<String, Object> response = new HashMap<>();
        try 
        {
        	params.put("size", params.size());
        	
        	String category = (params.get("recordType").equals("0")) ? (String)params.get("expendCategory") : (String)params.get("incomeCategory");
        	params.put("recordCategory", category);
        	
        	System.out.println( "update....." + params );
        	
        	int updResult = budgetService.updBudgetInfo( params );
            String result = (updResult == 0)? "FAIL" : "SUCCESS";
           
            response.put("data", result);
        } 
        catch (Exception e) 
        {
        	e.printStackTrace();
        	response.put("error", e.getMessage());
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
    
}
