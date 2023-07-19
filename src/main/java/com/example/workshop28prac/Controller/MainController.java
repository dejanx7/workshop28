package com.example.workshop28prac.Controller;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.workshop28prac.Service.GameService;

@RestController
@RequestMapping
public class MainController {

    @Autowired
    GameService gameService;


    @GetMapping(path="/game/{game_id}/reviews")
    public ResponseEntity<Document> getGameWithComments(@PathVariable Integer game_id){

        System.out.println(game_id);

        return ResponseEntity.ok(gameService.getResult(game_id));


    }
    
}
