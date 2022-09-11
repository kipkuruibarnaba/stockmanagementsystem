package com.stockmanagementsystem.dto;

import com.stockmanagementsystem.entity.User;
import lombok.Data;

import java.util.Set;

/**
 * @created Barnaba Mutai
 * @created 11/ 09/ 2022 - 10:14 AM
 */
@Data
public class StockDTO {
    private Long id;
    private String type;
    private String symbol;
    private String shares;
    private String price;
    private String timestamp;
//    private Set<User> user;
    private User user;
}
