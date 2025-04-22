import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
    scenarios: {
        arbitrary_scenario_name: {
            executor: 'ramping-vus',
            stages: [
                { duration: '5s', target: 20 },
                { duration: '30s', target: 10 },
                { duration: '15s', target: 35 },
                { duration: '5s', target: 1 },
                { duration: '1s', target: 25 },
            ],
        },
    },
};

export default () => {
    const urlRes = http.get('http://10.0.2.4:8080/products');
//   sleep(1);
};