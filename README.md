# 💰 Finance Dashboard Backend

A robust backend system for managing financial records with **role-based access control**, **JWT authentication**, and **dashboard analytics**.

This project demonstrates clean backend architecture, secure API design, and efficient data handling using Spring Boot.

---

## 🚀 Features

### 🔐 Authentication & Authorization

* JWT-based authentication (Register & Login)
* Role-based access control:

  * **ADMIN** → Full access (CRUD + user management)
  * **ANALYST** → View records + analytics
  * **VIEWER** → Read-only access

---

### 👤 User Management

* Create users
* Assign roles (ADMIN, ANALYST, VIEWER)
* Activate / deactivate users
* Secure endpoints using Spring Security

---

### 💰 Financial Records

* Create, update, and soft delete records
* Fields include:

  * Amount
  * Type (INCOME / EXPENSE)
  * Category
  * Description
  * Date
* Filtering:

  * By type
  * By category
  * By date range

---

### 📊 Dashboard Analytics

* Total Income
* Total Expense
* Net Balance
* Category-wise totals
* Monthly trends
* Recent activity

---

### 🔍 Additional Features

* Pagination for record listing
* Search functionality
* Soft delete support (data safety)
* Validation & error handling
* Clean layered architecture

---

## 🛠️ Tech Stack

* **Backend:** Spring Boot
* **Language:** Java
* **Database:** MySQL
* **ORM:** Spring Data JPA (Hibernate)
* **Security:** Spring Security + JWT
* **Build Tool:** Maven
* **Utilities:** Lombok

---

## 📁 Project Structure

```
com.finance.dashboard
│
├── controller        # REST API layer
├── service           # Business logic
├── repository        # Data access layer
├── model             # Entity classes
├── dto               # Request/Response DTOs
├── security          # JWT + Security config
├── specification     # Filtering logic
├── exception         # Global exception handling
└── config            # Application configuration
```

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/vimalkarkarpatel12/finance-dashboard-backend.git
cd finance-dashboard
```

---

### 2️⃣ Configure MySQL

Create a database:

```sql
CREATE DATABASE finance_db;
```

---

### 3️⃣ Update `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/finance_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=your_secret_key
jwt.expiration=86400000
```

---

### 4️⃣ Run the Application

```bash
mvn spring-boot:run
```

---

## 🔑 API Endpoints

### 🔐 Authentication

| Method | Endpoint         | Description       |
| ------ | ---------------- | ----------------- |
| POST   | `/auth/register` | Register new user |
| POST   | `/auth/login`    | Login & get JWT   |

---

### 👤 User Management (ADMIN)

| Method | Endpoint             |
| ------ | -------------------- |
| GET    | `/users`             |
| PUT    | `/users/{id}/status` |

---

### 💰 Financial Records

| Method | Endpoint                   |
| ------ | -------------------------- |
| POST   | `/records`                 |
| GET    | `/records?page=0&size=5`   |
| PUT    | `/records/{id}`            |
| DELETE | `/records/{id}`            |
| GET    | `/records/filter`          |
| GET    | `/records/search?keyword=` |

---

### 📊 Dashboard

| Method | Endpoint              |
| ------ | --------------------- |
| GET    | `/dashboard/summary`  |
| GET    | `/dashboard/category` |
| GET    | `/dashboard/recent`   |
| GET    | `/dashboard/monthly`  |

---

## 🔐 Authentication Usage

Include JWT token in request headers:

```
Authorization: Bearer <your_token>
```

---

## 🧪 Testing

* Use **Postman** or any API testing tool
* Steps:

  1. Register user
  2. Login → get JWT token
  3. Use token in secured APIs

---

## 📌 Assumptions

* Soft delete is used instead of permanent deletion
* Monthly trends are based on record creation date
* Role-based access enforced via Spring Security

---

## 🎯 Evaluation Highlights

✔ Clean architecture (Controller → Service → Repository)
✔ Role-based access control
✔ Efficient aggregation queries
✔ Proper validation & error handling
✔ Scalable and maintainable design

---

## 🚀 Future Improvements

* Swagger API documentation
* Unit & integration testing
* Rate limiting
* Advanced analytics

---

## 👨‍💻 Author

**Vimal**
Backend Developer (Spring Boot | Java)



    
