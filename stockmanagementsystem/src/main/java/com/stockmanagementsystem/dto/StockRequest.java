package com.stockmanagementsystem.dto;

import com.stockmanagementsystem.entity.Stock;
import com.stockmanagementsystem.entity.User;
import lombok.Data;

/**
 * @created Barnaba Mutai
 * @created 11/ 09/ 2022 - 10:14 AM
 */
@Data
public class StockRequest {
    private Long id;
    private String type;
    private String symbol;
    private String shares;
    private String price;
    private String timestamp;
    private User user;
}
