package com.example.shopall_challenge.service;

import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Resena;
import com.example.shopall_challenge.model.Vendedor;
import com.example.shopall_challenge.repository.ReviewRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ReviewService {


    private ReviewRepository resenaRepository;
    public ReviewService(ReviewRepository resenaRepository) {
        this.resenaRepository = resenaRepository;

    }
    public GenericResponse getReview(){
        List<Resena> sellers = this.resenaRepository.findAll();
        GenericResponse response =  new GenericResponse(200, "Correct", sellers);
        return response;
    }
}
