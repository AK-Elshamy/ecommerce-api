# 🛒 E-Commerce REST API

A backend REST API for an E-commerce application built with **Spring Boot** and **MySQL**.

---

## 📑 Table of Contents

- [Tech Stack](#-tech-stack)
- [Features](#-features)
- [Architecture](#️-architecture)
- [Authentication](#-authentication)
- [Main Endpoints](#-main-endpoints)
- [Run the Project](#️-run-the-project)
- [Author](#-author)

---

## 🚀 Tech Stack

| Category | Technology |
|---|---|
| ☕ Language | Java 25 |
| 🍃 Framework | Spring Boot 4 |
| 🌐 Web | Spring Web |
| 🗄️ Persistence | Spring Data JPA |
| 🐬 Database | MySQL |
| 🔐 Security | Spring Security |
| 🎫 Auth | JWT Authentication |
| 🔑 Encryption | BCrypt Password Encryption |
| 🧩 Utilities | Lombok |
| 📦 Build Tool | Maven |

---

## ✨ Features

- 👤 User Registration & Login
- 🔐 JWT Authentication
- 🛡️ Role-based Authorization (Customer / Admin)
- 📦 Product Management
- 🗂️ Category Management
- 🛒 Shopping Cart
- 💳 Checkout & Orders
- 📊 Stock Management
- 🔄 Order Status Management
- ⚠️ Global Exception Handling
- ✅ Request Validation

---

## 🏗️ Architecture

The project follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

---

## 🔑 Authentication

The API uses **JWT Bearer Tokens** for authentication.

```text
Authorization: Bearer <your-token>
```

---

## 📌 Main Endpoints

| Endpoint | Description |
|---|---|
| `/api/auth` | 🔐 Register / Login |
| `/api/products` | 📦 Manage & browse products |
| `/api/categories` | 🗂️ Manage & browse categories |
| `/api/cart` | 🛒 Shopping cart operations |
| `/api/orders` | 💳 Checkout & order management |

---

## ▶️ Run the Project

### 1️⃣ Create MySQL Database

```sql
CREATE DATABASE ecommerce_db;
```

### 2️⃣ Configure Database

Update `application.properties` with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=root
spring.datasource.password=your_password
```

### 3️⃣ Run

```bash
mvn spring-boot:run
```

The API will be available at:

```text
http://localhost:8080
```

---

## 👨‍💻 Author

**Ahmed Elshamy**

---

<p align="center">Made with ☕ and Spring Boot</p>
