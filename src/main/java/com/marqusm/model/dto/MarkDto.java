package com.marqusm.model.dto;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 21-Sep-17
 */
@Entity
@Table(name = "mark")
public class MarkDto extends DtoMetadata {

  @Id
  @SequenceGenerator(name = "id_seq", sequenceName = "id_seq", allocationSize = 1)
  @GeneratedValue(generator = "id_seq")
  @Column(name = "mark_id")
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "student_id")
  private StudentDto studentDto;
  @Column(name = "value")
  private Integer value;

  public MarkDto() {
  }

  public MarkDto(StudentDto studentDto, Integer value) {
    this.studentDto = studentDto;
    this.value = value;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public StudentDto getStudentDto() {
    return studentDto;
  }

  public void setStudentDto(StudentDto student) {
    this.studentDto = student;
  }

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("student", studentDto)
        .append("value", value)
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
    MarkDto markDto = (MarkDto) o;
    return Objects.equals(id, markDto.id) &&
        Objects.equals(studentDto, markDto.studentDto) &&
        Objects.equals(value, markDto.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, studentDto, value);
  }
}
