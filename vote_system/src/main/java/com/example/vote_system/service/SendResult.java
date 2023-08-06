package com.example.vote_system.service;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SendResult {
@NotBlank
    String voter;
@NotEmpty
    List<ArrayList> selection;

    public String getVoter() {
        return voter;
    }

    public void setVoter(String voter) {
        this.voter = voter;
    }

    public List<ArrayList> getSelection() {
        return selection;
    }

    public void setSelection(List<ArrayList> selection) {
        this.selection = selection;
    }
}
