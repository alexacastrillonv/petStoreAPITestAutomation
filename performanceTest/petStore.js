import http from 'k6/http';
import { sleep, check } from 'k6';
import { Counter } from 'k6/metrics';

export const requests = new Counter('http_reqs');

const status=['sold','pending','available']


const reqHeader = {
    headers: { 'Content-Type': 'application/json' },
}

export const options = {
    scenarios: {
        getPetByStatus: {
            executor: 'ramping-vus',
            startVUs: 0,
            stages: [
                { duration: '180s', target: 100  },
                { duration: '180s', target: 100 },
                { duration: '10s', target: 0 },
              ],
            gracefulRampDown: '0s',
            exec: 'getPetByStatus',
        },
        /*getPetById: {
            executor: 'ramping-vus',
            startVUs: 0,
            stages: [
                { duration: '50s', target: 50 },
                { duration: '40s', target: 50 },
                { duration: '50s', target: 0 },
              ],
            gracefulRampDown: '0s',
            exec: 'getPetById',
        }*/
    }
};

export function getPetByStatus() {
    const randomStatus = status[Math.floor(Math.random() * status.length)];
    let res = http.get(`https://petstore3.swagger.io/api/v3/pet/findByStatus?status=${randomStatus}`, reqHeader);
    let checkRes = check(res, {
        'status is 200': (r) => r.status === 200,
    });
}

export function getPetById() {
    const randomId = Math.floor(Math.random() * 3);
    let res = http.get(`https://petstore3.swagger.io/api/v3/pet/${randomId}`, reqHeader);
    let checkRes = check(res, {
        ' getPetById status should be 200': (r) => r.status === 200,
        'getPetById status is 404': (r) => r.status === 404,
    });
}

