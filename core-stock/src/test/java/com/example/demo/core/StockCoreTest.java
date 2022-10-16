package com.example.demo.core;

import com.example.demo.core.exception.StockException;
import com.example.demo.core.shoe.ShoeEntity;
import com.example.demo.core.shoe.ShoeMapper;
import com.example.demo.core.stock.StockEntity;
import com.example.demo.core.stock.StockEntityState;
import com.example.demo.core.stock.StockMapper;
import com.example.demo.dto.in.StockShoe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

public class StockCoreTest extends StockCoreImpl {

    private static final StockEntity stockEntity = new StockEntity(StockEntityState.EMPTY, null);
    private static final ShoeEntity shoeEntity = new ShoeEntity("BLUE", 35, 20, stockEntity);
    private static StockShoe shoeDTO;
    private static StockShoe shoeDTO31;
    private static final StockEntityState stockEntityState = StockEntityState.FULL;

    @BeforeAll
    public static void initialize() {
        var shoes = Arrays.asList(
                new ShoeEntity("RED", 35, 5, stockEntity),
                new ShoeEntity("GREEN", 40, 10, stockEntity),
                new ShoeEntity("BLUE", 45, 10, stockEntity));
        stockEntity.setShoes(shoes);
        shoeDTO31 = StockShoe.builder()
                .color("GREEN")
                .size(35)
                .quantity(31)
                .build();
        shoeDTO = StockShoe.builder()
                .color("YELLOW")
                .size(30)
                .quantity(10)
                .build();
    }

    @Test
    @DisplayName("Test mapShoesEntityToDTO")
    public void mapShoesEntityToDTO() {
        var shoes = ShoeMapper.mapShoesEntityToDTO(Collections.singletonList(shoeEntity)).get(0);
        Assertions.assertEquals(shoeEntity.getColor(), shoes.getColor());
        Assertions.assertEquals(shoeEntity.getSize(), shoes.getSize());
        Assertions.assertEquals(shoeEntity.getQuantity(), shoes.getQuantity());
    }

    @Test
    @DisplayName("Test mapShoesDTOToEntity")
    public void mapShoesDTOToEntity() {
        var shoeEntity = ShoeMapper.mapShoesDTOToEntity(Collections.singletonList(shoeDTO), stockEntity).get(0);
        Assertions.assertEquals(shoeDTO.getColor(), shoeEntity.getColor());
        Assertions.assertEquals(shoeDTO.getSize(), shoeEntity.getSize());
        Assertions.assertEquals(shoeDTO.getQuantity(), shoeEntity.getQuantity());
    }

    @Test
    @DisplayName("Test mapShoeDTOToEntity")
    public void mapShoeDTOToEntity() {
        var shoeEntity = ShoeMapper.mapShoeDTOToEntity(shoeDTO, stockEntity);
        Assertions.assertEquals(shoeDTO.getColor(), shoeEntity.getColor());
        Assertions.assertEquals(shoeDTO.getSize(), shoeEntity.getSize());
        Assertions.assertEquals(shoeDTO.getQuantity(), shoeEntity.getQuantity());
    }

    @Test
    @DisplayName("Test mapStockEntityToDto")
    public void mapStockEntityToDto() {
        var stock = StockMapper.mapStockEntityToDTO(stockEntity);
        Assertions.assertEquals(stockEntity.getShoes().get(0).getColor(), stock.getShoes().get(0).getColor());
        Assertions.assertEquals(stockEntity.getShoes().get(0).getSize(), stock.getShoes().get(0).getSize());
        Assertions.assertEquals(stockEntity.getShoes().get(0).getQuantity(), stock.getShoes().get(0).getQuantity());
    }

    @Test
    @DisplayName("Test mapStockStateEntityToDTO")
    public void mapStockStateEntityToDTO() {
        var stock = StockMapper.mapStockStateEntityToDTO(stockEntityState);
        Assertions.assertEquals(stock.name(), stockEntityState.name());
    }

    @Test
    @DisplayName("Test getStockState")
    public void getStockState() {
        Assertions.assertEquals(StockEntityState.SOME, getStockState(ShoeMapper.mapShoesEntityToDTO(stockEntity.getShoes())));
    }

    @Test
    @DisplayName("Test getStockStateStockException")
    public void getStockStateStockException() {
        Assertions.assertThrows(StockException.class, () -> getStockState(Collections.singletonList(shoeDTO31)));
    }
}
