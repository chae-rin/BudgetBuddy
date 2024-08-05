package com.budgetbuddy.finance.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.budgetbuddy.finance.dto.BudgetDTO;
import com.budgetbuddy.finance.repository.BudgetRepository;
import com.budgetbuddy.finance.service.BudgetService;


@Service
public class BudgetServiceImpl implements BudgetService {

	@Autowired
	BudgetRepository budgetRepository;
	
	@Override
	public List<BudgetDTO> selectBudgetList(Map<String,Object> params) throws Exception {
		return budgetRepository.selectBudgetList(params);
	}
	
	@Override
	public BudgetDTO getMonthlyTotal(Map<String,Object> params) throws Exception {
		return budgetRepository.getMonthlyTotal(params);
	}

	@Override
	public int regBudgetInfo(Map<String, String> params) throws Exception {
		return budgetRepository.regBudgetInfo(params);
	}
	
	@Override
	public BudgetDTO getBudgetInfo(Map<String,Object> params) throws Exception {
		return budgetRepository.getBudgetInfo(params);
	}
	
	@Override
	public int updBudgetInfo(Map<String, String> params) throws Exception {
		return budgetRepository.updBudgetInfo(params);
	}
}
