package com.marqusm.model.dto;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 01-Sep-17
 */
@Entity
@Table(name = "student")
public class StudentDto extends DtoMetadata {

  @Id
  @SequenceGenerator(name = "id_seq", sequenceName = "id_seq", allocationSize = 1)
  @GeneratedValue(generator = "id_seq")
  @Column(name = "student_id")
  private Long id;
  @Column(name = "name")
  private String name;

  public StudentDto() {
  }

  public StudentDto(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String text) {
    this.name = text;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
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
    StudentDto that = (StudentDto) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
