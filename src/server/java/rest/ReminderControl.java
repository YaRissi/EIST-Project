package rest;

import model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ReminderService;

@RestController
public class ReminderControl {

    @Autowired
    private ReminderService reminderService;

    @Autowired
    private User user;

    @RequestMapping("send-reminder")
    public String send(){
        user.setEmail("david.steinberg17@gmail.com");

        reminderService.sendNotificationEmail(user);
        return "Success!";
    }

}
