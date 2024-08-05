package com.budgetbuddy.finance.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.budgetbuddy.finance.dto.CommonDTO;
import com.budgetbuddy.finance.repository.CommonRepository;
import com.budgetbuddy.finance.service.CommonService;


@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	CommonRepository commonRepository;
	
	public List<CommonDTO> selectCodeList(Map<String,Object> params) throws Exception {
		return commonRepository.selectCodeList(params);
	}

}
