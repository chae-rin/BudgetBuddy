package com.budgetbuddy.finance.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.budgetbuddy.finance.dto.CommonDTO;
import com.budgetbuddy.finance.service.CommonService;


/**
 * 공통 API
 */
@RestController
@RequestMapping(value = "/common", produces = "application/json; charset=utf8")
public class CommonController {

	@Autowired
	CommonService commonService;
	
	// 코드 리스트 조회
	@GetMapping("/code")
    public ResponseEntity<Map<String, Object>> selectCodeList(@RequestParam Map<String,Object> params) {
    	Map<String, Object> result = new HashMap<>();
    	try 
    	{
			List<CommonDTO> codeList = commonService.selectCodeList(params);
			result.put("data", codeList);
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
    
   
}
