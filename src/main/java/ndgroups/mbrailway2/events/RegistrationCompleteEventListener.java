package ndgroups.mbrailway2.events;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ndgroups.mbrailway2.model.User;
import ndgroups.mbrailway2.service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    @Autowired
    private IUserService userService;
    @Autowired
    private JavaMailSender mailSender;
    private User theUser;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //1. get the newly registered user
        theUser = event.getUser();
        //2. create a verification token for the user
        String verificationToken  = UUID.randomUUID().toString();
        //3. save the verification token for the user
        userService.saveUserVerificationToken(theUser, verificationToken);
        //4. build the verification url to be sent to the user
        String url  =  event.getApplicationUrl() + "/register/verifyEmail?token=" + verificationToken;
        //5. send the email
        try {
            sendVerificationEmail(url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
//        log.info("click the link to complete your registration : {}", url);
    }

    public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Email Verification";
        String senderName = "User Registration Portal Service";
        String mailContent = "<p>Hi, " + theUser.getUsername() + ", </p>" +
                "<p>Please follow the link to complete your registration </p>" +
                "<a href=\"" + url + "\">verify your email to activate account</a>" +
                "<p>thank you, User Registration Portal Service";
        MimeMessage message  = mailSender.createMimeMessage();
        var messageHelper  = new MimeMessageHelper(message);
        messageHelper.setFrom("idokobryan01@gmail.com", senderName);
        messageHelper.setTo(theUser.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);

    }
}

