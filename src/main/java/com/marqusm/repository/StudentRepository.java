package com.marqusm.repository;

import com.marqusm.model.dto.StudentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 01-Sep-17
 */
@Component
public interface StudentRepository extends JpaRepository<StudentDto, Long> {

}