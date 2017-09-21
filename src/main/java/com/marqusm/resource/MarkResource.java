package com.marqusm.resource;

import com.marqusm.enumeration.ApiEndpoint;
import com.marqusm.enumeration.CookieName;
import com.marqusm.model.request.MarkRequest;
import com.marqusm.model.response.MarkResponse;
import com.marqusm.service.MarkService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 21-Sep-17
 */
@RestController
@RequestMapping(ApiEndpoint.MARKS)
public class MarkResource extends BaseResource {

  private final MarkService markService;

  @Autowired
  public MarkResource(MarkService markService) {
    this.markService = markService;
  }

  @PostMapping
  public void saveMark(@CookieValue(name = CookieName.AUTH_TOKEN) String token, @RequestBody MarkRequest markRequest) {
    markService.saveMark(token, markRequest);
  }

  @GetMapping
  public List<MarkResponse> getAllMarks(@CookieValue(name = CookieName.AUTH_TOKEN) String token) {
    return markService.getAllMarks(token);
  }
}
