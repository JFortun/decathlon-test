package com.example.demo.core.exception;

public enum StockExceptionType {

    STOCK_LIMIT_REACHED("The stock limit is 30");

    private final String description;

    StockExceptionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
