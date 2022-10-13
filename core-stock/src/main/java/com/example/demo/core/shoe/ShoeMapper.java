package com.example.demo.core.shoe;

import com.example.demo.core.stock.StockEntity;
import com.example.demo.dto.out.StockShoe;

import java.util.ArrayList;
import java.util.List;

public class ShoeMapper {

    public static List<StockShoe> mapShoesEntityToDTO(List<ShoeEntity> shoeEntities) {
        var shoes = new ArrayList<StockShoe>();
        shoeEntities.forEach(s -> shoes.add(StockShoe.builder()
                .color(s.getColor())
                .size(s.getSize())
                .quantity(s.getQuantity())
                .build()));

        return shoes;
    }

    public static List<ShoeEntity> mapShoesDTOToEntity(List<StockShoe> shoesDto, StockEntity stockEntity) {
        var shoes = new ArrayList<ShoeEntity>();
        shoesDto.forEach(s -> shoes.add(new ShoeEntity(s.getColor(), s.getSize(), s.getQuantity(), stockEntity)));

        return shoes;
    }

    public static ShoeEntity mapShoeDTOToEntity(StockShoe shoeDto, StockEntity stockEntity) {

        return new ShoeEntity(shoeDto.getColor(), shoeDto.getSize(), shoeDto.getQuantity(), stockEntity);
    }

}
