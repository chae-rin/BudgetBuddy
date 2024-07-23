package com.budgetbuddy.finance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.budgetbuddy.finance.dto.DBConnTestDTO;
import com.budgetbuddy.finance.service.DBConnTestService;


/**
 * db connection 테스트
 */
@RestController
@RequestMapping(value = "/test", produces = "application/json; charset=utf8")
public class DBConnTestController {

	@Autowired
	DBConnTestService service;
	
    @PostMapping("/")
    public void getSingleCompAllFinanInfo() {
    	
    	try 
    	{
			List<DBConnTestDTO> list = service.getTestList();
			
			System.out.println( list.get(0).getTest_id() );
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
		}

    }

}
