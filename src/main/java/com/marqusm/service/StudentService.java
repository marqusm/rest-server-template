package com.marqusm.service;

import com.marqusm.exception.NotFoundException;
import com.marqusm.model.dto.StudentDto;
import com.marqusm.model.response.StudentResponse;
import com.marqusm.repository.StudentRepository;
import com.marqusm.util.Checker;
import com.marqusm.util.Transformer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 29-Aug-17
 */
@Component
@Transactional
public class StudentService {

  private final StudentRepository studentRepository;
  private final Transformer transformer;

  @Autowired
  public StudentService(StudentRepository studentRepository, Transformer transformer) {
    this.studentRepository = studentRepository;
    this.transformer = transformer;
  }

  public StudentResponse getOneStudent(Long id) throws NotFoundException {
    StudentDto studentDto = studentRepository.findOne(id);
    Checker.checkNotNull(studentDto);
    return transformer.transform(StudentResponse.class, studentDto);
  }

  public List<StudentResponse> getAllStudents() throws NotFoundException {
    List<StudentDto> studentDtos = studentRepository.findAll();
//    Checker.checkNotNull(studentDto);
    return transformer.transform(StudentResponse.class, studentDtos);
  }
}
