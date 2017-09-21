package com.marqusm.filter;

import com.marqusm.model.security.Token;
import com.marqusm.security.PublicApiEndpointMap;
import com.marqusm.service.TokenService;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 10-Sep-17
 */
@Component
public class RestAuthenticationTokenProcessingFilter extends OncePerRequestFilter {

  private final static Logger logger = LoggerFactory
      .getLogger(RestAuthenticationTokenProcessingFilter.class);

  @Autowired
  private TokenService tokenService;
  @Autowired
  private PublicApiEndpointMap publicApiEndpointMap;
  @Autowired
  private UserDetailsService userService;
  private String REST_USER;

  public RestAuthenticationTokenProcessingFilter() {
  }

  public RestAuthenticationTokenProcessingFilter(UserDetailsService userService, String restUser) {
    this.userService = userService;
    this.REST_USER = restUser;
  }

  @Override
  public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    boolean isPublicApi = false;
    if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
      isPublicApi = true;
    } else {
      for (Map.Entry<String, HttpMethod[]> entry : publicApiEndpointMap.getPublicApiUrls().entrySet()) {
        if (request.getRequestURI().equals("/" + entry.getKey()) && entry.getValue() != null) {
          for (HttpMethod httpMethod : entry.getValue()) {
            if (request.getMethod().equals(httpMethod.name())) {
              isPublicApi = true;
              break;
            }
          }
          if (isPublicApi) {
            break;
          }
        }
      }
    }
    String authToken = extractAuthTokenFromRequest(request);
    if (isPublicApi) {
      logger.info("Api is open. No need to authenticate.");
      chain.doFilter(request, response);
    } else if (authToken == null) {
      logger.info("Unable to authenticate. There is no token.");
      response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unable to authenticate. There is no token.");
      response.flushBuffer();
    } else {
//        String tokenId = parts[0];
//        String tokenSecret = parts[1];
        Token token = tokenService.getTokenByValue(authToken);
        //        List<String> allowedIPs = //new Gson().fromJson(token.getAllowedIP(), new TypeToken<ArrayList<String>>() {}.getType());
        //        if (isAllowIP(allowedIPs, request.getRemoteAddr())) {
        if (token != null) {
          if (token.getValue().equals(authToken) && token.getExpiredDate().isAfter(LocalDateTime.now())) {
            UserDetails userDetails = userService.loadUserByUsername(REST_USER);
            UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.info("Authenticated " + token.getValue() + " via IP: " + request.getRemoteAddr());
            updateLastLogin(token);
            chain.doFilter(request, response);
          } else {
            String errorMessage = "Unable to authenticate the token: " + authToken + ". Token is expired.";
            logger.info(errorMessage);
            response.sendError(HttpStatus.UNAUTHORIZED.value(), errorMessage);
            response.flushBuffer();
          }
        } else {
          String errorMessage = "No token.";
          logger.info(errorMessage);
          response.sendError(HttpStatus.UNAUTHORIZED.value(), errorMessage);
          response.flushBuffer();
      }
    }

  }

  private void updateLastLogin(final Token token) {
    Thread updateTokenThread;
    updateTokenThread = new Thread(() -> tokenService.updateLastLoginByCurrentDate(token));
    updateTokenThread.setName("RESTTokenThread-" + RandomStringUtils.randomNumeric(4));
    updateTokenThread.start();
  }

  private boolean validateTokenKey(String tokenKey) {
    String[] parts = tokenKey.split("-");
    return parts.length == 5;
  }

  private String extractAuthTokenFromRequest(HttpServletRequest httpRequest) {
        /* Get token from header */
    String authToken = null;
    if (httpRequest.getCookies() != null) {
      for (Cookie cookie : httpRequest.getCookies()) {
        if (cookie.getName().equals("auth_token")) {
          authToken = cookie.getValue();
        }
      }
    }

		/* If token not found get it from request parameter */
//    if (authToken == null) {
//      authToken = httpRequest.getParameter("token");
//    }

    return authToken;
  }


}
