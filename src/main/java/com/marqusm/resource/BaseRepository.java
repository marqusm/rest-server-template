package com.marqusm.resource;

import com.marqusm.exception.NotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 02-Sep-17
 */
public class BaseRepository {

  private final static Logger logger = LoggerFactory.getLogger(BaseRepository.class);

  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(NotFoundException.class)
  @ResponseBody
  public void handleBadRequestException(HttpServletRequest req, Exception exception) {
    logger.info("Request: " + req.getRequestURL() + " raised exception: " + exception.toString());
  }
}
