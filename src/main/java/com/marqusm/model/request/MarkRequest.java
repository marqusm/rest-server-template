package com.marqusm.model.request;

import com.marqusm.model.dto.MarkDto;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 21-Sep-17
 */
public class MarkRequest {
  private Integer value;

  public MarkRequest() {
  }

  public MarkRequest(Integer value) {
    this.value = value;
  }

  public Integer getValue() {
    return value;
  }

  public MarkDto export() {
    return new MarkDto(null, value);
  }
}
