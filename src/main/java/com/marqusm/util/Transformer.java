package com.marqusm.util;

import com.marqusm.model.dto.Dto;
import com.marqusm.model.dto.StudentDto;
import com.marqusm.model.response.StudentResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 02-Sep-17
 */
@Component
public class Transformer {

  public <T> List<T> transform(Class<T> klass, List<StudentDto> dtoList) {
    List<T> results = new ArrayList<>();
    for (Object dto : dtoList) {
      if (dto instanceof Dto[]) {
        results.add(transform(klass, (Dto[]) dto));
      } else {
        results.add(transform(klass, (Dto) dto));
      }
    }
    return results;
  }

  public <T> T transform(Class<T> klass, Dto... dtos) {
    if (klass.equals(StudentResponse.class)) {
      return (T) new StudentResponse((StudentDto) dtos[0]);
    } else {
      throw new IllegalArgumentException(
          String.format("Class %s not supported", klass.getSimpleName()));
    }
  }

}
