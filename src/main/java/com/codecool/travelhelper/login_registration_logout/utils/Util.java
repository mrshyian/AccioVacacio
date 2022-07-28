package com.codecool.travelhelper.login_registration_logout.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Configuration
public class Util {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public String hashPassword(String password){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] resultByteArray =  messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : resultByteArray) {
                sb.append(String.format("%02x",b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return "";
    }
}
