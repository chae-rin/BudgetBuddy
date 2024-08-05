package com.budgetbuddy.finance.service;

import java.util.List;
import java.util.Map;

import com.budgetbuddy.finance.dto.CommonDTO;

public interface CommonService {
	
	public List<CommonDTO> selectCodeList(Map<String,Object> params) throws Exception;

}
