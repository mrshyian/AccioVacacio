package com.codecool.travelhelper.login_registration_logout.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/users")
    public ResponseEntity<List<MyUserTable>> getAllUsers(){

        return ResponseEntity.ok().body(userService.getUsers());

    }

    @PostMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try {
                String refreshToken = authorizationHeader.replace("Bearer ","");
                Algorithm algorithm = Algorithm.HMAC256("naszsupertajnykluczszyfrujacy".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decoded = verifier.verify(refreshToken);//tu nie działa
                String userEmail = decoded.getSubject();
                MyUserTable user = userService.getUserById(userEmail);
                ArrayList<String> roles = new ArrayList<>();
                roles.add(user.getRole());
                String accessToken = JWT.create()
                        .withSubject(user.getId().toString())
                        .withIssuer("TripHelper")
                        .withExpiresAt(new Date(System.currentTimeMillis()+10*1000))
                        .withClaim("roles",roles)
                        .sign(algorithm);

                Map<String,String> tokens = new HashMap<>();
                tokens.put("accessToken", accessToken);
                tokens.put("refreshToken", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);

                new ObjectMapper().writeValue(response.getOutputStream(),tokens);

            } catch (Exception e){
                response.setHeader("Error",e.getMessage());
                response.setStatus(HttpStatus.NOT_FOUND.value());
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