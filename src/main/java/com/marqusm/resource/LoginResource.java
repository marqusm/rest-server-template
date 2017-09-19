package com.marqusm.resource;

import com.marqusm.enumeration.ApiEndpoint;
import com.marqusm.model.request.LoginRequest;
import com.marqusm.model.security.Token;
import com.marqusm.service.TokenService;
import java.time.LocalDateTime;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 06-Sep-17
 */
@RestController
@RequestMapping(ApiEndpoint.LOGIN)
public class LoginResource extends ErrorHandlingResource {

  @Autowired
  private UserDetailsService userDetailsService;
  @Autowired
  private TokenService tokenService;

  @PostMapping
  public void login(@RequestBody LoginRequest loginRequest, HttpServletRequest request,
      HttpServletResponse response) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
        userDetails, null, userDetails.getAuthorities());
    authentication
        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    Token token = tokenService.generateToken(LocalDateTime.now().plusMinutes(15));
    tokenService.addToken(token);
    response.addCookie(new Cookie("auth_token", token.getToken()));
  }

  /**
   * Checks if user is logged in
   */
  @GetMapping
  public void checkLogin() {
    return;
  }

}
