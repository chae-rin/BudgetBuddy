package com.budgetbuddy.finance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.budgetbuddy.finance.dto.UserDTO;
import com.budgetbuddy.finance.repository.UserRepository;
import com.budgetbuddy.finance.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	public String getFindId(String user_email) throws Exception {
		return userRepository.getFindId(user_email);
	}
	
	public UserDTO login(UserDTO userDTO) throws Exception {
		return userRepository.login(userDTO);
	}
	
	public UserDTO getFindPw(UserDTO userDTO) throws Exception {
		return userRepository.getFindPw(userDTO);
	}
	
	public String getDupIdCheck(String check_id) throws Exception {
		return userRepository.getDupIdCheck(check_id);
	}
	
	public String getDupEmailCheck(String check_email) throws Exception {
		return userRepository.getDupEmailCheck(check_email);
	}
	
	public int registerUser(UserDTO userDTO) throws Exception {
		return userRepository.registerUser(userDTO);
	}
	
	public int resetPassword(UserDTO userDTO) throws Exception {
		return userRepository.resetPassword(userDTO);
	}

	public String getEncryptedPW(UserDTO userDTO) throws Exception {
		return userRepository.getEncryptedPW(userDTO);
	}
}