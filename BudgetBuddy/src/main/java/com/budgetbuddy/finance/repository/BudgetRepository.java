package com.budgetbuddy.finance.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.budgetbuddy.finance.dto.BudgetDTO;

@Mapper
@Repository
public interface BudgetRepository {

	public List<BudgetDTO> selectBudgetList(Map<String,Object> params) throws Exception;
	public BudgetDTO getMonthlyTotal(Map<String,Object> params) throws Exception;
}
