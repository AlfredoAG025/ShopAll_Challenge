package com.example.shopall_challenge.service;

import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Resena;
import com.example.shopall_challenge.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {


    private ReviewRepository repository;
    public ReviewService(ReviewRepository resenaRepository) {
        this.repository = resenaRepository;

    }
    public GenericResponse getReview(){
        List<Resena> sellers = this.repository.findAll();
        GenericResponse response =  new GenericResponse(200, "Correct", sellers);
        return response;
    }

    public GenericResponse getReview(@PathVariable Long comentario_id){
        List<Resena> reviews = new ArrayList<>();
        Resena review;
        Optional<Resena> review_opt = repository.findById(comentario_id);
        GenericResponse response = null;

        if (review_opt.isPresent()){
            review = review_opt.get();
            reviews.add(review);
            response =  new GenericResponse(201, "Review found", reviews);
        } else {
            response =  new GenericResponse(409, "Review not found", reviews);
        }

        return response;
    }

    public GenericResponse addReview(@RequestBody Resena body){
        List<Resena> reviews = new ArrayList<>();
        reviews.add(body);

        GenericResponse response;
        try{
            repository.save(body);
            response =  new GenericResponse(201, "Accepted", reviews);
        } catch (Exception e){
            response =  new GenericResponse(409, "Conflict", reviews);
        }
        return response;
    }

    public GenericResponse updateReview(@PathVariable Long comentario_id, @RequestBody Resena body){
        List<Resena> reviews = new ArrayList<>();
        Resena review;
        Optional<Resena> review_opt = repository.findById(comentario_id);
        GenericResponse response = null;

        if (review_opt.isPresent()){
            review = body;
            reviews.add(review);
            repository.save(review);
            response =  new GenericResponse(201, "Reviews Updated!", reviews);
        } else {
            response =  new GenericResponse(409, "Reviews not found", reviews);
        }
        return response;
    }

    public GenericResponse deleteReview(@PathVariable Long comentario_id){
        List<Resena> sellers = new ArrayList<>();
        Resena review;
        Optional<Resena> review_opt = repository.findById(comentario_id);
        GenericResponse response = null;

        if (review_opt.isPresent()){
            review = review_opt.get();
            sellers.add(review);
            repository.deleteById(comentario_id);
            response =  new GenericResponse(201, "Review with id: " + comentario_id + " deleted", sellers);
        } else {
            response =  new GenericResponse(409, "Review not found", sellers);
        }

        return response;
    }

}
