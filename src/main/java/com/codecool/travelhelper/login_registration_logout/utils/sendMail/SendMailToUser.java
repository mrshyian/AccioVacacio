package com.codecool.travelhelper.login_registration_logout.utils.sendMail;


import com.codecool.travelhelper.aws.database.models.MyUserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class SendMailToUser {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail,  String userName, KindOfEmail kindOfEmail, String friend){
        String mailText =  "";
        String mailTitle = "";

        switch (kindOfEmail){
            case AFTER_REGISTRATION:
                mailText = "Dear " + userName + "! You have just registered on Travel Helper. Thank you for using our service. Good luck!";
                mailTitle = "Welcome in our application!";
                break;

            case AFTER_USER_DETAILS_CHANGED:
                mailText = "Dear " + userName + "! At the moment perennial user details have been changed. If this was not done by you, please write us a message at this email:\n" +
                        "kodzikul@gmail.com. Greetings, Travel Helper administration. ";
                mailTitle = "Attention! You have some changes in your account.";
                break;

            case WAS_ADDED_TO_FRIENDS:
                mailText= "Hello " + userName + "! " + friend + " just added you to his friend list. Find him on the search tool and add him to your list of friends, too.";
                mailTitle = "You have a new friend :)";

            case WAS_REMOVED_FROM_FRIENDS:
                mailText= "Hello " + userName + "! " + friend + " just removed you from his friend list.";
                mailTitle = "You lost friend :(";

        }

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("kodzikul@gmail.com");
        message.setTo(toEmail);
        message.setText(mailText);
        message.setSubject(mailTitle);

        mailSender.send(message);
        System.out.println("Mail send...");
    }
}
