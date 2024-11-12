package com.example.evoLog_TP3.logging.profile;

import java.util.List;

public class WriteProfile extends UserProfile {
    private int writeOperations;

    public WriteProfile(int writeOperations, String userId, List<String> actions) {
        super(userId,actions);
        this.writeOperations = writeOperations;
    }

    public WriteProfile( String userId) {
        super(userId);
    }
    public int getWriteOperations() {
        return writeOperations;
    }

    public void setWriteOperations(int writeOperations) {
        this.writeOperations = writeOperations;
    }

    public void incrementWriteOperations() {
        this.writeOperations++;
    }
}