package com.example.demo.core;

import com.example.demo.core.exception.StockException;
import com.example.demo.core.exception.StockExceptionType;
import com.example.demo.core.shoe.ShoeMapper;
import com.example.demo.core.shoe.ShoeRepository;
import com.example.demo.core.stock.StockEntity;
import com.example.demo.core.stock.StockEntityState;
import com.example.demo.core.stock.StockMapper;
import com.example.demo.core.stock.StockRepository;
import com.example.demo.dto.in.StockShoe;
import com.example.demo.dto.out.Stock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Implementation(version = 1)
public class StockCoreImpl extends AbstractStockCore {

    private final static Logger LOGGER = Logger.getLogger(StockCoreImpl.class.getSimpleName());

    @Autowired StockRepository stockRepository;

    @Autowired ShoeRepository shoeRepository;

    @Override
    public void initializeStock() {
        var stock = new StockEntity(StockEntityState.EMPTY, null);
        stockRepository.saveAndFlush(stock);
        LOGGER.log(Level.INFO, String.format("Initial Stock entity created with %s state", stock.getState()));
    }

    @Override
    public Stock getStock() {
        var stock = stockRepository.findById(1).get();
        LOGGER.log(Level.INFO, String.format("Stock info retrieved with state %s and shoe data %s", stock.getState(),
                ShoeMapper.mapShoesEntityToDTO(stock.getShoes())));

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
        LOGGER.log(Level.INFO, String.format("Stock updated in batch with shoes %s", shoes));

        return StockMapper.mapStockEntityToDTO(updatedStock);
    }

    @Override
    public Stock updateStockSingle(StockShoe shoe) {
        var actualStock = stockRepository.findById(1).get();
        shoeRepository.save(ShoeMapper.mapShoeDTOToEntity(shoe, actualStock));
        actualStock.setState(getStockState(ShoeMapper.mapShoesEntityToDTO(shoeRepository.findAll())));
        updateStock(actualStock);
        var updatedStock = stockRepository.findById(1).get();
        LOGGER.log(Level.INFO, String.format("Stock updated with a new added shoe: %s", shoe));

        return StockMapper.mapStockEntityToDTO(updatedStock);
    }

    private void updateStock(StockEntity stock) {
        stock.setShoes(shoeRepository.findAll());
        stockRepository.saveAndFlush(stock);
    }

    public StockEntityState getStockState(List<StockShoe> shoes) {
        var count = 0;
        for (StockShoe s : shoes) {
            count = count + s.getQuantity();
        }
        if (count == 0) {
            return StockEntityState.EMPTY;
        } else if (count > 0 && count < 30) {
            return StockEntityState.SOME;
        } else if (count == 30) {
            return StockEntityState.FULL;
        } else {
            LOGGER.log(Level.INFO, "Client tried to exceed the 30 shoes limit");
            throw new StockException(StockExceptionType.STOCK_LIMIT_REACHED.getDescription());
        }

    }

}
