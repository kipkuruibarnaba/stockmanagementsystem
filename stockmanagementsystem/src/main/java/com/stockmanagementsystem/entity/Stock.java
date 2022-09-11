package com.stockmanagementsystem.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @created Barnaba Mutai
 * @created 11/ 09/ 2022 - 9:55 AM
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "stocks"
)
public class Stock {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private Long userid;
    private String type;
    private String symbol;
    private String shares;
    private String price;
    private String timestamp;
}

