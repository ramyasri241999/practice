-- SQL script to create `orders` and `order_items` tables
-- MySQL-compatible DDL
-- Path: db/create_orders_tables.sql

-- Drop tables if they exist (order_items first because of FK)
DROP TABLE IF EXISTS `order_items`;
DROP TABLE IF EXISTS `orders`;

-- `orders` table
CREATE TABLE `orders` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `order_number` VARCHAR(100) NOT NULL,
  `order_date` DATETIME NOT NULL,
  `total_amount` DECIMAL(19,4) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_orders_order_number` (`order_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- `order_items` table
CREATE TABLE `order_items` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(255) NOT NULL,
  `quantity` INT NOT NULL,
  `price` DECIMAL(19,4) NOT NULL,
  `order_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_order_items_order_id` (`order_id`),
  CONSTRAINT `fk_order_items_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Example inserts
-- INSERT INTO `orders` (`order_number`, `order_date`, `total_amount`, `status`) VALUES ('ORD-1001', '2026-03-17 10:00:00', 199.99, 'NEW');
-- INSERT INTO `order_items` (`product_name`, `quantity`, `price`, `order_id`) VALUES ('Widget A', 2, 49.99, 1);

-- Notes:
-- Mapping decisions:
-- Java Long -> BIGINT
-- String -> VARCHAR(length) (length chosen conservatively)
-- LocalDateTime -> DATETIME
-- BigDecimal -> DECIMAL(19,4)
-- int -> INT
-- The FK has ON DELETE CASCADE to match cascade = CascadeType.ALL and orphanRemoval = true semantics on parent removal.
