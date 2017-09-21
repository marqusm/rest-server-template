package com.marqusm.service;

import com.marqusm.exception.NotFoundException;
import com.marqusm.model.dto.StudentDto;
import com.marqusm.model.request.StudentRequest;
import com.marqusm.model.response.StudentResponse;
import com.marqusm.repository.StudentRepository;
import com.marqusm.util.Checker;
import com.marqusm.util.Transformer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 29-Aug-17
 */
@Service
@Transactional
public class StudentService {

  private final StudentRepository studentRepository;
  private final Transformer transformer;

  @Autowired
  public StudentService(StudentRepository studentRepository, Transformer transformer) {
    this.studentRepository = studentRepository;
    this.transformer = transformer;
  }

  public void saveStudent(StudentRequest studentRequest) throws NotFoundException {
    StudentDto studentDto = studentRequest.export();
    studentDto.setCreationMetadata(true, LocalDateTime.now(), 1L);
    studentRepository.save(studentDto);
  }

  public StudentResponse getOneStudent(Long id) throws NotFoundException {
    StudentDto studentDto = studentRepository.findOne(id);
    Checker.checkNotNull(studentDto);
    return new StudentResponse(studentDto);
  }

  public List<StudentResponse> getAllStudents() throws NotFoundException {
    List<StudentDto> studentDtos = studentRepository.findAll();
    return studentDtos.stream()
        .map(studentDto -> new StudentResponse(studentDto))
        .collect(Collectors.toList());
  }
}
