package com.example.demo.core;

import com.example.demo.core.shoe.ShoeMapper;
import com.example.demo.core.shoe.ShoeRepository;
import com.example.demo.core.stock.StockEntity;
import com.example.demo.core.stock.StockEntityState;
import com.example.demo.core.stock.StockMapper;
import com.example.demo.core.stock.StockRepository;
import com.example.demo.dto.out.Stock;
import com.example.demo.dto.out.StockShoe;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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

        return StockMapper.mapStockEntityToDTO(stock);
    }

    @Override
    public Stock updateStockBatch(List<StockShoe> shoes) {
        var actualStock = stockRepository.findById(1).get();
        shoeRepository.deleteAll();
        shoeRepository.saveAll(ShoeMapper.mapShoesDTOToEntity(shoes, actualStock));
        actualStock.setState(getStockState(ShoeMapper.mapShoesEntityToDTO(shoeRepository.findAll())));
        updateStock(actualStock);
        var updatedStock = stockRepository.findById(1).get();

        return StockMapper.mapStockEntityToDTO(updatedStock);
    }

    @Override
    public Stock updateStockSingle(StockShoe shoe) {
        var actualStock = stockRepository.findById(1).get();
        shoeRepository.save(ShoeMapper.mapShoeDTOToEntity(shoe, actualStock));
        actualStock.setState(getStockState(ShoeMapper.mapShoesEntityToDTO(shoeRepository.findAll())));
        updateStock(actualStock);
        var updatedStock = stockRepository.findById(1).get();

        return StockMapper.mapStockEntityToDTO(updatedStock);
    }

    private void updateStock(StockEntity stock) {
        stock.setShoes(shoeRepository.findAll());
        stockRepository.saveAndFlush(stock);
    }

    private StockEntityState getStockState(List<StockShoe> shoes) {
        var count = 0;
        for (StockShoe s : shoes) {
            count = count + s.getQuantity();
        }
        if (count == 0) {
            return StockEntityState.EMPTY;
        } else if (count > 0 && count < 30) {
            return StockEntityState.SOME;
        } else {
            return StockEntityState.FULL;
        }

    }

}
