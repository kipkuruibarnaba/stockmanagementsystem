package com.stockmanagementsystem.controller;

import com.stockmanagementsystem.dto.StockRequest;
import com.stockmanagementsystem.dto.StockDTO;
import com.stockmanagementsystem.dto.StockResponse;
import com.stockmanagementsystem.service.StockService;
import com.stockmanagementsystem.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @created Barnaba Mutai
 * @created 11/ 09/ 2022 - 4:20 PM
 */
@RestController
@RequestMapping("api/stocks")
public class StockController {
    private StockService stockService;
    public StockController(StockService stockService){
        this.stockService = stockService;
    }
    @PostMapping
    public ResponseEntity<StockDTO> createStock(@RequestBody StockRequest stockRequest){
        return new ResponseEntity<>(stockService.createStock(stockRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public StockResponse getAllStocks(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NO,required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false) String sortDir
    ){
        return stockService.getAllStocks(pageNo,pageSize,sortBy,sortDir);
    }
    @GetMapping("/{id}")
    public ResponseEntity<StockDTO>getStockById(@PathVariable (name="id") long id){
        return ResponseEntity.ok(stockService.getStockById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<StockDTO>updateStock(@RequestBody StockDTO stockDTO, @PathVariable (name="id") long id){
        StockDTO stockResponse = stockService.updateStock(stockDTO ,id);
        return new ResponseEntity<>(stockResponse, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteStock(@PathVariable (name="id") long id){
        stockService.deleteStockById(id);
        return new ResponseEntity<>("Stock deleted successfully. ", HttpStatus.OK);
    }
    @GetMapping("/{userId}/stocks")
    public ResponseEntity<StockDTO> getStockByUserId(@PathVariable(name="userId") long userid){
        return ResponseEntity.ok(stockService.getStockByUserId(userid));
    }
}
