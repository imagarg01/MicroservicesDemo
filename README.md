# Microservices Demo with Spring Boot, Docker, and k6

This project demonstrates a set of microservices (Order, Inventory, Payment) built with Spring Boot, running on Docker to simulate AWS ECS Fargate behavior. It includes externalized configuration via Spring Cloud Config and a k6 performance test plan with multiple scenarios (normal load, stress test, spike test). Prometheus and Grafana provide basic monitoring.

## Prerequisites
- Docker and Docker Compose
- Java 17 and Maven
- k6 (`brew install k6` on macOS or equivalent)
- Git

## Project Structure
- `order-service`: Handles order creation, I/O-bound (700ms DB simulation + calls to Inventory/Payment).
- `inventory-service`: Checks stock, I/O-bound (500ms).
- `payment-service`: Processes payments, CPU-bound (300ms).
- `config-server`: Provides externalized configurations.
- `k6`: k6 performance test scripts.
- `config-repo`: Configuration files for services.
- `docker-compose.yml`: Simulates ECS Fargate with CPU/memory limits.
- `prometheus.yml`: Prometheus configuration for metrics.

## Setup
1. **Clone the Repository**:
   ```bash
   git clone <your-repo-url>
   cd microservices-demo
