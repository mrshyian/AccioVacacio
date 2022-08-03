package com.codecool.travelhelper.login_registration_logout.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class ThAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("doFilterInternal");
        if (request.getServletPath().equals("/app/login")  || request.getServletPath().equals("/auth/refreshToken")){
            filterChain.doFilter(request,response);
        }else {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                try {
                    System.out.println("doFilterInternal try");
                    String token = authorizationHeader.substring("Bearer ".length());
                    System.out.println(token);
                    System.out.println("doFilterInternal 1");
                    Algorithm algorithm = Algorithm.HMAC256("naszsupertajnykluczszyfrujacy".getBytes());
                    System.out.println("doFilterInternal 2");
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    System.out.println("doFilterInternal 3");
                    DecodedJWT decoded = verifier.verify(token);//nie działa
                    System.out.println("doFilterInternal 4");
                    String userEmail = decoded.getSubject();
                    System.out.println("doFilterInternal 5");
                    String[] roles = decoded.getClaim("roles").asArray(String.class);
                    System.out.println("doFilterInternal 6");
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    System.out.println("doFilterInternal 7");
                    stream(roles).forEach(role -> {
                        authorities.add(new SimpleGrantedAuthority(role));
                    });
                    System.out.println("doFilterInternal 8");
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userEmail,null,authorities);
                    System.out.println("doFilterInternal 9");
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    System.out.println("doFilterInternal 10");
                    filterChain.doFilter(request,response);
                    System.out.println("doFilterInternal 11");
                } catch (Exception e){
                    System.out.println("doFilterInternal else kurła");
                    response.setHeader("Error",e.getMessage());
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    Map<String,String> error = new HashMap<>();
                    error.put("Mesage",e.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(),error);
                }
            }else {
                filterChain.doFilter(request,response);
            }
        }
    }
}
