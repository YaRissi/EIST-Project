package de.tum.in.ase.insertteamnamehere.rest;


import de.tum.in.ase.insertteamnamehere.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import de.tum.in.ase.insertteamnamehere.service.ReminderService;


    @RestController

    @RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class ReminderControl {


    private ReminderService reminderService;


    private User user;

    @RequestMapping("send-reminder")
    public String send(){
        user.setEmail("david.steinberg17@gmail.com");

        reminderService.sendNotificationEmail(user);
        return "Success!";
    }

}
