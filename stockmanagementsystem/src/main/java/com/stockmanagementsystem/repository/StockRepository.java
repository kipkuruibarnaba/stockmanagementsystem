package com.stockmanagementsystem.repository;

import com.stockmanagementsystem.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @created Barnaba Mutai
 * @created 11/ 09/ 2022 - 10:20 AM
 */
public interface StockRepository extends JpaRepository <Stock, Long>{
}
