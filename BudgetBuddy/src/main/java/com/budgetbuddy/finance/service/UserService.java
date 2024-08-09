package com.budgetbuddy.finance.service;

import com.budgetbuddy.finance.dto.UserDTO;

public interface UserService {
	public int registerUser(UserDTO userDTO) throws Exception;
	public String getDupIdCheck(String check_id) throws Exception;
	public String getDupEmailCheck(String check_email) throws Exception;
	public String getFindId(String user_email) throws Exception;
	public UserDTO getFindPw(UserDTO userDTO) throws Exception;
	public int resetPassword(UserDTO userDTO) throws Exception;
	public UserDTO findByUserId(UserDTO userDTO) throws Exception;
}