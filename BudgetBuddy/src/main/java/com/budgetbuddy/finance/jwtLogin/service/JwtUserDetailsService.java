package com.budgetbuddy.finance.jwtLogin.service;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.budgetbuddy.finance.dto.UserDTO;
import com.budgetbuddy.finance.service.UserService;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserService userService;
	
	final Logger log = LoggerFactory.getLogger(JwtUserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("사용자 인증 execute");
		
		// parameter로 넘어온 userId 값으로 DB에 있는 유저 정보 조회
		UserDTO userDTO = new UserDTO();
		userDTO.setUser_id(username);
		
		UserDTO userInfo = new UserDTO();
		
		// DB에서 회원 정보 조회
		try {
			userInfo = userService.findByUserId(userDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		log.info("사용자 비밀번호 암호화 - start");
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encodePw = bCryptPasswordEncoder.encode(userInfo.getUser_pw());
		
		if(username.equals(userInfo.getUser_id())) {
			return new User(username, encodePw, new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username : " + username);
        }
	}
	
	// OVERLOAD
    public UserDetails loadUserByUsername(String username, String password) throws UsernameNotFoundException {
    	log.info("사용자 인증 execute");
    	
        // parameter 로 넘어온 username 값으로 DB에 있는 유저 정보 및 권한 조회
        UserDTO userDTO = new UserDTO();
		userDTO.setUser_id(username);
		
		UserDTO userInfo = new UserDTO();
		
		// DB에서 회원 정보 조회
		try {
			userInfo = userService.findByUserId(userDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(userInfo != null) {			
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			if(bCryptPasswordEncoder.matches(password, userInfo.getUser_pw())) {
				System.out.println("사용자 비밀번호 매칭 성공 : " + userInfo.getUser_id());
				return new User(username, userInfo.getUser_pw(), new ArrayList<>());
			} else {
				System.out.println("사용자 비밀번호 매칭 실패, 로그인 오류");
				throw new UsernameNotFoundException("Invalid user password");
			}
		} else {
			throw new UsernameNotFoundException("User Not Found : " + username);
		}
    }
	
}