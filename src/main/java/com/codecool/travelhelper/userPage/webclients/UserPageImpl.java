package com.codecool.travelhelper.userPage.webclients;

import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.login_registration_logout.utils.sendMail.KindOfEmail;
import com.codecool.travelhelper.login_registration_logout.utils.sendMail.SendMailToUser;
import com.codecool.travelhelper.login_registration_logout.webclients.LoginImpl;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserPageImpl {

    @Autowired
    private SendMailToUser sendMailToUser;

    @Autowired
    private LoginImpl loginImpl;

    @Autowired
    private UserRepository userRepository;

    public MyUserTable getUserFromDB(){
        Long userId = loginImpl.getCurrentUserId();
        return  userRepository.findMyUserTableById(userId);
    }

    public void updateUser(String userData){

        JsonParser jsonParser = new JsonParser();
        JsonObject countryJsonObject = (JsonObject)jsonParser.parse(userData);

        String nickName = countryJsonObject.get("nickName").getAsString();
        String fullName = countryJsonObject.get("fullName").getAsString();
        String aboutMe = countryJsonObject.get("aboutMe").getAsString();
        String instagram = countryJsonObject.get("instagram").getAsString();
        String facebook = countryJsonObject.get("facebook").getAsString();
        String eMail = countryJsonObject.get("eMail").getAsString();

        MyUserTable userFromDB = userRepository.findAllByUserEMail(eMail).get();

        userFromDB.setAboutMe(aboutMe);
        userFromDB.setFacebook(facebook);
        userFromDB.setInstagram(instagram);
        userFromDB.setNickName(nickName);
        userFromDB.setFullName(fullName);

        userRepository.save(userFromDB);

        sendMailToUser.sendSimpleEmail(eMail, fullName, KindOfEmail.AFTER_USER_DETAILS_CHANGED);

    }
}
