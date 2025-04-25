import { check } from 'k6';
import { randomString } from 'https://jslib.k6.io/k6-utils/1.4.0/index.js';
import kafka from 'k6/x/kafka';

const producer = new kafka.Producer({
    brokers: [__ENV.KAFKA_BROKER || 'hl22.zil:9094'],
    clientId: 'k6-producer',
});

const topic = __ENV.KAFKA_TOPIC || 'var01';

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
    const message = generateProductPayload();
    try {
        producer.produce({
            topic: topic,
            messages: [{ value: message }],
        });
    } catch (err) {
        console.error(`Kafka send error: ${err}`);
    }
}

export function readScenario() {
    const timeout = '360s';

    const res = http.get('http://hl1.zil:8081/orders/total-prices', {
        timeout: timeout,
    });

    check(res, { 'got total prices': r => r.status === 200 });
}
