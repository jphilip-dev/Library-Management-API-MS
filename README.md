# Library Management API Microservices

A modular library management system built with Java, Spring Boot, and Maven.  
This project is organized into several microservices:

- **auth-service**: Handles authentication and user management.
- **user-profile-service**: Manages user profiles.
- **book-service**: Manages book catalog and details.
- **book-inventory-service**: Manages book inventory and branch stock.
- **borrowing-service**: Handles borrowing records and transactions.
- **api-gateway**: API Gateway for routing and security.

## Features

- User authentication and authorization (JWT)
- Book catalog and search
- Inventory management per branch
- Borrowing and returning books
- Admin and user endpoints
- Role-based access control

## Tech Stack

- Java 17+
- Spring Boot
- Spring Cloud Gateway
- Maven
- Docker (optional for deployment)