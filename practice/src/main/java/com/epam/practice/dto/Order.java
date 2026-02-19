package com.epam.practice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="order_number", nullable=false, unique = true)
	private String orderNumber;
	
	@Column(name="order_date",nullable = false)
	private LocalDateTime orderDate;
	
	@Column(name="total_amount", nullable = false)
	private BigDecimal totalAmount;
	
	@Column(nullable= false)
	private String status;
	
	@OneToMany(mappedBy="order",cascade= CascadeType.ALL,orphanRemoval=true,fetch=FetchType.LAZY)
	private List<OrderItem> orderItems;

}

@Entity
@Table(name="order_items")
class OrderItem{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="product_name", nullable=false)
	private String productName;
	
	@Column(nullable=false)
	private int quantity;
	
	@Column(nullable=false)
	private BigDecimal price;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name ="order_id",nullable=false)
	private Order order;
}
