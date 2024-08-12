package com.techelevator.controller;

import com.techelevator.dao.FollowDao;
import com.techelevator.dao.NotificationDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Follow;
import com.techelevator.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class NotificationController {

    private NotificationDao notificationDao;
    private FollowDao followDao;
    private UserDao userDao;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/notifications", method= RequestMethod.POST)
    public Notification addNotification(Principal principal, @RequestBody Notification notification) {
       return notificationDao.addNotification(notification);
    }

    @RequestMapping(path = "/notifications", method= RequestMethod.GET)
    public List<Notification> fetchListOfNotifications (Principal principal) {
        int userId = userDao.getUserByUsername(principal.getName()).getId();
        List<Follow> follows = followDao.getFollows(userId);
        return notificationDao.getListOfNotifications(follows);
    }

//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(path = "/notifications/{id}", method= RequestMethod.POST)
//    public Follow addNewFollow(@PathVariable String id, Principal principal) {
//
//    }

}
