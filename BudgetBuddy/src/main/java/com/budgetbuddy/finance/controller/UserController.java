package com.budgetbuddy.finance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.budgetbuddy.finance.dto.UserDTO;
import com.budgetbuddy.finance.service.UserService;

/**
 * User Controller
 */
@RestController
@RequestMapping(value = "/user", produces = "application/json; charset=utf8")
public class UserController {

	@Autowired
	UserService userService;
    
    @PostMapping("/register")
    public void registerUser(@RequestBody UserDTO userDTO) {
    	// 회원가입    	
    	userDTO.setUser_email(userDTO.getUser_email().toLowerCase());

    	try {
    		userService.registerUser(userDTO);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @PostMapping("/dupId")
    public String dupIdCheck(@RequestBody UserDTO userDTO) {
    	// ID 중복확인
    	String checkId = userDTO.getUser_id().toLowerCase();
    	String getCheckId = "";
    	
    	try {
    		getCheckId = userService.getDupIdCheck(checkId);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	if(checkId.equals(getCheckId)) {
    		return "fail";
    	} else {
    		return "success";
    	}
    }
    
    @PostMapping("/dupEmail")
    public String dupEmailCheck(@RequestBody UserDTO userDTO) {
    	// email 중복확인
    	String checkEmail = userDTO.getUser_email().toLowerCase();
    	String getCheckEmail = "";
    	
    	try {
    		getCheckEmail = userService.getDupEmailCheck(checkEmail);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	if(checkEmail.equals(getCheckEmail)) {
    		return "fail";
    	} else {
    		return "success";
    	}
    }
    
    @PostMapping("/loginPassword")
    public String loginPassword(@RequestBody UserDTO userDTO) {
    	String encryptedPW = null;
    	try {
    		encryptedPW = userService.getEncryptedPW(userDTO);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return encryptedPW;
    }
    
//    @PostMapping("/login")
//    public String login(@RequestBody UserDTO userDTO) {
//    	// 로그인    	
//    	UserDTO dtoResult = null;
//    	
//    	try {
//    		dtoResult = userService.login(userDTO);
//    	}
//    	catch (Exception e) {
//    		e.printStackTrace();
//    	}
//    	
//    	if(dtoResult == null) {
//    		return "fail";
//    	} else {
//    		return "success";
//    	}
//    }
    
    @PostMapping("/findId")
    public String findId(@RequestBody UserDTO userDTO) {
    	// 아이디 찾기
    	String findUserId = "";
    	try {
    		findUserId = userService.getFindId(userDTO.getUser_email());
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    	if(findUserId == null) findUserId = "NULL"; // 아이디없음

    	return findUserId;
    }
    
    @PostMapping("/findPw")
    public String findPw(@RequestBody UserDTO userDTO){
    	// 비밀번호 초기화
    	UserDTO dtoResult = null;
    	userDTO.setUser_email(userDTO.getUser_email().toLowerCase());
    	try {
    		dtoResult = userService.getFindPw(userDTO);
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	if(dtoResult == null) {
    		return "fail";
    	} else {
    		return "success";
    	}
    }
    
    @PostMapping("/resetPw")
    public void resetPw(@RequestBody UserDTO userDTO) {
    	// 비밀번호 리셋
    	try {
    		userService.resetPassword(userDTO);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
