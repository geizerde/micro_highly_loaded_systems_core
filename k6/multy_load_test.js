import { check } from 'k6';
import http from 'k6/http';
import { randomString } from 'https://jslib.k6.io/k6-utils/1.4.0/index.js';
import { Writer, SCHEMA_TYPE_STRING, SchemaRegistry } from 'k6/x/kafka';

const topic = __ENV.KAFKA_TOPIC || 'var01';
const brokers = [__ENV.KAFKA_BROKER || 'hl22.zil:9094'];

const writer = new Writer({
    brokers: brokers,
    topic: topic,
});

const schemaRegistry = new SchemaRegistry(); // используем сериализацию строк

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
        entity: 'PRODUCT',
        operation: 'POST',
        payload: {
            name: randomString(8),
            category: randomString(5),
            manufacturer: randomString(10),
            price: +(Math.random() * (500 - 10) + 10).toFixed(2),
        },
    });
}

export function writeScenario() {
    const msg = generateProductPayload();

    writer.produce({
        messages: [
            {
                key: schemaRegistry.serialize({
                    data: randomString(4),
                    schemaType: SCHEMA_TYPE_STRING,
                }),
                value: schemaRegistry.serialize({
                    data: msg,
                    schemaType: SCHEMA_TYPE_STRING,
                }),
            },
        ],
    });
}

export function readScenario() {
    const res = http.get('http://hl1.zil:8081/orders/total-prices', {
        timeout: '360s',
    });

    check(res, { 'got total prices': r => r.status === 200 });
}