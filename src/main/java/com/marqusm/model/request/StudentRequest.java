package com.marqusm.model.request;

import com.marqusm.model.dto.StudentDto;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 03-Sep-17
 */
public class StudentRequest {

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public StudentDto export() {
    return new StudentDto(name);
  }

}
