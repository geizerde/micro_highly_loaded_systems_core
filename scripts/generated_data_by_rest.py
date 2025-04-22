import argparse
import random
from faker import Faker
import requests
from datetime import datetime

fake = Faker()

BASE_URL = "http://localhost:8080"

def clear_all():
    requests.delete(f"{BASE_URL}/clear")

def generate_customer():
    return {
        "fullName": fake.name(),
        "email": fake.unique.email(),
        "phone": fake.unique.numerify("+7-9##-###-##-##"),
    }

def generate_product():
    return {
        "name": fake.word(),
        "category": fake.word(),
        "manufacturer": fake.company(),
        "price": round(random.uniform(10.0, 500.0), 2)
    }

def generate_order(customer_id, product_ids):
    items = []
    for _ in range(random.randint(1, 3)):
        items.append({
            "product": {
                "id": random.choice(product_ids)
            },
            "quantity": random.randint(1, 5)
        })

    return {
        "orderItems": items,
        "customer": {
            "id": customer_id
        },
        "orderDate": datetime.now().strftime("%Y-%m-%d %H:%M:%S"),
        "paymentStatus": random.choice(["PAID", "PENDING", "FAILED"])
    }

def populate(endpoint, count):
    if endpoint == "customers":
        for _ in range(count):
            data = generate_customer()
            requests.post(f"{BASE_URL}/customers", json=data)

    elif endpoint == "products":
        for _ in range(count):
            data = generate_product()
            requests.post(f"{BASE_URL}/products", json=data)

    elif endpoint == "orders":
        customers = []
        products = []

        for _ in range(count // 2):
            customer_data = generate_customer()
            res = requests.post(f"{BASE_URL}/customers", json=customer_data)
            if res.ok:
                customers.append(res.json()["id"])

            product_data = generate_product()
            res = requests.post(f"{BASE_URL}/products", json=product_data)
            if res.ok:
                products.append(res.json()["id"])

        for _ in range(count):
            order_data = generate_order(random.choice(customers), products)
            requests.post(f"{BASE_URL}/orders", json=order_data)

    else:
        print(f"Unknown endpoint: {endpoint}")

def main():
    parser = argparse.ArgumentParser(description="Test data generator")
    parser.add_argument("--count", type=int, default=500, help="Number of objects to create")
    parser.add_argument("--endpoint", type=str, required=True,
                        choices=["customers", "products", "orders"],
                        help="API endpoint to populate")
    args = parser.parse_args()

    clear_all()
    populate(args.endpoint, args.count)

if __name__ == "__main__":
    main()
