package com.example.demo;

import com.example.demo.controller.StockController;
import com.example.demo.core.StockCoreImpl;
import com.example.demo.core.exception.StockException;
import com.example.demo.core.shoe.ShoeMapper;
import com.example.demo.core.stock.StockEntity;
import com.example.demo.core.stock.StockEntityState;
import com.example.demo.core.stock.StockRepository;
import com.example.demo.dto.in.StockShoe;
import com.example.demo.facade.StockFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MultipleCoreImplemSampleApplicationTest {

    @Autowired
    StockController stockController;

    @Autowired
    StockFacade stockFacade;

    @Autowired
    StockCoreImpl stockCore;

    @Autowired
    StockRepository stockRepository;

    private static final Integer version = 1;
    private static final StockEntity stockEntity = new StockEntity(StockEntityState.EMPTY, null);
    private static StockShoe shoeDTO;
    private static StockShoe shoeDTO31;

    @BeforeAll
    public static void initialize() {
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
    @DisplayName("Test contextLoadsController")
    public void contextLoadsController() {
        assertThat(stockController).isNotNull();
    }

    @Test
    @DisplayName("Test contextLoadsFacade")
    public void contextLoadsFacade() {
        assertThat(stockFacade).isNotNull();
    }

    @Test
    @DisplayName("Test contextLoadsCore")
    public void contextLoadsCore() {
        assertThat(stockCore).isNotNull();
    }

    @Test
    @DisplayName("Test contextLoadsRepository")
    public void contextLoadsRepository() {
        assertThat(stockRepository).isNotNull();
    }

    @Test
    @DisplayName("Test getStockAfterBatchUpdate")
    public void getStockAfterBatchUpdate() {
        stockEntity.setShoes(Collections.singletonList(ShoeMapper.mapShoeDTOToEntity(shoeDTO, stockEntity)));
        var stockUpdated = stockFacade.get(version).updateStockBatch(Collections.singletonList(shoeDTO));
        var stock = stockFacade.get(version).getStock();

        Assertions.assertEquals(stock, stockUpdated);
    }

    @Test
    @DisplayName("Test getStockSingleBatchUpdate")
    public void getStockSingleBatchUpdate() {
        stockEntity.setShoes(Collections.singletonList(ShoeMapper.mapShoeDTOToEntity(shoeDTO, stockEntity)));
        var stockUpdated = stockFacade.get(version).updateStockSingle(shoeDTO);
        var stock = stockFacade.get(version).getStock();

        Assertions.assertEquals(stock, stockUpdated);
    }

    @Test
    @DisplayName("Test getStockStateStockException")
    public void getStockStateStockException() {
        Assertions.assertThrows(StockException.class, () -> stockFacade.get(version).updateStockSingle(shoeDTO31));
    }

}
