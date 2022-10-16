package com.example.demo.controller;

import com.example.demo.dto.in.StockShoe;
import com.example.demo.dto.out.Stock;
import com.example.demo.facade.StockFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockFacade stockFacade;

    @GetMapping(path = "/all", produces = "application/json")
    public ResponseEntity<Stock> stock(@RequestHeader Integer version) {

        return new ResponseEntity<>(stockFacade.get(version).getStock(), HttpStatus.OK);

    }

    @PatchMapping(path = "/update/batch", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Stock> updateBatch(@Valid @RequestBody List<StockShoe> shoes, @RequestHeader Integer version) {

        return new ResponseEntity<>(stockFacade.get(version).updateStockBatch(shoes), HttpStatus.CREATED);

    }

    @PatchMapping(path = "/update/single", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Stock> updateSingle(@Valid @RequestBody StockShoe shoe, @RequestHeader Integer version) {

        return new ResponseEntity<>(stockFacade.get(version).updateStockSingle(shoe), HttpStatus.CREATED);

    }

}
