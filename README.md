# Inventory & Order Management Microservices

## Overview

This project implements a **microservices-based Inventory and Order Management system** as part of the Körber technical assignment.

The solution consists of **two independent Spring Boot microservices**:

1. **Inventory Service** – Manages product inventory and batch-level stock with expiry-based allocation.
2. **Order Service** – Accepts product orders, checks inventory availability, allocates stock, and updates inventory accordingly.

The system follows clean architecture principles, uses **Spring Data JPA**, **H2 in-memory database**, **Liquibase for database migrations**, and applies the **Factory Design Pattern** to ensure extensibility.

---

## Tech Stack

- Java **17**
- Spring Boot **4.x**
- Spring Data JPA
- H2 In-Memory Database
- Liquibase
- RestTemplate (inter-service communication)
- Maven
- JUnit 5 & Mockito (testing)

---

## Project Structure

```
Inventory-Order-Project
│
├── inventory-service
│   ├── controller
│   ├── service
│   ├── service/impl
│   ├── service/factory
│   ├── repository
│   ├── model
│   ├── dto
│   └── resources
│       └── db/changelog
│
├── order-service
│   ├── controller
│   ├── service
│   ├── serviceImpl
│   ├── client
│   ├── repository
│   ├── model
│   └── resources
│       └── db/changelog
│
└── README.md
```

---

## Inventory Service

### Port
```
8081
```

### Endpoints

#### GET /inventory/{productId}

Returns inventory batches sorted by expiry date.

**Example**
```
GET http://localhost:8081/inventory/1001
```

**Response**
```json
[
  {
    "batchId": 1,
    "quantity": 50,
    "expiryDate": "2025-12-31"
  },
  {
    "batchId": 2,
    "quantity": 30,
    "expiryDate": "2026-03-15"
  }
]
```

#### POST /inventory/update

Updates inventory quantities after order allocation.

**Request**
```json
{
  "productId": 1005,
  "updates": [
    {
      "batchId": 5,
      "quantity": 10
    }
  ]
}
```

---

## Order Service

### Port
```
8080
```

### Endpoints

#### POST /order

Places an order and reserves inventory.

**Request**
```json
{
  "productId": 1002,
  "quantity": 3
}
```

**Response**
```json
{
  "orderId": 5012,
  "productId": 1002,
  "productName": "Smartphone",
  "quantity": 3,
  "status": "PLACED",
  "reservedFromBatchIds": [3],
  "message": "Order placed. Inventory reserved."
}
```

---

## Database & Liquibase

- Each service uses an **H2 in-memory database**
- Schema and data are loaded automatically using **Liquibase**
- CSV files are loaded via `<loadData>`

---

## Testing

- Unit tests using **JUnit 5** and **Mockito**
- Integration tests using `@SpringBootTest`
- REST endpoints covered

---

## Running the Application

### Prerequisites
- Java 17+
- Maven 3+

### Steps

```bash
git clone <repository-url>
cd inventory-service
mvn spring-boot:run
```

In a new terminal:

```bash
cd order-service
mvn spring-boot:run
```

---

## H2 Console

| Service | URL |
|-------|-----|
| Inventory | http://localhost:8081/h2-console |
| Order | http://localhost:8080/h2-console |

---

## Conclusion

This project fulfills all requirements specified in the Körber technical assignment, focusing on correctness, clarity, extensibility, and ease of setup.
