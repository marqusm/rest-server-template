package com.marqusm.util;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 01-Sep-17
 */
@Component
public class TestStartUpRunner implements ApplicationRunner {

  private final static Logger logger = LoggerFactory.getLogger(TestStartUpRunner.class);

  @Override
  public void run(ApplicationArguments applicationArguments) throws Exception {
    logger.info("Init data loading started");
    // Set up application environment
    ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
  }
}
