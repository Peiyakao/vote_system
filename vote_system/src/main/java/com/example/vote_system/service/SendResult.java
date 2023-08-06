package com.example.vote_system.service;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

public class SendResult {
@NotBlank
    String voter;
@NotEmpty
    List<ArrayList<String>> selection;

    public String getVoter() {
        return voter;
    }

    public void setVoter(String voter) {
        this.voter = voter;
    }

    public List<ArrayList<String>> getSelection() {
        return selection;
    }

    public void setSelection(List<ArrayList<String>> selection) {
        this.selection = selection;
    }
}
