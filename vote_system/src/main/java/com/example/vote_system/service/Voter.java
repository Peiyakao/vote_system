package com.example.vote_system.service;

import com.example.vote_system.service.SendResult;
import com.example.vote_system.service.VoteList;

import java.util.Map;

public interface Voter {
    int vote(SendResult sendResult);
    VoteList getcandidate();
    int addcandidate(Map<String,String> newcandidate);
    int removecandidate(Map<String,String> removeCandidate);
}
