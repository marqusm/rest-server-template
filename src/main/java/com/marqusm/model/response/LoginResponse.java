package com.marqusm.model.response;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 10-Sep-17
 */
public class LoginResponse {

  private String token;

  public LoginResponse() {
  }

  public LoginResponse(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
