package com.example.shopall_challenge.controller;

import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Resena;
import com.example.shopall_challenge.model.Usuario;
import com.example.shopall_challenge.model.Vendedor;
import com.example.shopall_challenge.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/review")
public class ReviewController {

    private final ReviewService service;
    @Autowired
    public ReviewController(ReviewService service){
        this.service = service;
    }

    @GetMapping
    public GenericResponse getReview(){
        return this.service.getReview();
    }

    @GetMapping("{comentario_id}")
    public GenericResponse getReviewById(@PathVariable("comentario_id") Long comentario_id){
        return this.service.getReview(comentario_id);
    }

    @PostMapping("/add")
    public GenericResponse addReview(@RequestBody Resena body){
        return this.service.addReview(body);
    }

    @PutMapping("/update/{comentario_id}")
    public GenericResponse updateReview(@PathVariable("comentario_id") Long comentario_id, @RequestBody Resena body){
        return this.service.updateReview(comentario_id, body);
    }

    @DeleteMapping("/delete/{comentario_id}")
    public GenericResponse deleteReview(@PathVariable("comentario_id") Long comentario_id){
        return this.service.deleteReview(comentario_id);
    }




}


