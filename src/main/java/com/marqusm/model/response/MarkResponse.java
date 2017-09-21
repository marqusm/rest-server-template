package com.marqusm.model.response;

import com.marqusm.model.dto.MarkDto;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 21-Sep-17
 */
public class MarkResponse {
  private Integer value;

  public MarkResponse() {
  }

  public MarkResponse(Integer value) {
    this.value = value;
  }

  public MarkResponse(MarkDto markDto) {
    this.value = markDto.getValue();
  }

  public Integer getValue() {
    return value;
  }
}
