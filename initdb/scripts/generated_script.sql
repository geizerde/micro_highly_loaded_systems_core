
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
        
BEGIN TRANSACTION;
INSERT INTO t_product (identifier, name, category, manufacturer, price) VALUES ('8af2f102-5cb3-48a8-b00e-b82ff93c4385', 'Product_1', 'Category_1', 'Manufacturer_1', 469.71);
INSERT INTO t_product (identifier, name, category, manufacturer, price) VALUES ('d6279b0e-0114-42d6-9e87-53e835b9bb9c', 'Product_2', 'Category_2', 'Manufacturer_0', 283.47);
INSERT INTO t_product (identifier, name, category, manufacturer, price) VALUES ('a59f7d39-b6fe-418f-bacc-0edcf74a5d92', 'Product_3', 'Category_0', 'Manufacturer_1', 333.42);
INSERT INTO t_product (identifier, name, category, manufacturer, price) VALUES ('d9c775e0-ed7f-4ba2-81f2-696819725121', 'Product_4', 'Category_1', 'Manufacturer_0', 157.46);
INSERT INTO t_product (identifier, name, category, manufacturer, price) VALUES ('83a38828-b0fa-41fa-82e0-a67183633e3e', 'Product_5', 'Category_2', 'Manufacturer_1', 52.43);
INSERT INTO t_product (identifier, name, category, manufacturer, price) VALUES ('17237a8e-306a-4f5f-8e0d-0a80f13a6f18', 'Product_6', 'Category_0', 'Manufacturer_0', 17.57);
INSERT INTO t_product (identifier, name, category, manufacturer, price) VALUES ('eaf76ea3-f8c9-4c43-b168-b03b637012ae', 'Product_7', 'Category_1', 'Manufacturer_1', 33.97);
INSERT INTO t_product (identifier, name, category, manufacturer, price) VALUES ('c44714e5-88ee-496b-be48-e8414d0a2025', 'Product_8', 'Category_2', 'Manufacturer_0', 235.86);
INSERT INTO t_product (identifier, name, category, manufacturer, price) VALUES ('556d120f-b1c6-4c38-bdda-fe72936e93b2', 'Product_9', 'Category_0', 'Manufacturer_1', 53.13);
INSERT INTO t_product (identifier, name, category, manufacturer, price) VALUES ('6a179ab1-81bb-4481-adcd-b4a2d4c92530', 'Product_10', 'Category_1', 'Manufacturer_0', 31.90);
INSERT INTO t_customer (identifier, full_name, email, phone, registration_date) VALUES ('dabf33a0-0941-484a-89a9-ca44d9303dbc', 'Customer_1', 'customer1@example.com', '+1234567891', '2024-10-21');
INSERT INTO t_customer (identifier, full_name, email, phone, registration_date) VALUES ('20e555cb-a822-42b8-8775-f253329d7e30', 'Customer_2', 'customer2@example.com', '+1234567892', '2024-07-25');
INSERT INTO t_customer (identifier, full_name, email, phone, registration_date) VALUES ('36fd90b1-bf6c-45dd-9c35-9d060ad4c013', 'Customer_3', 'customer3@example.com', '+1234567893', '2024-09-14');
INSERT INTO t_customer (identifier, full_name, email, phone, registration_date) VALUES ('9a7b77e8-6dff-4fe9-8116-d18fc5faa636', 'Customer_4', 'customer4@example.com', '+1234567894', '2024-06-15');
INSERT INTO t_customer (identifier, full_name, email, phone, registration_date) VALUES ('b63884e9-ef21-4cd6-ad5a-6ced97913df9', 'Customer_5', 'customer5@example.com', '+1234567895', '2024-10-22');
INSERT INTO t_orders (identifier, customer_id, order_date, payment_status) VALUES ('9dd12120-5048-44cd-b8d3-4c776d64263f', 2, '2025-02-28 22:05:50.375862', 'PENDING');
INSERT INTO t_orders (identifier, customer_id, order_date, payment_status) VALUES ('25683849-b505-455b-b93c-0b6eac23b47e', 1, '2025-02-27 22:05:50.375884', 'PENDING');
INSERT INTO t_orders (identifier, customer_id, order_date, payment_status) VALUES ('6dce3175-9266-4ed7-b06f-4ddcc0925a36', 3, '2025-03-14 22:05:50.375900', 'PAID');
INSERT INTO t_orders (identifier, customer_id, order_date, payment_status) VALUES ('98f0e475-c908-4cb1-b438-32b2bca67032', 2, '2025-03-12 22:05:50.375914', 'PAID');
INSERT INTO t_orders (identifier, customer_id, order_date, payment_status) VALUES ('7b1a7650-a546-4766-bb0e-3308ae7d2982', 3, '2025-02-25 22:05:50.375928', 'PENDING');
INSERT INTO t_orders (identifier, customer_id, order_date, payment_status) VALUES ('6f55a769-8cac-4d7c-b2f1-b8a66a12f2e7', 3, '2025-03-05 22:05:50.375941', 'PAID');
INSERT INTO t_orders (identifier, customer_id, order_date, payment_status) VALUES ('e36eb9b7-9b2e-4628-99f6-34b6dd2571b2', 4, '2025-03-11 22:05:50.375956', 'FAILED');
INSERT INTO t_orders (identifier, customer_id, order_date, payment_status) VALUES ('1a8c7aac-3139-4cec-b501-2e2540bc9594', 1, '2025-03-01 22:05:50.375970', 'PENDING');
INSERT INTO t_order_item (identifier, order_id, product_id, quantity) VALUES ('7fdf2e45-2f46-4efd-a440-9fa21ce202e5', 6, 9, 9);
INSERT INTO t_order_item (identifier, order_id, product_id, quantity) VALUES ('946d972c-2174-42f5-b2af-7a183fc996bf', 1, 1, 4);
INSERT INTO t_order_item (identifier, order_id, product_id, quantity) VALUES ('aab10037-8ba1-4f14-9f77-a86bd9664dcc', 7, 9, 9);
INSERT INTO t_order_item (identifier, order_id, product_id, quantity) VALUES ('b724aa38-5110-4028-a88f-88b00ff2f3ee', 5, 10, 1);
INSERT INTO t_order_item (identifier, order_id, product_id, quantity) VALUES ('c119ccf3-5df5-4252-86f8-a834a1a2d85e', 6, 8, 8);
INSERT INTO t_order_item (identifier, order_id, product_id, quantity) VALUES ('dfa435a5-ee03-4eff-b016-ede58901cad6', 7, 10, 2);
INSERT INTO t_order_item (identifier, order_id, product_id, quantity) VALUES ('6c568bfa-ecce-4683-a394-f5430eed6b77', 7, 5, 1);
INSERT INTO t_order_item (identifier, order_id, product_id, quantity) VALUES ('1833c9f4-2849-4eaf-8815-dbd6e20f3a82', 1, 1, 8);
INSERT INTO t_order_item (identifier, order_id, product_id, quantity) VALUES ('4727c019-f658-471d-87b7-a7a1abadefbb', 6, 8, 3);
INSERT INTO t_order_item (identifier, order_id, product_id, quantity) VALUES ('fb4c120c-8814-4177-ae2f-505081f2d311', 3, 4, 1);
INSERT INTO t_order_item (identifier, order_id, product_id, quantity) VALUES ('33baff74-72b0-41c6-baeb-4d775eaf511a', 3, 4, 7);
INSERT INTO t_order_item (identifier, order_id, product_id, quantity) VALUES ('5b4eac49-c45a-470e-aa38-87800c653446', 2, 1, 8);
INSERT INTO t_order_item (identifier, order_id, product_id, quantity) VALUES ('e1477bec-c8e0-4e1f-b98c-5e256dedbdd1', 5, 3, 7);
INSERT INTO t_order_item (identifier, order_id, product_id, quantity) VALUES ('bf713fee-f19a-46f4-a1fb-d1a5bc8f0dc1', 4, 1, 7);
INSERT INTO t_order_item (identifier, order_id, product_id, quantity) VALUES ('cc860d3a-f301-4bb7-8372-700a55cd8a7c', 3, 4, 9);
INSERT INTO t_order_item (identifier, order_id, product_id, quantity) VALUES ('da1c0bb2-8cc3-49a5-bec3-a47ef12e3682', 6, 5, 10);
INSERT INTO t_order_item (identifier, order_id, product_id, quantity) VALUES ('8fe49c8d-57fb-4ed5-8532-08ebb67d0dcf', 5, 8, 5);
INSERT INTO t_order_item (identifier, order_id, product_id, quantity) VALUES ('227077b3-494f-4ee5-837d-a75a94454214', 1, 5, 5);
INSERT INTO t_order_item (identifier, order_id, product_id, quantity) VALUES ('140bbb81-1fca-4cf3-bf0a-345217d972e8', 4, 1, 8);
INSERT INTO t_order_item (identifier, order_id, product_id, quantity) VALUES ('de020874-6c77-4fca-8b56-1f14ddf090ad', 5, 8, 9);
COMMIT;
