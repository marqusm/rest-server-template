package com.marqusm.security;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.Map;
import org.springframework.http.HttpMethod;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 10-Sep-17
 */
public class PublicApiEndpointMap {

  private final Map<String, HttpMethod[]> openApiEndpoints;

  public PublicApiEndpointMap() {
    this.openApiEndpoints = Maps.newHashMap();
  }

  public void addPublicApiUrl(String apiUrl, HttpMethod... methods) {
    openApiEndpoints.put(apiUrl, methods);
  }

  public Map<String, HttpMethod[]> getPublicApiUrls() {
    return ImmutableMap.copyOf(openApiEndpoints);
  }
}
