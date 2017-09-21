package com.marqusm.configuration;

import com.google.common.collect.ImmutableList;
import com.marqusm.enumeration.ApiEndpoint;
import com.marqusm.filter.RestAuthenticationTokenProcessingFilter;
import com.marqusm.security.PublicApiEndpointMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 03-Sep-17
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private RestAuthenticationTokenProcessingFilter tokenFilter;

  @Bean
  protected PublicApiEndpointMap getOpenApiEndpointList() {
    PublicApiEndpointMap publicApiEndpointMap = new PublicApiEndpointMap();
    publicApiEndpointMap.addPublicApiUrl(ApiEndpoint.LOGIN, HttpMethod.POST);
    return publicApiEndpointMap;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        // authorization
        .authorizeRequests()
//        .antMatchers(HttpMethod.POST, "/sign-in/**").permitAll()
//        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//        .antMatchers(HttpMethod.OPTIONS, "/students/**").permitAll()
//        .antMatchers(HttpMethod.POST, "/students/**").permitAll()
        .anyRequest().permitAll()//authenticated()
        .and()
        // form login
        .formLogin().disable()
//        .usernameParameter("username")
//        .passwordParameter("password")
//        .loginPage(null)
//        .defaultSuccessUrl("/", true)
//        .successHandler(getAuthenticationSuccessHandler())
//        .permitAll()
//        .and()
        // form logout
        .logout().disable()
//        .logoutUrl("/logout")
//        .invalidateHttpSession(true)
//        .logoutSuccessUrl("/")
//        .deleteCookies("JSESSIONID")
//        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//        .and()
        // token filter
        .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
//        .addFilterBefore(corsFilter, CorsFilter.class)
        // csrf
        .csrf().disable()
        // cors
        .cors()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  private AuthenticationSuccessHandler getAuthenticationSuccessHandler() {
    return (httpServletRequest, httpServletResponse, authentication) -> {
      return;
    };
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(new AuthenticationProvider() {
      @Override
      public Authentication authenticate(Authentication authentication)
          throws AuthenticationException {
        return null;
      }

      @Override
      public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
      }
    });
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    final CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(ImmutableList.of("*"));
    configuration
        .setAllowedMethods(ImmutableList
            .of(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
                HttpMethod.DELETE.name(), HttpMethod.PATCH.name(), HttpMethod.OPTIONS.name()));
    configuration.setAllowCredentials(true);
    configuration
        .setAllowedHeaders(ImmutableList.of("X-Auth-Token", "Cache-Control", "Content-Type"));
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public UserDetailsService getUserDetailsService() {
    return username -> new User("student", "student123", true, true, true, true,
        ImmutableList.of(new SimpleGrantedAuthority("ROLE_STUDENT")));
  }

}
