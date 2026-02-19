-- =========================
-- DEPARTMENTS
-- =========================

INSERT INTO departments (id, name, location, budget)
VALUES (1, 'HR', 'Blore', 50000);

INSERT INTO departments (id, name, location, budget)
VALUES (2, 'IT', 'Hyd', 200000);

INSERT INTO departments (id, name, location, budget)
VALUES (3, 'Finance', 'Pune', 100000);


-- =========================
-- EMPLOYEES
-- =========================

INSERT INTO employees (id, name, email, salary, hire_date, department_id)
VALUES (1, 'Ramya', 'ramya@company.com', 50000, '2022-01-10', 2);

INSERT INTO employees (id, name, email, salary, hire_date, department_id)
VALUES (2, 'Arjun', 'arjun@company.com', 60000, '2021-05-15', 2);

INSERT INTO employees (id, name, email, salary, hire_date, department_id)
VALUES (3, 'Meena', 'meena@company.com', 45000, '2023-02-20', 1);

INSERT INTO employees (id, name, email, salary, hire_date, department_id)
VALUES (4, 'Kiran', 'kiran@company.com', 70000, '2020-08-01', 2);

INSERT INTO employees (id, name, email, salary, hire_date, department_id)
VALUES (5, 'Sita', 'sita@company.com', 40000, '2022-11-11', 3);




-- =========================
-- ORDERS
-- =========================

INSERT INTO orders (id, order_number, order_date, total_amount, status)
VALUES (1, 'ORD101', CURRENT_TIMESTAMP, 1000, 'COMPLETED');

INSERT INTO orders (id, order_number, order_date, total_amount, status)
VALUES (2, 'ORD102', CURRENT_TIMESTAMP, 500, 'PENDING');


-- =========================
-- ORDER_ITEMS
-- =========================

INSERT INTO order_items (id, product_name, quantity, price, order_id)
VALUES (1, 'Laptop', 1, 800, 1);

INSERT INTO order_items (id, product_name, quantity, price, order_id)
VALUES (2, 'Mouse', 2, 100, 1);

INSERT INTO order_items (id, product_name, quantity, price, order_id)
VALUES (3, 'Keyboard', 1, 500, 2);




-- =========================
-- STUDENTS
-- =========================

INSERT INTO students (id, name, email)
VALUES (1, 'Ramya', 'ramya@mail.com');

INSERT INTO students (id, name, email)
VALUES (2, 'Arjun', 'arjun@mail.com');


-- =========================
-- COURSES
-- =========================

INSERT INTO courses (id, title, code,credits)
VALUES (10, 'Java', 'J101',5);

INSERT INTO courses (id, title, code,credits)
VALUES (20, 'Databases', 'D201',10);


-- =========================
-- ENROLLMENTS (Replacing student_courses)
-- =========================

INSERT INTO enrollments (id, enrollment_date, grade, status, student_id, course_id)
VALUES (100, '2024-01-01', 'A', 'COMPLETED', 1, 10);

INSERT INTO enrollments (id, enrollment_date, grade, status, student_id, course_id)
VALUES (101, '2024-02-01', 'B', 'ACTIVE', 1, 20);

INSERT INTO enrollments (id, enrollment_date, grade, status, student_id, course_id)
VALUES (102, '2024-03-01', 'C', 'ACTIVE', 2, 10);




COMMIT;
