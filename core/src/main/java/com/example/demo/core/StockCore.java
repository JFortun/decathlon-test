package com.example.demo.core;

import com.example.demo.dto.in.StockShoe;
import com.example.demo.dto.out.Stock;

import java.util.List;

public interface StockCore {

    void initializeStock();

    Stock getStock();

    Stock updateStockBatch(List<StockShoe> shoes);

    Stock updateStockSingle(StockShoe shoe);

}
