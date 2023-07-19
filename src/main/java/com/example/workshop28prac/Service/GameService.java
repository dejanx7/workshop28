package com.example.workshop28prac.Service;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workshop28prac.Repository.GameRepo;

@Service
public class GameService {

    @Autowired
    GameRepo gameRepo;

    public Document getResult(Integer game_id) {

        Document gameDoc = gameRepo.getGame(game_id);
        List<Document> listOfReviewDocs = gameRepo.getReview(game_id);

        Document result = new Document();
        result.append("Game", gameDoc);
        result.append("reviews", listOfReviewDocs);
        return result;

    }

}
