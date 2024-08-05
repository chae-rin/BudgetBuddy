package com.budgetbuddy.finance.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.budgetbuddy.finance.dto.CommonDTO;

@Mapper
@Repository
public interface CommonRepository {

	public List<CommonDTO> selectCodeList(Map<String,Object> params) throws Exception;
}
