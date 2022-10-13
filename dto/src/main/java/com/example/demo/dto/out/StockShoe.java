package com.example.demo.dto.out;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = StockShoe.StockShoeBuilder.class)
public class StockShoe {

    String color;
    Integer size;
    Integer quantity;

    @JsonPOJOBuilder(withPrefix = "")
    public static class StockShoeBuilder {

    }

}
