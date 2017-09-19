package com.marqusm.filter;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 03-Sep-17
 */
//@Component
public class CorsFilter /*extends org.springframework.web.filter.CorsFilter */{/*

  private final static Logger logger = LoggerFactory.getLogger(CorsFilter.class);

  public CorsFilter(CorsConfigurationSource configSource) {
    super(configSource);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain)
      throws ServletException, IOException {
    logger.info("doFilterInternal");
    response.addHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
    response.addHeader("Access-Control-Allow-Credentials", "true");
    if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS"
        .equals(request.getMethod())) {
      // CORS "pre-flight" request
      response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, PATCH");
      response.addHeader("Access-Control-Allow-Headers",
          "X-Requested-With,Origin,Content-Type,Accept,Cache-Control");
    }
    filterChain.doFilter(request, response);
  }*/

}
