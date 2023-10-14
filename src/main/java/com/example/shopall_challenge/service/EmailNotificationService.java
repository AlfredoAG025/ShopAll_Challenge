package com.example.shopall_challenge.service;

import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.EmailNotificacion;
import com.example.shopall_challenge.repository.EmailNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmailNotificationService {
    private EmailNotificationRepository repository;

    @Autowired
    public EmailNotificationService(EmailNotificationRepository repository){

        this.repository = repository;
    }

    public GenericResponse getNotifications(){
        List<EmailNotificacion> emailNotifications = this.repository.findAll();
        GenericResponse response =  new GenericResponse(200, "Correct", emailNotifications);
        return response;
    }

    public GenericResponse getNotificationById(@PathVariable Long notificacion_id){
        List<EmailNotificacion> emailNotificacions = new ArrayList<>();
        EmailNotificacion emailNotificacion;
        Optional<EmailNotificacion> emailNotificacion_opt = repository.findById(notificacion_id);
        GenericResponse response = null;

        if (emailNotificacion_opt.isPresent()){
            emailNotificacion = emailNotificacion_opt.get();
            emailNotificacions.add(emailNotificacion);
            response =  new GenericResponse(201, "EmailNotification found", emailNotificacions);
        } else {
            response =  new GenericResponse(409, "EmailNotification not found", emailNotificacions);
        }
        return response;
    }

    public GenericResponse updateNotification(@PathVariable Long notification_id, @RequestBody EmailNotificacion body){
        List<EmailNotificacion> emailNotificacions = new ArrayList<>();
        EmailNotificacion emailNotificacion;
        Optional<EmailNotificacion> emailNotificacion_opt = repository.findById(notification_id);
        GenericResponse response = null;

        if (emailNotificacion_opt.isPresent()){
            emailNotificacion = body;
            emailNotificacions.add(emailNotificacion);
            repository.save(emailNotificacion);
            response =  new GenericResponse(201, "EmailNotification Updated!", emailNotificacions);
        } else {
            response =  new GenericResponse(409, "EmailNotification not found", emailNotificacions);
        }

        return response;
    }

    public GenericResponse addNotification(@RequestBody EmailNotificacion body){
        List<EmailNotificacion> emailNotificacions = new ArrayList<>();
        emailNotificacions.add(body);
        GenericResponse response;
        try{
            repository.save(body);
            response =  new GenericResponse(201, "Accepted", emailNotificacions);
        } catch (Exception e){
            response =  new GenericResponse(409, "Conflict", emailNotificacions);
        }
        return response;
    }

    public GenericResponse deleteNotification(@PathVariable Long notificacion_id){
        List<EmailNotificacion> emailNotificacion = new ArrayList<>();
        EmailNotificacion emailNotificacions;
        Optional<EmailNotificacion> emailNotification_opt = repository.findById(notificacion_id);
        GenericResponse response = null;

        if (emailNotification_opt.isPresent()){
            emailNotificacions = emailNotification_opt.get();
            emailNotificacion.add(emailNotificacions);
            repository.deleteById(notificacion_id);
            response =  new GenericResponse(201, "Notification with id: " + notificacion_id + " deleted", emailNotificacion);
        } else {
            response =  new GenericResponse(409, "Notification not found", emailNotificacion);
        }

        return response;
    }






}
