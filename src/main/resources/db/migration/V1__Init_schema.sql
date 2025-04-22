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
