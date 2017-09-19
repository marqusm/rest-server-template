package com.marqusm.model.security;

import com.marqusm.model.dto.StudentDto;
import java.time.LocalDateTime;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 10-Sep-17
 */
public class Token {
  private LocalDateTime expiredDate;
  private LocalDateTime createdDate;
  private LocalDateTime lastUsedDate;
  private String id;
  private String token;
  private StudentDto studentDto;

  public Token() {
  }

  public LocalDateTime getExpiredDate() {
    return expiredDate;
  }

  public void setExpiredDate(LocalDateTime expiredDate) {
    this.expiredDate = expiredDate;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public LocalDateTime getLastUsedDate() {
    return lastUsedDate;
  }

  public void setLastUsedDate(LocalDateTime lastUsedDate) {
    this.lastUsedDate = lastUsedDate;
  }

  public String getId() {
    return id;
  }

  public void setId(String key) {
    this.id = key;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
