package com.marqusm.model.response;

import com.marqusm.model.dto.StudentDto;
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("name", name)
        .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StudentResponse studentResponse = (StudentResponse) o;
    return Objects.equals(name, studentResponse.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
