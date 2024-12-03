package com.example.evoLog_TP3.logging.loggingParser;

public class LPS {
    private String timestamp;
    private String userId;
    private String action;
    private double price;
    // Private constructor to enforce the use of the builder
    private LPS(LPSBuilder builder) {
        this.timestamp = builder.timestamp;
        this.userId = builder.userId;
        this.action = builder.action;
    }

    private LPS(LPSBuilder builder, double price) {
        this.timestamp = builder.timestamp;
        this.userId = builder.userId;
        this.action = builder.action;
        this.price = builder.price;
    }



    // Getters
    public String getTimestamp() { return timestamp; }
    public String getUserId() { return userId; }
    public String getAction() { return action; }

    @Override
    public String toString() {
        if(price==0.0){
            return "LPS{" +
                    "timestamp='" + timestamp + '\'' +
                    ", userId='" + userId + '\'' +
                    ", action='" + action + '\'' +
                    '}';
        }

        return "LPS{" +
                "timestamp='" + timestamp + '\'' +
                ", userId='" + userId + '\'' +
                ", action='" + action + '\'' +
                ", price='" + price + '\''+
                '}';
    }

    public double getPrice() {
        return price;
    }

    // Static inner Builder class
    public static class LPSBuilder {
        private String timestamp;
        private String userId;
        private String action;
        private double price;

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

        public LPSBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public LPS build() {
            return new LPS(this);
        }
        public LPS buildPrice() {
            return new LPS(this, price);
        }
    }
}