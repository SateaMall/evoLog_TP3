package com.example.evoLog_TP3.logging.profile;

import java.util.List;

public class HighPriceProfile extends UserProfile {
    private int highPriceOperations;

    public HighPriceProfile( String userId) {
        super(userId);
    }

    public int getHighPriceOperations() {
        return highPriceOperations;
    }

    public void setHighPriceOperations(int highPriceOperations) {
        this.highPriceOperations = highPriceOperations;
    }

    public void incrementHighPriceOperations() {
        this.highPriceOperations++;
    }
}