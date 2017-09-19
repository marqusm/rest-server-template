package com.marqusm.resource;

import com.marqusm.enumeration.ApiEndpoint;
import com.marqusm.exception.NotFoundException;
import com.marqusm.model.request.StudentRequest;
import com.marqusm.model.response.StudentResponse;
import com.marqusm.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 29-Aug-17
 */
@RestController
@RequestMapping(ApiEndpoint.STUDENTS)
public class StudentResource extends ErrorHandlingResource {

  private final StudentService studentService;

  @Autowired
  public StudentResource(StudentService studentService) {
    this.studentService = studentService;
  }

  /**
   * Save student
   *
   * @return {@link StudentResponse} student
   */
  @Secured("ROLE_STUDENT")
  @PostMapping
  public void saveStudent(@RequestBody StudentRequest studentRequest)
      throws NotFoundException {
    studentService.saveStudent(studentRequest);
  }

  /**
   * Get one student
   *
   * @return {@link StudentResponse} student
   */
  @Secured("ROLE_STUDENT")
  @GetMapping("{studentId}")
  public StudentResponse getOneStudent(@PathVariable("studentId") Long studentId)
      throws NotFoundException {
    return studentService.getOneStudent(studentId);
  }

  /**
   * Get all students
   *
   * @return {@link List<StudentResponse>} students
   */
  @Secured("ROLE_STUDENT")
  @GetMapping
  public List<StudentResponse> getAllStudents() throws NotFoundException {
    return studentService.getAllStudents();
  }
}
