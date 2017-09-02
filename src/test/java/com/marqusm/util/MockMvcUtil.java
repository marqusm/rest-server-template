package com.marqusm.util;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 31-Aug-17
 */
public class MockMvcUtil {

  private Object controller;

  private String apiUrl;

  private ObjectMapper objectMapper;

  public MockMvcUtil(Object controller, String apiUrl, ObjectMapper objectMapper) {
    this.controller = controller;
    this.apiUrl = apiUrl;
    this.objectMapper = objectMapper;
  }

  public void testGet(Object expectedResponse) throws Exception {
    testGet(Collections.emptyList(), expectedResponse);
  }

  public void testGet(List params, Object expectedResponse) throws Exception {
    testGet(params, HttpStatus.OK, expectedResponse);
  }

  public void testGet(List params, HttpStatus expectedHttpStatus, Object expectedResponse)
      throws Exception {
    StringBuilder url = new StringBuilder(apiUrl);
    for (Object param : params) {
      url.append("/").append(param.toString());
    }

    ResultMatcher statusResultMatcher;
    statusResultMatcher = status().is(expectedHttpStatus.value());

    ResultMatcher contentResultMatcher;
    if (expectedResponse == null) {
      contentResultMatcher = content().bytes(new byte[0]);
    } else if (expectedResponse instanceof byte[]) {
      contentResultMatcher = content().bytes((byte[]) expectedResponse);
    } else {
      contentResultMatcher = content().json(objectMapper.writeValueAsString(expectedResponse));
    }

    MockMvc mockMvc = standaloneSetup(controller)/*.setMessageConverters(httpMessageConverter)*/
        .build();
    mockMvc.perform(
        get(url.toString()))
        .andExpect(statusResultMatcher)
        .andExpect(contentResultMatcher)
        .andReturn();
  }
}
