package com.budgetbuddy.finance.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.budgetbuddy.finance.dto.DBConnTestDTO;

@Mapper
@Repository
public interface DBConnTestRepository {

	public List<DBConnTestDTO> getTestList() throws Exception;

}
