package com.marqusm.resource;

import com.marqusm.enumeration.ApiEndpoint;
import com.marqusm.model.request.LoginRequest;
import com.marqusm.service.LoginService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LoginResource extends BaseResource {

  private final LoginService loginService;

  @Autowired
  public LoginResource(LoginService loginService) {
    this.loginService = loginService;
  }

  @PostMapping
  public void login(@RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
    loginService.login(loginRequest, request, response);
  }

  /**
   * Checks if user is logged in
   */
  @GetMapping
  public void checkLogin() {
    return;
  }

}
