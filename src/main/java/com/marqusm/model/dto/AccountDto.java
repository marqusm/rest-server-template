package com.marqusm.model.dto;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 06-Sep-17
 */
public class AccountDto {

  private String username;
  private String password;

  public AccountDto() {
  }

  public AccountDto(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
