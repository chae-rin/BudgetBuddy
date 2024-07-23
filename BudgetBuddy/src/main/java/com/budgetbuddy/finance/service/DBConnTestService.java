package com.budgetbuddy.finance.service;

import java.util.List;

import com.budgetbuddy.finance.dto.DBConnTestDTO;

public interface DBConnTestService {
	
	public List<DBConnTestDTO> getTestList() throws Exception;

}
