package com.example.demo.core.stock;

import com.example.demo.core.shoe.ShoeMapper;
import com.example.demo.dto.out.Stock;
import com.example.demo.dto.out.StockState;

public class StockMapper {

    public static Stock mapStockEntityToDTO(StockEntity stockEntity) {
        var stockDTO = Stock.builder()
                .state(mapStockStateEntityToDTO(stockEntity.getState()))
                .shoes(ShoeMapper.mapShoesEntityToDTO(stockEntity.getShoes()))
                .build();
        return stockDTO;
    }

    public static StockState mapStockStateEntityToDTO(StockEntityState stockEntityState) {
        return StockState.valueOf(stockEntityState.name());
    }

}
