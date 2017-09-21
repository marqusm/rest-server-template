package com.marqusm.service;

import com.marqusm.model.dto.StudentDto;
import com.marqusm.model.request.LoginRequest;
import com.marqusm.model.security.Token;
import com.marqusm.repository.StudentRepository;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 21-Sep-17
 */
@Service
public class LoginService {

  private final UserDetailsService userDetailsService;
  private final TokenService tokenService;
  private final StudentRepository studentRepository;

  @Autowired
  public LoginService(UserDetailsService userDetailsService, TokenService tokenService, StudentRepository studentRepository) {
    this.userDetailsService = userDetailsService;
    this.tokenService = tokenService;
    this.studentRepository = studentRepository;
  }

  public void login(LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
    UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    StudentDto studentDto = studentRepository.findOne(Example.of(new StudentDto(loginRequest.getUsername())));

    if (studentDto == null) {
      response.sendError(HttpStatus.UNAUTHORIZED.value(), "User not found.");
      response.flushBuffer();
    } else {
      Token token = tokenService.generateToken(LocalDateTime.now().plusMinutes(15), studentDto);
      tokenService.addToken(token);
      Cookie tokenCookie = new Cookie("auth_token", token.getValue());
      tokenCookie.setHttpOnly(true);
      response.addCookie(tokenCookie);
    }
  }

}
