package de.tum.in.ase.insertteamnamehere.service;

import de.tum.in.ase.insertteamnamehere.user.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


public class ReminderService {

    JavaMailSender javaMailSender;

    public void sendNotificationEmail(User user){
        SimpleMailMessage newMail = new SimpleMailMessage();
        newMail.setTo(user.getEmail());
        newMail.setSubject("Confirm your Reservation!");
        newMail.setText("Here is your Reservation: ... ");

        javaMailSender.send(newMail);
    }




}
