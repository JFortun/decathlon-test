package com.example.demo.dto.out;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
@Builder
@JsonDeserialize(builder = StockShoes.StockShoesBuilder.class)
public class StockShoes {

    List<StockShoe> shoes;

    @JsonPOJOBuilder(withPrefix = "")
    public static class StockShoesBuilder {

    }

}
