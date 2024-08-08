package com.budgetbuddy.finance.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.budgetbuddy.finance.dto.UserDTO;

@Mapper
@Repository
public interface UserRepository {
	public String getFindId(String user_email) throws Exception;
	public UserDTO login(UserDTO userDTO) throws Exception;
	public UserDTO getFindPw(UserDTO userDTO) throws Exception;
	public String getDupIdCheck(String check_id) throws Exception;
	public String getDupEmailCheck(String check_email) throws Exception;
	public int registerUser(UserDTO userDTO) throws Exception;
	public int resetPassword(UserDTO userDTO) throws Exception;
	public String getEncryptedPW(UserDTO userDTO) throws Exception;
}
