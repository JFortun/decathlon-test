package com.example.demo.dto.in;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@Builder
@JsonDeserialize(builder = StockShoe.StockShoeBuilder.class)
public class StockShoe {

    @NotBlank(message = "Color is mandatory")
    String color;

    @NotNull(message = "Size is mandatory")
    Integer size;

    @NotNull(message = "Quantity is mandatory")
    @Max(value = 30, message = "Quantity must be between 0 and 30")
    @Min(value = 0, message = "Quantity must be between 0 and 30")
    Integer quantity;

    @JsonPOJOBuilder(withPrefix = "")
    public static class StockShoeBuilder {

    }

}
