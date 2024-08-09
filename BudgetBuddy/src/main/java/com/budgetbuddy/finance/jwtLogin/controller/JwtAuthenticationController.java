package com.budgetbuddy.finance.jwtLogin.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.budgetbuddy.finance.jwtLogin.config.JwtTokenUtil;
import com.budgetbuddy.finance.jwtLogin.model.JwtRequest;
import com.budgetbuddy.finance.jwtLogin.service.JwtUserDetailsService;

@RestController
@CrossOrigin(origins = "*")
public class JwtAuthenticationController {
	
	@Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;
    
    final Logger log = LoggerFactory.getLogger(JwtUserDetailsService.class);
    
    @RequestMapping(value="/authenticate", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUser_id(), authenticationRequest.getUser_pw());

        System.out.println("/authenticate");
        System.out.println(authenticationRequest.getUser_id());
        System.out.println(authenticationRequest.getUser_pw());
        System.out.println("user details : " + userDetails);
        final String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println("token : " + token);

        HashMap<String, Object> responseMap = new HashMap<String, Object>();

//        responseMap.put("token", new JwtResponse(token));
        responseMap.put("token", token);
        responseMap.put("auth", userDetails.getAuthorities());
        
        return ResponseEntity.ok(responseMap);
    }
}