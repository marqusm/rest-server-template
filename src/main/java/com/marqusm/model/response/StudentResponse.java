package com.marqusm.model.response;

import com.marqusm.model.dto.StudentDto;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 29-Aug-17
 */
public class StudentResponse {

  private String name;

  public StudentResponse() {
  }

  public StudentResponse(String name) {
    this.name = name;
  }

  public StudentResponse(StudentDto studentDto) {
    name = studentDto.getName();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
