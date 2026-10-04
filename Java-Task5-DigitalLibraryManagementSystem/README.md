# Digital Library Management System

A web-based Digital Library Management System developed using Java and Spring Boot. The application provides separate Admin and User functionalities for managing books, issuing and returning books, tracking fines, and handling advance reservations.

## Project Overview

The Digital Library Management System is designed to simplify library operations by providing an online platform for both administrators and library members.

The system allows administrators to manage the library catalogue, members, issued books, fines, and user queries. Registered users can browse books, issue available books, return books, view their issued books, and reserve books that are currently unavailable.

## Features

### Admin Features

* Secure admin login
* Admin dashboard
* Add new books
* Edit existing books
* Delete books
* View total books
* View issued books
* View registered members
* Manage fines
* Mark fines as paid
* View and manage user queries
* Monitor book availability

### User Features

* User registration
* User login
* Browse available books
* Search books
* View book details
* Issue books
* Return issued books
* View issued books
* Automatic book availability update
* Fine calculation for overdue books
* Advance booking/reservation of unavailable books
* Cancel book reservations
* Submit queries through the contact/query section

## Technology Stack

### Backend

* Java
* Spring Boot
* Spring MVC
* Spring Data JPA
* Spring Security

### Frontend

* HTML
* CSS
* Thymeleaf
* JavaScript

### Database

* PostgreSQL

### Build Tool

* Maven

## Project Architecture

The project follows a layered MVC architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

### Main Layers

**Controller:** Handles HTTP requests and connects the frontend with backend services.

**Service:** Contains the business logic such as issuing books, returning books, calculating fines, and managing reservations.

**Repository:** Communicates with the PostgreSQL database using Spring Data JPA.

**Model/Entity:** Represents database tables such as users, books, issues, fines, bookings, and queries.

**Templates:** Thymeleaf HTML pages used for the application interface.

## Main Entities

The application contains the following major entities:

* User
* Book
* Issue
* Fine
* Booking
* Query

## Book Management

Each book contains information such as:

* Book ID
* Title
* Author
* ISBN
* Category
* Total Quantity
* Available Quantity

When a book is issued, the available quantity is decreased.

When a book is returned, the available quantity is increased.

## Fine Management

The system supports overdue fine management.

When a user returns a book after the due date, the system can calculate the applicable fine.

Example:

```text
Fine = Number of overdue days × Fine per day
```

Administrators can view fines and mark them as paid.

## Reservation System

Users can reserve books that are currently unavailable.

The reservation system allows users to:

* Create a reservation
* View reservations
* Check reservation status
* Cancel reservations

## Authentication and Authorization

Spring Security is used for authentication and role-based authorization.

The application supports:

```text
ADMIN
USER
```

Administrators can access admin pages, while normal users can access user functionality.

Passwords are securely stored using BCrypt password encoding.

## Database

The application uses PostgreSQL as the database.

The database stores information related to:

* Users
* Books
* Book Issues
* Fines
* Book Reservations
* User Queries

## How to Run the Project

### 1. Clone the Repository

```bash
git clone https://github.com/chiraggoel226-ui/OIBSIP.git
```

### 2. Open the Project

Open the Digital Library project in:

* IntelliJ IDEA
* Eclipse
* Spring Tool Suite

### 3. Configure PostgreSQL

Create a PostgreSQL database and update the database configuration in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/digital_library
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
```

Replace the PostgreSQL username and password with your local credentials.

### 4. Run the Application

Run the Spring Boot application from your IDE or use Maven:

```bash
mvn spring-boot:run
```

### 5. Open in Browser

```text
http://localhost:8080
```

## Important URLs

### General

```text
/
```

### Admin

```text
/admin/dashboard
/admin/books
/admin/books/add
/admin/books/edit/{id}
/admin/books/delete/{id}
/admin/issues
/admin/users
/admin/fines
/admin/queries
```

### User

```text
/user/dashboard
/user/books
/user/my-books
/user/booking
/user/contact
```

## Project Structure

```text
src
├── main
│   ├── java
│   │   └── DigitalLibraryManagementSystem
│   │       ├── controller
│   │       ├── model
│   │       ├── repository
│   │       ├── service
│   │       └── config
│   │
│   └── resources
│       ├── static
│       │   ├── css
│       │   └── js
│       │
│       ├── templates
│       │   ├── admin
│       │   └── user
│       │
│       └── application.properties
│
└── pom.xml
```

## Future Improvements

* Email notifications for due dates and reservations
* Advanced book search and filtering
* Pagination for large book collections
* Admin analytics and reports
* Improved reservation queue management
* Responsive UI improvements
* Fine payment integration

## Screenshots

Add screenshots of the following pages to the project repository:

* Login Page
* Registration Page
* Admin Dashboard
* Manage Books
* Add Book
* Issued Books
* Members
* Fine Management
* User Dashboard
* My Books
* Book Reservations

## Internship Task

This project was developed as part of the Oasis Infobyte Internship Program.

**Task:** Digital Library Management System

## Author

**Chirag Goel**

GitHub:
https://github.com/chiraggoel226-ui

---

## Conclusion

The Digital Library Management System provides a complete web-based solution for managing library operations. It combines Spring Boot, Spring Security, Thymeleaf, JPA, and PostgreSQL to implement role-based authentication, catalogue management, book issuing and returning, fine management, reservations, and user queries.
