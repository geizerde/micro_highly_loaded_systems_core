import uuid
import random
from datetime import datetime, timedelta
import os

def generate_sql(filename, num_products=10, num_customers=5, num_orders=8, num_order_items=20):
    payment_statuses = ["PENDING", "PAID", "FAILED"]

    os.makedirs(os.path.dirname(filename), exist_ok=True)

    with open(filename, 'w', encoding='utf-8') as f:
        f.write("""
        BEGIN TRANSACTION;

        CREATE SEQUENCE seq_product START WITH 1 INCREMENT BY 1;
        CREATE SEQUENCE seq_customer START WITH 1 INCREMENT BY 1;
        CREATE SEQUENCE seq_orders START WITH 1 INCREMENT BY 1;
        CREATE SEQUENCE seq_order_item START WITH 1 INCREMENT BY 1;

        CREATE TABLE t_product (
            id BIGINT PRIMARY KEY DEFAULT nextval('seq_product'),
            identifier UUID UNIQUE DEFAULT gen_random_uuid(),
            name VARCHAR(255) NOT NULL,
            category VARCHAR(255) NOT NULL,
            manufacturer VARCHAR(255) NOT NULL,
            price DECIMAL(10, 2) NOT NULL
        );

        CREATE TABLE t_customer (
            id BIGINT PRIMARY KEY DEFAULT nextval('seq_customer'),
            identifier UUID UNIQUE DEFAULT gen_random_uuid(),
            full_name VARCHAR(255) NOT NULL,
            email VARCHAR(255) NOT NULL UNIQUE,
            phone VARCHAR(20) NOT NULL UNIQUE,
            registration_date DATE NOT NULL
        );

        CREATE TABLE t_orders (
            id BIGINT PRIMARY KEY DEFAULT nextval('seq_orders'),
            identifier UUID UNIQUE DEFAULT gen_random_uuid(),
            customer_id BIGINT NOT NULL,
            order_date TIMESTAMP NOT NULL DEFAULT now(),
            payment_status VARCHAR(255) NOT NULL,
            FOREIGN KEY (customer_id) REFERENCES t_customer (id) ON DELETE CASCADE
        );

        CREATE TABLE t_order_item (
            id BIGINT PRIMARY KEY DEFAULT nextval('seq_order_item'),
            identifier UUID UNIQUE DEFAULT gen_random_uuid(),
            order_id BIGINT NOT NULL,
            product_id BIGINT NOT NULL,
            quantity INT NOT NULL CHECK (quantity > 0),
            FOREIGN KEY (order_id) REFERENCES t_orders (id) ON DELETE CASCADE,
            FOREIGN KEY (product_id) REFERENCES t_product (id) ON DELETE CASCADE
        );

        COMMIT;
        """)

        f.write("\nBEGIN TRANSACTION;\n")

        for i in range(1, num_products + 1):
            f.write(
                f"INSERT INTO t_product (identifier, name, category, manufacturer, price) VALUES ('{uuid.uuid4()}', 'Product_{i}', 'Category_{i % 3}', 'Manufacturer_{i % 2}', {random.uniform(10, 500):.2f});\n")

        for i in range(1, num_customers + 1):
            f.write(
                f"INSERT INTO t_customer (identifier, full_name, email, phone, registration_date) VALUES ('{uuid.uuid4()}', 'Customer_{i}', 'customer{i}@example.com', '+123456789{i}', '{(datetime.now() - timedelta(days=random.randint(30, 365))).date()}');\n")

        for i in range(1, num_orders + 1):
            customer_id = random.randint(1, num_customers)
            payment_status = random.choice(payment_statuses)
            f.write(
                f"INSERT INTO t_orders (identifier, customer_id, order_date, payment_status) VALUES ('{uuid.uuid4()}', {customer_id}, '{datetime.now() - timedelta(days=random.randint(1, 30))}', '{payment_status}');\n")

        for i in range(1, num_order_items + 1):
            order_id = random.randint(1, num_orders)
            product_id = random.randint(1, num_products)
            quantity = random.randint(1, 10)
            f.write(
                f"INSERT INTO t_order_item (identifier, order_id, product_id, quantity) VALUES ('{uuid.uuid4()}', {order_id}, {product_id}, {quantity});\n")

        f.write("COMMIT;\n")

generate_sql("scripts/generated_script.sql")