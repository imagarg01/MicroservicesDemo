import http from 'k6/http';
import { sleep, check } from 'k6';

export const options = {
  scenarios: {
    normal_load: {
      executor: 'constant-arrival-rate',
      rate: 10, // 10 TPS
      timeUnit: '1s',
      duration: '2m',
      preAllocatedVUs: 10,
      maxVUs: 50,
    },
    stress_test: {
      executor: 'ramping-arrival-rate',
      startRate: 10,
      timeUnit: '1s',
      stages: [
        { duration: '2m', target: 10 }, // 10 TPS
        { duration: '2m', target: 50 }, // Ramp to 50 TPS
        { duration: '1m', target: 10 }, // Scale down
      ],
      preAllocatedVUs: 50,
      maxVUs: 200,
    },
    spike_test: {
      executor: 'per-vu-iterations',
      vus: 100, // 100 concurrent users
      iterations: 10,
      maxDuration: '30s',
    },
  },
  thresholds: {
    http_req_duration: ['p(95)<1000'], // 95% of requests < 1s
    http_req_failed: ['rate<0.01'], // Error rate < 1%
  },
};

export default function () {
  const payload = JSON.stringify({
    productId: 'prod-123',
    amount: 99.99,
  });

  const params = {
    headers: { 'Content-Type': 'application/json' },
  };

  const res = http.post('http://localhost:8081/orders', payload, params);

  check(res, {
    'status is 200': (r) => r.status === 200,
    'response time < 1s': (r) => r.timings.duration < 1000,
  });

  sleep(1); // Simulate user think time
}
