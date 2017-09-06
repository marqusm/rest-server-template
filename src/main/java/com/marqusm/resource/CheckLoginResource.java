package com.marqusm.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 06-Sep-17
 */
@RestController
@RequestMapping("check-login")
public class CheckLoginResource extends ErrorHandlingResource {

  /**
   * Checks if user is logged in
   */
  @GetMapping
  public void checkLogin() {
  }

}
