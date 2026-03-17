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

/*
 * One to many relationship: A one-to-many relationship is a type of association where one entity in a table can be associated with multiple entities in another table, 
 * but each entity in the second table is associated with only one entity in the first table.
 * ex: An order can have multiple order items, but each order item belongs to only one order.
 * here orderitems is owning side of the relationship because it contains the foreign key to the order table. 
 * We will use @JoinColumn annotation in the orderitems class to specify the foreign key column.
 * on the owning side we use @ManyToOne annotation to specify the relationship, and on the non-owning side we use @OneToMany annotation with mappedBy attribute to specify the relationship.
 * In a one-to-many relationship, the entity on the "one" side is often referred to as the "parent" entity, while the entities on the "many" side are referred to as "child" entities.
 */
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
