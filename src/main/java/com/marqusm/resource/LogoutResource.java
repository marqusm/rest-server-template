package com.marqusm.resource;

import com.marqusm.enumeration.ApiEndpoint;
import com.marqusm.enumeration.CookieName;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 20-Sep-17
 */
@RestController
@RequestMapping(ApiEndpoint.LOGOUT)
public class LogoutResource extends BaseResource {

  @PostMapping
  public void logout(HttpServletRequest request, HttpServletResponse response) {
    for (Cookie cookie : request.getCookies()) {
      if (cookie.getName().equals(CookieName.AUTH_TOKEN)) {
        cookie.setMaxAge(0);
        Cookie tokenCookie = new Cookie(CookieName.AUTH_TOKEN, cookie.getValue());
        tokenCookie.setMaxAge(0);
        tokenCookie.setHttpOnly(true);
        response.addCookie(tokenCookie);
        break;
      }
    }
  }
}
