package com.example.cardapio.controller;

import com.example.cardapio.food.Food;
import com.example.cardapio.food.FoodRepository;
import com.example.cardapio.food.FoodRequestDTO;
import com.example.cardapio.food.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodController {
    @Autowired
    private FoodRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*") //http://localhost:3000
    @PostMapping("/food")
    public void saveFood(@RequestBody FoodRequestDTO data){
        Food foodData = new Food(data);
        repository.save(foodData);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*") //http://localhost:3000
    @GetMapping("/foods")
    public List<FoodResponseDTO> getAll(){
        List<FoodResponseDTO> foodList = repository.findAll().stream().map(FoodResponseDTO::new).toList();
        return foodList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/food/{id}")
    public void changeFood(@RequestBody FoodRequestDTO data, @PathVariable("id") long foodId){
        Food foodData = repository.findById(foodId).get();
        foodData.setTitle(data.title());
        foodData.setPrice(data.price());
        foodData.setImage(data.image());

        repository.save(foodData);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/food/{id}")
    public void deleteFood(@PathVariable("id") long foodId){
        repository.deleteById(foodId);
    }
}
