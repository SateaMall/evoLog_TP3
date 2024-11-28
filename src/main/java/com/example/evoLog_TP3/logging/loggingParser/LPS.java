package com.example.evoLog_TP3.logging.loggingParser;

public class LPS {
    private String timestamp;
    private String userId;
    private String action;

    // Private constructor to enforce the use of the builder
    private LPS(LPSBuilder builder) {
        this.timestamp = builder.timestamp;
        this.userId = builder.userId;
        this.action = builder.action;
    }

    // Getters
    public String getTimestamp() { return timestamp; }
    public String getUserId() { return userId; }
    public String getAction() { return action; }

    // Static inner Builder class
    public static class LPSBuilder {
        private String timestamp;
        private String userId;
        private String action;

        public LPSBuilder setTimestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public LPSBuilder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public LPSBuilder setAction(String action) {
            this.action = action;
            return this;
        }

        public LPS build() {
            return new LPS(this);
        }
    }
}