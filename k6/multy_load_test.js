import http from 'k6/http';
import { check } from 'k6';
import { randomItem, randomString } from 'https://jslib.k6.io/k6-utils/1.4.0/index.js';

export const options = {
    scenarios: {
        writers: {
            executor: 'constant-vus',
            vus: __ENV.VUS_WRITE ? parseInt(__ENV.VUS_WRITE) : 1,
            duration: '1m',
            exec: 'writeScenario',
        },
        readers: {
            executor: 'constant-vus',
            vus: __ENV.VUS_READ ? parseInt(__ENV.VUS_READ) : 10,
            duration: '1m',
            exec: 'readScenario',
        },
    },
};

export function setup() {
    const res = http.get('http://localhost:8080/orders');
    let orderIds = [];

    try {
        orderIds = res.json().map(o => o.id);
    } catch (err) {
        console.error('Ошибка разбора /orders:', err);
    }

    return { orderIds };
}

function generateProductPayload() {
    return JSON.stringify({
        name: randomString(8),
        category: randomString(5),
        manufacturer: randomString(10),
        price: (Math.random() * (500 - 10) + 10).toFixed(2),
    });
}

export function writeScenario(data) {
    const payload = generateProductPayload();
    const headers = { 'Content-Type': 'application/json' };
    const res = http.post('http://localhost:8080/products', payload, { headers });
    check(res, { 'created product': r => r.status === 200 || r.status === 201 });
}

export function readScenario(data) {
    const id = randomItem(data.orderIds);
    if (id) {
        const res = http.get(`http://localhost:8080/orders/${id}/total-price`);
        check(res, { 'got order total': r => r.status === 200 });
    }
}