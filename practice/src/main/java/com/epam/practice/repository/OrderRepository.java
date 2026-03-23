package com.epam.practice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.epam.practice.dto.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderNumber(String orderNumber);
    
    
    @Query("SELECT O FROM Order O WHERE O.status = :status")
    List<Order> findByStatus(@Param("status") String status);
    
    @Query(value = "SELECT O.* FROM orders O WHERE O.total_amount > :amount", nativeQuery = true)
    List<Order> totalNumberGreaterThan(@Param("amount") String amount);
    
    @Modifying  // This annotation is required for update/delete queries
    @Query("UPDATE Order O SET O.status = :status WHERE O.id = :id")
	String updateStatusById(@Param("id") Long id, @Param("status") String status);
}
