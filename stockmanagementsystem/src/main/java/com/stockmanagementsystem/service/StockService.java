package com.stockmanagementsystem.service;

import com.stockmanagementsystem.dto.StockRequest;
import com.stockmanagementsystem.dto.StockDTO;
import com.stockmanagementsystem.dto.StockResponse;

/**
 * @created Barnaba Mutai
 * @created 11/ 09/ 2022 - 10:20 AM
 */
public interface StockService {
    StockDTO createStock(StockRequest stockRequest);
    StockResponse getAllStocks(int pageNo, int pageSize, String sortBy, String sortDir);
    StockDTO getStockById(long id);
    StockDTO updateStock(StockDTO stockDTO, long id);

    void deleteStockById (long id);
    StockDTO getStockByUserId(long userid);
}
