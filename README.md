# 🏥 HMS Payment Service

This is the **Payment Microservice** of the Hospital Management System (HMS).  
It is built using **Spring Boot (Java)** and follows the *microservices architecture* pattern — each service has its own database and runs independently.  

The Payment Service is responsible for handling **billing payments**, **refunds**, and **idempotent charge processing** for completed appointments.

---

## 🚀 Features

- Capture payments for bills (`/v1/payments/charge`)
- Handle refunds (partial / full)
- Enforce idempotency (no double-charging on retries)
- Update bill status after successful payment
- RESTful APIs (OpenAPI 3.0 / Swagger)
- Structured logging with correlation IDs
- Docker & Kubernetes ready

---

## 🧩 Tech Stack

| Component | Technology |
|------------|-------------|
| Language | Java 17 |
| Framework | Spring Boot 3.x |
| Database | MySQL |
| API Docs | Springdoc OpenAPI (Swagger UI) |
| Containerization | Docker |
| Deployment | Kubernetes / Minikube |

---

## 🗄️ Database Schema

**Table: payments**

| Field | Type | Description |
|--------|------|-------------|
| payment_id | BIGINT (PK) | Unique payment ID |
| bill_id | BIGINT | Linked bill record |
| amount | DECIMAL(10,2) | Payment amount |
| method | VARCHAR(50) | Payment method (UPI, CARD, CASH, etc.) |
| reference | VARCHAR(100) | External reference ID |
| paid_at | DATETIME | Payment timestamp |

---

## ⚙️ API Endpoints

| Method | Endpoint | Description |
|---------|-----------|-------------|
| `POST` | `/v1/payments/charge` | Capture a payment (idempotent) |
| `GET` | `/v1/payments` | List all payments (with pagination) |
| `GET` | `/v1/payments/{id}` | Get payment details by ID |
| `POST` | `/v1/payments/refund/{id}` | Initiate refund for a payment |

---

## 🧾 Example Request

**POST** `/v1/payments/charge`

```json
{
  "billId": 12345,
  "amount": 1500.00,
  "method": "UPI",
  "reference": "TXN-987654321"
}
