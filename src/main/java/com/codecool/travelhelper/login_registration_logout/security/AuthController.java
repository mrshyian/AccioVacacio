package com.codecool.travelhelper.login_registration_logout.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.user.UserRoleTable;
import com.codecool.travelhelper.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final UserService userService;

//    http.authorizeRequests().antMatchers("/","/login/**","/refreshToken/**").permitAll();
//    http.authorizeRequests().antMatchers("/admin/user/save/**").hasAnyRole("ADMIN");
//    http.authorizeRequests().antMatchers("/users/**").hasAnyRole("USER");

    @GetMapping("/users")
    public ResponseEntity<List<MyUserTable>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getUsers());

    }

    @PostMapping("/admin/user/save")
    public ResponseEntity<MyUserTable> addUser(@RequestBody MyUserTable user){
        return ResponseEntity.ok().body(userService.saveUserToBD(user));
    }

    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try {
                String refreshToken = authorizationHeader.replace("Bearer ","");
                Algorithm algorithm = Algorithm.HMAC256("naszsupertajnykluczszyfrujacy".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decoded = verifier.verify(refreshToken);
                String userEmail = decoded.getSubject();
                MyUserTable user = userService.getUser(userEmail);
                String akcesToken = JWT.create()
                        .withSubject(user.getUserEMail())
                        .withIssuer("TripHelper")
                        .withExpiresAt(new Date(System.currentTimeMillis()+24*60*60*1000))
                        .withClaim("roles",user.getRole().stream().map(UserRoleTable::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String,String> tokens = new HashMap<>();
                tokens.put("tokenDostępowy", akcesToken);
                tokens.put("tokenOdświeżający", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);

            } catch (Exception e){
                response.setHeader("Error",e.getMessage());
                response.setStatus(HttpStatus.FORBIDDEN.value());
                Map<String,String> error = new HashMap<>();
                error.put("Message",e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),error);
            }
        }else {
            throw new RuntimeException("No Token!!");
        }
    }
}
