package com.staxrt.tutorial.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The type Jwt authorization filter.
 *
 * @author Givantha Kalansuriya @Project spring -boot-rest-api-auth-jwt-tutorial
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

  /**
   * Instantiates a new Jwt authorization filter.
   *
   * @param authenticationManager the authentication manager
   */
  public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    // read the request header and extract the JWT token
    String header = request.getHeader(JWTAuthenticationFilter.HEADER_STRING);

    if (header == null || !header.startsWith(JWTAuthenticationFilter.TOKEN_PREFIX)) {
      chain.doFilter(request, response);
      return;
    }

    try {
      // validate the JWT Token
      UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
      // if user is valid with token allow priced the request with adding user to security context
      SecurityContextHolder.getContext().setAuthentication(authentication);
      chain.doFilter(request, response);
    } catch (SignatureVerificationException e) {
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.getWriter().write("Authentication error, SignatureVerification fail.");
    } catch (TokenExpiredException e) {
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.getWriter().write("Authentication error, The Token's Expired.");
    }
  }

  private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
    String token = request.getHeader(JWTAuthenticationFilter.HEADER_STRING);
    if (token != null) {
      // parse the token.
      String user =
          JWT.require(Algorithm.HMAC512(JWTAuthenticationFilter.SECRET.getBytes()))
              .build()
              .verify(token.replace(JWTAuthenticationFilter.TOKEN_PREFIX, ""))
              .getSubject();

      if (user != null) {
        return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
      }
      return null;
    }
    return null;
  }
}
