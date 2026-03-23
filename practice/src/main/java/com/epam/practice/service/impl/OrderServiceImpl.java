package com.epam.practice.service.impl;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.epam.practice.dto.Order;
import com.epam.practice.dto.OrderItem;
import com.epam.practice.repository.OrderRepository;
import com.epam.practice.service.OrderService;

/*
 * One to many relationship: A one-to-many relationship is a type of association where one entity in a table can be associated with multiple entities in another table, 
 * but each entity in the second table is associated with only one entity in the first table.
 * ex: An order can have multiple order items, but each order item belongs to only one order.
 * here orderitems is owning side of the relationship because it contains the foreign key to the order table. 
 * We will use @JoinColumn annotation in the orderitems class to specify the foreign key column.
 * on the owning side we use @ManyToOne annotation to specify the relationship, and on the non-owning side we use @OneToMany annotation with mappedBy attribute to specify the relationship.
 * In a one-to-many relationship, the entity on the "one" side is often referred to as the "parent" entity, while the entities on the "many" side are referred to as "child" entities.
 */

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private TransactionalServiceExample transactionalService; // self-injection to allow calling methods with transactional annotations within the same class
	//but it is better to create new service class and move createOrder2 method to that class and inject that service class in this class and call createOrder2 method from that service class, because self-injection can lead to circular dependencies and it is not recommended.

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    /*
     * This is example of  required propagation, which means that if there is an existing transaction, 
     * the method will join that transaction. If there is no existing transaction, a new one will be created.
     * createOrder method calls createOrder2 method, both methods are annotated with @Transactional(propagation= Propagation.REQUIRED) and they are in same class, so they will share the same transaction. 
     * If any of the methods throws an exception, the entire transaction will be rolled back, and no changes will be persisted to the database.
     */

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public Order createOrder(Order order) {
        // ensure bidirectional relationship is set
        if (order.getOrderItems() != null) {
            for (OrderItem item : order.getOrderItems()) {
                item.setOrder(order);
            }
        }
        
        Order o = orderRepository.save(order);
       // just to demonstrate that we are still in the same transaction
        //transactionalService.createOrder2(order); //testing both the methods are in same transaction, either both will succeed or both will fail, because they are in same transaction
        
        
        return o;
    }
    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public Order createOrder2(Order order) { 
    /* This method is in same class, so it will share the same transaction as createOrder method, 
     		because both methods are annotated with @Transactional(propagation= Propagation.REQUIRED) and they are in same class, so they will share the same transaction.
     * Imp thing notice here is it wont bypass the proxy and it will execute the transactional code, 
    		because we are calling this method from another method which is also annotated with @Transactional(propagation= Propagation.REQUIRED) and they are in same class, so they will share the same transaction.
    */
         
         //ensure bidirectional relationship is set
        if (order.getOrderItems() != null) {
            for (OrderItem item : order.getOrderItems()) {
                item.setOrder(order);
            }
        }
        return orderRepository.save(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrder(Long id, Order updated) {
        return orderRepository.findById(id).map(existing -> {
            existing.setOrderNumber(updated.getOrderNumber());
            existing.setOrderDate(updated.getOrderDate());
            existing.setStatus(updated.getStatus());
            existing.setTotalAmount(updated.getTotalAmount());
            // replace items
            existing.setOrderItems(updated.getOrderItems());
            return orderRepository.save(existing);
        }).orElseThrow(() -> new IllegalArgumentException("Order not found: " + id));
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Order> getOrdersByStatus(String status) {
		return orderRepository.findByStatus(status);
	}
    
    @Override
    @Transactional(readOnly = true)
    public List<Order> totalNumberGreaterThan(String totalAmount) {
    			return orderRepository.totalNumberGreaterThan(totalAmount);
    }
    
    @Override
    @Transactional
    public String updateStatusById(Long id, String status) {
    	Optional<Order> orderOpt = orderRepository.findById(id);
		if (orderOpt.isPresent()) {
			Order order = orderOpt.get();
			order.setStatus(status);
			orderRepository.save(order);
			return "Order status updated successfully.";
		} else {
			return "Order not found with id: " + id;
		}
    }
}
