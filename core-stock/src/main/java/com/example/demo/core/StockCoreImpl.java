package com.example.demo.core;

import com.example.demo.core.shoe.ShoeEntity;
import com.example.demo.core.shoe.ShoeMapper;
import com.example.demo.core.shoe.ShoeRepository;
import com.example.demo.core.stock.StockEntity;
import com.example.demo.core.stock.StockEntityState;
import com.example.demo.core.stock.StockMapper;
import com.example.demo.core.stock.StockRepository;
import com.example.demo.dto.out.Stock;
import com.example.demo.dto.out.StockShoe;
import com.example.demo.dto.out.StockShoes;
import org.springframework.beans.factory.annotation.Autowired;

@Implementation(version = 1)
public class StockCoreImpl extends AbstractStockCore {

    @Autowired
    StockRepository stockRepository;

    @Autowired
    ShoeRepository shoeRepository;

    @Override
    public void initializeStock() {
        var stock = new StockEntity(StockEntityState.EMPTY, null);
        stockRepository.saveAndFlush(stock);
    }

    @Override
    public Stock getStock() {
        var stock = stockRepository.findById(1).get();
        var shoeTest1 = new ShoeEntity("BLUE", 40, 10, stock);
        shoeRepository.save(shoeTest1);
        updateStock(stock);
        var stockEntity = stockRepository.findById(1).get();

        return StockMapper.mapStockEntityToDTO(stockEntity);
    }

    @Override
    public Stock updateStockBatch(StockShoes shoes) {
        var actualStock = stockRepository.findById(1).get();
        shoeRepository.deleteAll();
        shoeRepository.saveAll(ShoeMapper.mapShoesDTOToEntity(shoes, actualStock));
        updateStock(actualStock);
        var updatedStock = stockRepository.findById(1).get();

        return StockMapper.mapStockEntityToDTO(updatedStock);
    }

    @Override
    public Stock updateStockSingle(StockShoe shoe) {
        var actualStock = stockRepository.findById(1).get();
        shoeRepository.save(ShoeMapper.mapShoeDTOToEntity(shoe, actualStock));
        updateStock(actualStock);
        var updatedStock = stockRepository.findById(1).get();

        return StockMapper.mapStockEntityToDTO(updatedStock);
    }

    private void updateStock(StockEntity stock) {
        stock.setShoes(shoeRepository.findAll());
        stockRepository.saveAndFlush(stock);
    }

}
