package com.codecool.travelhelper.login_registration_logout.webclients;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.login_registration_logout.utils.Util;
import com.codecool.travelhelper.login_registration_logout.utils.sendMail.KindOfEmail;
import com.codecool.travelhelper.login_registration_logout.utils.sendMail.SendMailToUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@Getter
@Setter
public class LoginImpl {

    private Long currentUserId;

    @Autowired
    private SendMailToUser sendMailToUser;

    @Autowired
    private Util util;

    @Autowired
    HttpSession session;

    @Autowired
    UserRepository userRepository;


    public Map<String,String> signWithGoogle(String data) {
        Map<String,String> tokens = new HashMap<>();
        JsonParser jsonParser = new JsonParser();
        JsonObject commentJsonObject = (JsonObject) jsonParser.parse(data);
        String email = commentJsonObject.get("email").getAsString();
        String fullName = commentJsonObject.get("fullName").getAsString();

        Optional<MyUserTable> userObject = userRepository.findAllByUserEMail(email);
        this.setCurrentUserId(null);

        if (userObject.isPresent()) {
            this.setCurrentUserId(userObject.get().getId());

            List<String> userRoles = new ArrayList<>();
            userRoles.add(userObject.get().getId().toString());

            Algorithm algorithm = Algorithm.HMAC256("naszsupertajnykluczszyfrujacy".getBytes());
            String accessToken = JWT.create()
                    .withSubject(userObject.get().getId().toString())
                    .withIssuer("TripHelper")
                    .withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000))
                    .withClaim("roles",userRoles)
                    .sign(algorithm);

            String refreshToken = JWT.create()
                    .withSubject(userObject.get().getId().toString())
                    .withIssuer("TripHelper")
                    .withExpiresAt(new Date(System.currentTimeMillis()+24*60*60*1000))
                    .sign(algorithm);


            tokens.put("tokenDostempowy", accessToken);
            tokens.put("tokenOdświerzający", refreshToken);
        } else {
            byte[] array = new byte[7]; // length is bounded by 7
            new Random().nextBytes(array);
            String generatedString = new String(array, StandardCharsets.UTF_8);

            MyUserTable newUser = new MyUserTable(
                    fullName,
                    "[nick name]",
                    "",
                    email,
                    util.hashPassword(generatedString)
            );
            userRepository.save(newUser);
            sendMailToUser.sendSimpleEmail(email, fullName, KindOfEmail.AFTER_REGISTRATION, null);

            this.setCurrentUserId(newUser.getId());
        }
        return tokens;
    }

}
