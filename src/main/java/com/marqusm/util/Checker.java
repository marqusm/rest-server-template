package com.marqusm.util;

import com.marqusm.exception.NotFoundException;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 02-Sep-17
 */
public class Checker {

  public static void checkNotNull(Object entity) throws NotFoundException {
    if (entity == null) {
      throw new NotFoundException();
    }
  }
}
