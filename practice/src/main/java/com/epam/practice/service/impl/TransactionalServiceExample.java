package com.epam.practice.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.epam.practice.dto.Order;
import com.epam.practice.dto.OrderItem;
import com.epam.practice.repository.OrderRepository;

@Service
public class TransactionalServiceExample {
	private  OrderRepository orderRepository;
	
	public TransactionalServiceExample(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
	
	/*
	 * From OrderServiceImpl.java we are calling createOrder2() method which is annotated with @Transactional(propagation= Propagation.REQUIRED).
	 * Since createOrder() method is also annotated with @Transactional(propagation= Propagation.REQUIRED), both methods will share the same transaction.
	 * They both will either succeed or fail together. If any of the methods throws an exception, the entire transaction will be rolled back, and no changes will be persisted to the database.
	 * This method uses the proxy mechanism of Spring to manage transactions. When createOrder() is called, Spring creates a proxy for the OrderServiceImpl class.
	 */
	    @Transactional(propagation= Propagation.REQUIRED)
	    public Order createOrder2(Order order) {
	        // ensure bidirectional relationship is set
	    	Order newOrder = new Order();
	    	newOrder.setStatus(order.getStatus()+"_inner_method");
	    	newOrder.setOrderNumber(order.getOrderNumber()+"_inner_method");
	    	newOrder.setOrderItems(order.getOrderItems());
	    	newOrder.setTotalAmount(order.getTotalAmount());
	    	newOrder.setOrderDate(order.getOrderDate());
//	        if (order.getOrderItems() != null) {
//	            for (OrderItem item : order.getOrderItems()) {
//	                item.setOrder(order);
//	            }
//	        }
	    	// either both createOrder and createOrder2 will succeed or both will fail, because they are in same transaction
		    // if checked exception is thrown from this method, then the transaction will not be rolled back and changes from createOrder method will be persisted to the database, because by default Spring only rolls back transactions for unchecked exceptions (RuntimeException and its subclasses) and errors. Checked exceptions do not trigger a rollback unless explicitly configured to do so.
	    	//if unchecked exception is thrown from this method, then the transaction will be rolled back and no changes(even from createOrder method) will be persisted to the database, because both methods are in same transaction and they will either succeed or fail together.
	       // we can still prevent rollback for unchecked exceptions by using @Transactional(propagation= Propagation.REQUIRED, noRollbackFor = RuntimeException.class) on this method, but in that case if unchecked exception is thrown from this method, then the transaction will not be rolled back and changes from createOrder method will be persisted to the database, because we have explicitly configured to prevent rollback for unchecked exceptions.
	    	return orderRepository.save(newOrder);  
	    /*
	     * Final Transaction Behavior Table (REQUIRED)
				Exception Type	Default Behavior	Can Change?	Outer Method	Inner Method	DB Result
				Checked Exception	✅ Commit	✔ rollbackFor	Executes	Executes	✅ BOTH persisted
				Runtime Exception	❌ Rollback	✔ noRollbackFor	Executes	Fails	❌ BOTH rolled back	
	     */
	    
	    }
	    
	    
	    /*
	     * Always creates a new transaction for this method, even if there is an existing transaction. 
	     * If there is an existing transaction, it will be suspended until the new transaction completes. 
	     * This means that the operations in this method will be completely independent of any outer transaction.
	     *  If an exception occurs in this method, only the operations within this method will be rolled back, and it will not affect any outer transaction.
	     */
	    @Transactional(propagation = Propagation.REQUIRES_NEW)
	    public Order createnewOrder(Order order) {
	    	/*
	    	 * Case 1: If createnewOrder() is called from createOrder() method which is annotated with @Transactional(propagation= Propagation.REQUIRED), 
	    	 * then createnewOrder() will execute in a new transaction. 
	    	 * Case 2: If an exception occurs in createnewOrder(), only the operations within createnewOrder() will be rolled back, and it will not affect the transaction of createOrder(). 
	    	 * The changes from createOrder() will still be persisted to the database, while the changes from createnewOrder() will be rolled back.
	    	 * Case 3: If an exception occurs in createOrder() after calling createnewOrder(), the transaction of createOrder() will be rolled back, 
	    	 * but the changes from createnewOrder() will still be persisted to the database, because createnewOrder() runs in its own transaction which is independent of createOrder()'s transaction.
	    	 * 
	    	 */
	    	//throw new RuntimeException("Simulating an exception in createnewOrder method");
			return order;
	    	
	    }
	    
	    /*
	     * Runs inside existing transaction but creates a savepoint.
	     * Runs within a nested transaction if there is an existing transaction. 
	     * If there is no existing transaction, it behaves like REQUIRED and creates a new transaction.
	     */
	    
	    @Transactional(propagation= Propagation.NESTED)
	    public Order createNestedOrder(Order order) {
	    	
	    	/*
	    	 * 
	    	 */
	    	return order;
	    }
}


/*
 * Case 1: Both Success
					Type	DB Result
					REQUIRED	Outer + Inner saved
					REQUIRES_NEW	Outer + Inner saved
					NESTED	Outer + Inner saved
❌ Case 2: Inner FAILS
					Type	Outer	Inner	DB Result
					REQUIRED	❌	❌	Nothing saved
					REQUIRES_NEW	✅	❌	Only outer saved
					NESTED	✅	❌	Only outer saved
❌ Case 3: Outer FAILS (after inner success)
					Type	Outer	Inner	DB Result
					REQUIRED	❌	❌	Nothing saved
					REQUIRES_NEW	❌	✅	Only inner saved
					NESTED	❌	❌	Nothing saved
*/

/*
`	*	we have other propagation types like SUPPORTS, NOT_SUPPORTED, MANDATORY, NEVER, 
		but they are less commonly used and have specific use cases.
		supports: If there is an existing transaction, the method will join that transaction.
		 If there is no existing transaction, the method will execute non-transactionally.
		not_supported: The method will always execute non-transactionally.
		mandatory: The method must run within an existing transaction. If there is no existing transaction, an exception will be thrown.
		never: The method must not run within a transaction. If there is an existing transaction, an exception will be thrown.
*/