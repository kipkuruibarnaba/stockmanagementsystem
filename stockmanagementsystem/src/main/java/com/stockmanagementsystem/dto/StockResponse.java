package com.stockmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @created Barnaba Mutai
 * @created 05/ 09/ 2022 - 12:09 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockResponse {
    private List<StockDTO> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
