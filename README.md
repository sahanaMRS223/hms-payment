
# hms-payment (Java Spring Boot)

Payment microservice for HMS, compatible with hms-patient style.

## Build
Requires Maven and Java 17.
```bash
mvn clean package -DskipTests
```

## Run (local)
Ensure MySQL is running and a database `paymentdb` exists on port 33062.
Update src/main/resources/application.yml if needed.

Run:
```bash
mvn spring-boot:run
```

## Docker
Build:
```bash
mvn clean package -DskipTests
docker build -t hms-payment:latest .
```

Run (example):
```bash
docker run -e SPRING_PROFILES_ACTIVE=local -p 8087:8087 --network host hms-payment:latest
```

## API
- POST /v1/payments  (Idempotency-Key optional header)
- GET /v1/payments
- GET /v1/payments/{id}
