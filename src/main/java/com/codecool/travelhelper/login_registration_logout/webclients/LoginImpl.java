package com.codecool.travelhelper.login_registration_logout.webclients;

import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.login_registration_logout.utils.Util;
import com.codecool.travelhelper.login_registration_logout.utils.sendMail.KindOfEmail;
import com.codecool.travelhelper.login_registration_logout.utils.sendMail.SendMailToUser;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Random;

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
    private HttpSession session;

    @Autowired
    private UserRepository userRepository;

    public String findUser(String data) {

        JsonParser jsonParser = new JsonParser();
        JsonObject commentJsonObject = (JsonObject) jsonParser.parse(data);
        String email = commentJsonObject.get("email").getAsString();
        String password = commentJsonObject.get("password").getAsString();

        Optional<MyUserTable> userObject = userRepository.findAllByUserEMail(email);
        this.setCurrentUserId(null);

        if (userObject.isPresent()) {
            String passwordFromDB = userObject.get().getPassword();
            if (validationPassword(util.hashPassword(password), passwordFromDB)) {
                session.setAttribute("userId", userObject.get().getId());
                this.setCurrentUserId(userObject.get().getId());
                return null;
            } else {
                System.out.println("incorrect log in ");
                return "Mail or password is incorrect";
            }
        } else {
            System.out.println("mail or password is incorrect");
            return "Mail or password is incorrect";
        }
    }

    public boolean validationPassword(String password, String passwordFromDB) {
        if (password.equals(passwordFromDB)) {
            return true;
        } else {
            return false;
        }
    }

    public String signWithGoogle(String data) {
        JsonParser jsonParser = new JsonParser();
        JsonObject commentJsonObject = (JsonObject) jsonParser.parse(data);
        String email = commentJsonObject.get("email").getAsString();
        String fullName = commentJsonObject.get("fullName").getAsString();
        String avatar = commentJsonObject.get("avatar").getAsString();

        Optional<MyUserTable> userObject = userRepository.findAllByUserEMail(email);
        this.setCurrentUserId(null);

        if (userObject.isPresent()) {
            session.setAttribute("userId", userObject.get().getId());
            this.setCurrentUserId(userObject.get().getId());
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
            newUser.setAvatar(avatar);
            userRepository.save(newUser);
            sendMailToUser.sendSimpleEmail(email, fullName, KindOfEmail.AFTER_REGISTRATION);

            session.setAttribute("userId", newUser.getId());
            this.setCurrentUserId(newUser.getId());
        }
        return String.valueOf(currentUserId);
    }

}
