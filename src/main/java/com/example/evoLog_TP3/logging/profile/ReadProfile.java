package com.example.evoLog_TP3.logging.profile;

import java.util.List;

public class ReadProfile extends UserProfile{
    private int readOperations;

    public ReadProfile(int readOperations, String userId, List<String> actions) {
        super(userId,actions);
        this.readOperations = readOperations;
    }

    public ReadProfile( String userId) {
        super(userId);
    }

    public int getReadOperations() {
        return readOperations;
    }

    public void setReadOperations(int readOperations) {
        this.readOperations = readOperations;
    }
    public void incrementReadOperations() {
        this.readOperations++;
    }
}
