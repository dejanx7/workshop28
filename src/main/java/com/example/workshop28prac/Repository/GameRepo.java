package com.example.workshop28prac.Repository;



import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationPipeline;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.internal.operation.AggregateOperation;

@Repository
public class GameRepo {

    @Autowired
    MongoTemplate template;


    public Document getGame(Integer game_id){

        // Criteria criteria = Criteria.where("gid").is(game_id);
        // Query query = Query.query(criteria);
        // System.out.println("get game"+template.find(query, Document.class, "game"));
        // return template.findOne(query, Document.class, "game");

        //using aggregation
        // db.game.aggregate([
        // {$match : {gid : 3} },
        // {$project : {_id : 0}}

        // ])

         MatchOperation matchGid = Aggregation.match(Criteria.where("gid").is(game_id));
         ProjectionOperation project = Aggregation.project().andExclude("_id");
         Aggregation pipeline = Aggregation.newAggregation(matchGid, project);
         AggregationResults<Document> results = template.aggregate(pipeline, "game", Document.class);
         return results.getMappedResults().get(0);
    }

    public List<Document> getReview(Integer game_id){

        // Criteria criteria = Criteria.where("gid").is(game_id);
        // Query query = Query.query(criteria);
        // return template.find(query, Document.class, "comment");

        //using aggregation
        // db.comment.aggregate([
        // {$match : {gid : 3}},
        // {$project : {_id : 0}}

        // ])

        MatchOperation matchGid = Aggregation.match(Criteria.where("gid").is(game_id));
        ProjectionOperation project = Aggregation.project().andExclude("_id");
        Aggregation pipeline = Aggregation.newAggregation(matchGid, project);
        AggregationResults<Document> results = template.aggregate(pipeline, "comment", Document.class);
        return results.getMappedResults();



    }
    
}
