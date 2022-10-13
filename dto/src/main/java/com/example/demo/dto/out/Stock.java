package com.example.demo.dto.out;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = Stock.StockBuilder.class)
public class Stock {

    StockState state;
    StockShoes shoes;

    @JsonPOJOBuilder(withPrefix = "")
    public static class StockBuilder {

    }

}
