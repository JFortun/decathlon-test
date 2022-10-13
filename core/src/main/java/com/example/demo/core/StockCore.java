package com.example.demo.core;

import com.example.demo.dto.out.Stock;
import com.example.demo.dto.out.StockShoe;
import com.example.demo.dto.out.StockShoes;

public interface StockCore {

    void initializeStock();

    Stock getStock();

    Stock updateStockBatch(StockShoes shoes);

    Stock updateStockSingle(StockShoe shoe);

}
