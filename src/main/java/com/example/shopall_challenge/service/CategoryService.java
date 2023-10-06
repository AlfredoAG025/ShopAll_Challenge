package com.example.shopall_challenge.service;

import com.example.shopall_challenge.domain.GenericResponse;
import com.example.shopall_challenge.model.Categoria;
import com.example.shopall_challenge.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository){
        this.repository = repository;
    }

    public GenericResponse getCategories(){
        List<Categoria> categories = this.repository.findAll();
        GenericResponse response =  new GenericResponse(200, "Correct", categories);
        return response;
    }

    public GenericResponse getCategoryById(@PathVariable Long category_id){
        List<Categoria> categories = new ArrayList<>();
        Categoria category;
        Optional<Categoria> category_opt = repository.findById(category_id);
        GenericResponse response = null;

        if (category_opt.isPresent()){
            category = category_opt.get();
            categories.add(category);
            response =  new GenericResponse(201, "Category found", categories);
        } else {
            response =  new GenericResponse(409, "Category not found", categories);
        }

        return response;
    }

    public GenericResponse updateCategory(@PathVariable Long category_id, @RequestBody Categoria body){
        List<Categoria> categories = new ArrayList<>();
        Categoria category;
        Optional<Categoria> user_opt = repository.findById(category_id);
        GenericResponse response = null;

        if (user_opt.isPresent()){
            category = body;
            categories.add(category);
            repository.save(category);
            response =  new GenericResponse(201, "Category Updated!", categories);
        } else {
            response =  new GenericResponse(409, "Category not found", categories);
        }

        return response;
    }

    public GenericResponse addCategory(@RequestBody Categoria body){
        List<Categoria> categories = new ArrayList<>();
        categories.add(body);
        GenericResponse response;
        try{
            repository.save(body);
            response =  new GenericResponse(201, "Accepted", categories);
        } catch (Exception e){
            response =  new GenericResponse(409, "Conflict", categories);
        }
        return response;
    }

    public GenericResponse deleteCategory(@PathVariable Long user_id){
        List<Categoria> categories = new ArrayList<>();
        Categoria category;
        Optional<Categoria> user_opt = repository.findById(user_id);
        GenericResponse response = null;

        if (user_opt.isPresent()){
            category = user_opt.get();
            categories.add(category);
            repository.deleteById(user_id);
            response =  new GenericResponse(201, "Category with id: " + user_id + " deleted", categories);
        } else {
            response =  new GenericResponse(409, "Category not found", categories);
        }

        return response;
    }
}
