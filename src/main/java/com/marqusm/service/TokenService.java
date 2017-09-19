package com.marqusm.service;

import com.marqusm.model.security.Token;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 10-Sep-17
 */
@Service
public class TokenService {

  private Map<String, Token> tokenMap;

  public TokenService() {
    this.tokenMap = new HashMap<>();
  }

  public Token getTokenByKey(String tokenKey) {
    return tokenMap.get(tokenKey);
  }

  public Token getTokenByValue(String tokenValue) {
    for (Token token : tokenMap.values()) {
      if (token.getToken().equals(tokenValue)) {
        return token;
      }
    }
    return null;
  }

  public Collection<Token> getTokens() {
    return tokenMap.values();
  }

  public void addToken(Token token) {
    tokenMap.put(token.getId(), token);
  }

  public Token generateToken(LocalDateTime expiredDate) {
    Token token = new Token();
    token.setExpiredDate(expiredDate);
    token.setCreatedDate(LocalDateTime.now());
    token.setId(UUID.randomUUID().toString());
    token.setToken(UUID.randomUUID().toString());
    return token;
  }

  public void updateLastLoginByCurrentDate(Token token) {
    token.setLastUsedDate(LocalDateTime.now());
    tokenMap.put(token.getId(), token);
  }

  public void updateToken(Token token) {
    tokenMap.put(token.getId(), token);
  }

  public void deleteToken(Integer tokenId) {
    tokenMap.remove(tokenId);
  }

  public Token getTokenById(Integer tokenId) {
    return tokenMap.get(tokenId);
  }

}
