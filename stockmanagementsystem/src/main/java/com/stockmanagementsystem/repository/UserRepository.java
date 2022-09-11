package com.stockmanagementsystem.repository;

import com.stockmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @created Barnaba Mutai
 * @created 11/ 09/ 2022 - 10:18 AM
 */
public interface UserRepository extends JpaRepository<User,Long> {
}
