package com.example.evoLog_TP3.logging.profile;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserProfile {
    @JsonProperty("userId")
    private String userId;
    

    public UserProfile(String userId) {
        this.userId = userId;
    }

}