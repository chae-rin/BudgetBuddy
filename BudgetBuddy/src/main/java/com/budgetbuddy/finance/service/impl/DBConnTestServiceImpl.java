package com.budgetbuddy.finance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.budgetbuddy.finance.dto.DBConnTestDTO;
import com.budgetbuddy.finance.repository.DBConnTestRepository;
import com.budgetbuddy.finance.service.DBConnTestService;


@Service
public class DBConnTestServiceImpl implements DBConnTestService {

	@Autowired
	DBConnTestRepository repository;
	
	public List<DBConnTestDTO> getTestList() throws Exception {
		return repository.getTestList();
	}

}
