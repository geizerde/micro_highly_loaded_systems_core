import http from 'k6/http';
import { check } from 'k6';
import { randomString } from 'https://jslib.k6.io/k6-utils/1.4.0/index.js';

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

function generateProductPayload() {
    return JSON.stringify({
        name: randomString(8),
        category: randomString(5),
        manufacturer: randomString(10),
        price: (Math.random() * (500 - 10) + 10).toFixed(2),
    });
}

export function writeScenario() {
    const payload = generateProductPayload();
    const headers = { 'Content-Type': 'application/json' };
    const res = http.post('http://hl1.zil:8080/products', payload, { headers });
    check(res, { 'created product': r => r.status === 200 || r.status === 201 });
}

export function readScenario() {
    const res = http.get('http://hl1.zil:8081/orders/total-prices');
    check(res, { 'got total prices': r => r.status === 200 });
}