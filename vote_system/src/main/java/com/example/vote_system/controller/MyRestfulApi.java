package com.example.vote_system.controller;

import com.example.vote_system.service.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MyRestfulApi {
    @Autowired
    private com.example.vote_system.service.createVoter createVoter;
    @PostMapping("/vote")
    public ResponseEntity voteAccepter(@RequestBody SendResult sendResult){
        createVoter.vote(sendResult);
        if(createVoter.vote(sendResult)>0){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("投票成功");
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404");
    }
    @GetMapping("/votelist")
    public ResponseEntity getVoteList(){

        if(createVoter.getcandidate()!=null){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(createVoter.getcandidate());
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404");
    }
    @PostMapping("/addvoteselect")

    public ResponseEntity addNewCandidate(@RequestBody Map<String,String> newCandidate){
        createVoter.addcandidate(newCandidate);
        if(createVoter.addcandidate(newCandidate)>0){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("新增一筆投票項目");
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404");

    }
    @DeleteMapping("/deletvoteselect")
    public ResponseEntity removeCandidate(@RequestBody Map<String,String> removeCandidate){
        createVoter.removecandidate(removeCandidate);
        if(createVoter.addcandidate(removeCandidate)>0){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("移除投票項目");
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404");
    }


}
