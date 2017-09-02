package com.marqusm.resource;

import com.marqusm.exception.NotFoundException;
import com.marqusm.model.response.StudentResponse;
import com.marqusm.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 29-Aug-17
 */
@RestController
@RequestMapping("/student")
public class StudentResource extends BaseRepository {

  private final StudentService studentService;

  @Autowired
  public StudentResource(StudentService studentService) {
    this.studentService = studentService;
  }

  /**
   * Get one student
   *
   * @return {@link StudentResponse} student
   */
  @RequestMapping(value = "/{studentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public StudentResponse getOneStudent(@PathVariable("studentId") Long studentId)
      throws NotFoundException {
    return studentService.getOneStudent(studentId);
  }

  /**
   * Get all students
   *
   * @return {@link StudentResponse} students
   */
  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public List<StudentResponse> getAllStudents()
      throws NotFoundException {
    return studentService.getAllStudents();
  }
}
