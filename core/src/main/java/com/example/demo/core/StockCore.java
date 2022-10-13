package com.example.demo.core;

import com.example.demo.dto.out.Stock;
import com.example.demo.dto.out.StockShoe;

import java.util.List;

public interface StockCore {

    void initializeStock();

    Stock getStock();

    Stock updateStockBatch(List<StockShoe> shoes);

    Stock updateStockSingle(StockShoe shoe);

}
