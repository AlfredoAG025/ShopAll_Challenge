package com.example.shopall_challenge.controller;

import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.EmailNotificacion;
import com.example.shopall_challenge.service.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/emailNotification")
public class EmailNotificationController {
    private EmailNotificationService service;

    @Autowired
    public EmailNotificationController(EmailNotificationService service){
        this.service = service;
    }

    @GetMapping
    public GenericResponse getNotifications(){
        return this.service.getNotifications();
    }

    @GetMapping("{notification_id}")
    public GenericResponse getNotificationById(@PathVariable("notification_id") Long notification_id){
        return this.service.getNotificationById(notification_id);
    }


    @PostMapping("/add")
    public GenericResponse addNotification(@RequestBody EmailNotificacion body){
        return this.service.addNotification(body);
    }

    @DeleteMapping("/delete/{notification_id}")
    public GenericResponse deleteNotification(@PathVariable("notification_id") Long notification_id){
        return this.service.deleteNotification(notification_id);
    }

    @PutMapping("/update/{notification_id}")
    public GenericResponse updateNotification(@PathVariable("notification_id") Long notification_id, @RequestBody EmailNotificacion body){
        return this.service.updateNotification(notification_id, body);
    }
}
