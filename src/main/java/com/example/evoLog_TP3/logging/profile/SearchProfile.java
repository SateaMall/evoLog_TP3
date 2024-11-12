package com.example.evoLog_TP3.logging.profile;

import java.util.List;

public class SearchProfile extends UserProfile {
    private int searchOperations;

    public SearchProfile(int searchOperations, String userId, List<String> actions) {
        super(userId,actions);
        this.searchOperations = searchOperations;
    }

    public SearchProfile( String userId) {
        super(userId);
    }

    public int getSearchOperations() {
        return searchOperations;
    }

    public void setSearchOperations(int searchOperations) {
        this.searchOperations = searchOperations;
    }

    public void incrementSearchOperations() {
        this.searchOperations++;
    }

}