package com.epam.practice.service.impl;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/*
 * This class is a placeholder for demonstrating transaction isolation levels in Spring.
 * how one transaction's changes are visible to other transactions. 
 * Dirty reads, non-repeatable reads, and phantom reads are phenomena that can occur under certain isolation levels.
 * Dirty Read: A transaction reads data that has been modified by another transaction but not yet committed. If the modifying transaction rolls back, the reading transaction has read invalid data.
 * Non-Repeatable Read: A transaction reads the same data twice and gets different results because another transaction has modified and committed the data in between the two reads.
 * Phantom Read: A transaction re-executes a query and finds that the set of rows returned has changed due to another transaction inserting, updating, or deleting rows that match the query criteria.
 * Isolation levels in Spring: 
 * 1. READ_UNCOMMITTED: Allows dirty reads, non-repeatable reads, and phantom reads. Transactions can see uncommitted changes made by other transactions.
 * 2. READ_COMMITTED: Prevents dirty reads but allows non-repeatable reads and phantom reads. Transactions can only see committed changes made by other transactions.
 * 3. REPEATABLE_READ: Prevents dirty reads and non-repeatable reads but allows phantom reads. Transactions can see committed changes made by other transactions, and once a transaction reads a row, it will see the same data for that row for the duration of the transaction.
 * 4. SERIALIZABLE: Prevents dirty reads, non-repeatable reads, and phantom reads. Transactions are completely isolated from each other, and they execute in a way that they appear to be serialized.
 * 
 * mostly we use READ_COMMITTED isolation level in Spring, because it provides a good balance between data integrity and performance.
 * 
 * we cannot use serializable isolation level in Spring, because it can lead to performance issues and it is not supported by all databases.
 */

public class IsolationExample {
	
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public void demonstrateIsolationLevels() {
		// This method is a placeholder for demonstrating isolation levels in Spring.
		// You can create multiple threads to simulate concurrent transactions and observe the effects of different isolation levels on dirty reads, non-repeatable reads, and phantom reads.
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void demonstrateIsolationCommited() {
		}
	
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public void demonstrateIsolationRepeatableRead() {
		}
	
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void demonstrateIsolationSerializable() {
		}
}

/*
 * Summary Table - x MEANS ALLOWS  CORRECT MEANS PREVENTS
					Isolation				DirtyRead	Non-repeatable	Phantom
					READ_UNCOMMITTED		❌				❌				❌ 
					READ_COMMITTED			✔				❌				❌
					REPEATABLE_READ			✔				✔				❌
					SERIALIZABLE			✔				✔				✔
 */		
