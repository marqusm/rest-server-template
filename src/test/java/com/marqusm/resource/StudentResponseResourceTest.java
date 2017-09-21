package com.marqusm.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marqusm.SpringTest;
import com.marqusm.model.dto.StudentDto;
import com.marqusm.model.response.StudentResponse;
import com.marqusm.repository.StudentRepository;
import com.marqusm.util.MockMvcUtil;
import com.marqusm.util.Transformer;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 31-Aug-17
 */
public class StudentResponseResourceTest extends SpringTest {

  @Autowired
  private ErrorHandlingResource studentResource;
  @Autowired
  private StudentRepository studentRepository;
  @Autowired
  private Transformer transformer;
  @Autowired
  private ObjectMapper jsonMapper;
  private MockMvcUtil mockMvcUtil;

  @Before
  public void setUp() {
    mockMvcUtil = new MockMvcUtil(studentResource, "/student", jsonMapper);
  }

  @Test
  public void readStudent() throws Exception {
    StudentDto studentDto = new StudentDto("Read Student");
    studentDto.setCreationMetadata(true, LocalDateTime.now(), 1L);
    studentRepository.save(studentDto);

    StudentResponse expectedResponse = new StudentResponse(studentDto.getName());

    mockMvcUtil.testGet(Collections.singletonList(studentDto.getId()), expectedResponse);
  }

  @Test
  public void readStudent_nonExisting() throws Exception {
    mockMvcUtil.testGet(Collections.singletonList(-1), HttpStatus.NOT_FOUND, new byte[]{});
  }

  @Test
  public void readAllStudents() throws Exception {
    StudentDto studentDto = new StudentDto("Read All Students 1");
    studentDto.setCreationMetadata(true, LocalDateTime.now(), 1L);
    studentRepository.save(studentDto);

    studentDto = new StudentDto("Read All Students 2");
    studentDto.setCreationMetadata(true, LocalDateTime.now(), 1L);
    studentRepository.save(studentDto);

    List<StudentResponse> expectedResponse = transformer
        .transform(StudentResponse.class, studentRepository.findAll());

    mockMvcUtil.testGet(expectedResponse);
  }

}