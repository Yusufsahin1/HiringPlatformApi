# Hiring Platform API

## 📋 Overview

Hiring Platform API is a comprehensive backend solution designed to facilitate the hiring process between companies and job seekers. Built with Spring Boot, this RESTful API provides a secure and efficient platform for job posting, application management, and user authentication.

## 🚀 Features

- **User Authentication & Authorization**
  - Secure JWT-based authentication
  - Role-based access control (Company, Job Seeker)
  
- **Company Features**
  - Company profile registration.
  - Job posting creation, updating, and deletion.
  - Viewing applications submitted for their job postings.
  
- **Job Seeker Features**
  - Job seeker profile registration.
  - Submitting job applications with an optional cover letter.
  - Viewing their own submitted applications.

- **Job Posting Management**
  - Publicly accessible listing of all job postings.
  - Retrieval of job postings by ID or by company.

## 🛠️ Technologies

- **Java 21**
- **Spring Boot 3.3.10**
- **Spring Security** with JWT authentication
- **Spring Data JPA**
- **PostgreSQL** database
- **Lombok** for reducing boilerplate code
- **Maven** for dependency management
- **spring-dotenv** for environment variable management
- **SpringDoc OpenAPI (Swagger UI)** for API documentation

## 📦 Project Structure

```
src/main/java/com/yusufsahin/hiring_platform_api/
├── config/               # Application configuration
├── controller/           # REST API endpoints
├── dto/                  # Data Transfer Objects
├── exception/            # Custom exceptions
├── model/                # Entity models
├── repository/           # Data access layer
├── security/             # Security configuration
└── service/              # Business logic
```

## 🔧 Setup & Installation

### Prerequisites

- Java 21 or higher
- Maven
- PostgreSQL database

### Installation Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/Yusufsahin1/HiringPlatformApi.git
   cd HiringPlatformApi
   ```

2. **Configure environment variables**
   
   Create a `.env` file in the root directory with the following variables:
   ```
   DB_URL=jdbc:postgresql://localhost:5432/your_database_name
   DB_SCHEMA=your_schema_name
   DB_USERNAME=your_username
   DB_PASSWORD=your_password
   SECRET_KEY=your_jwt_secret_key
   ```

3. **Build the application**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```
   
   The API will be available at `http://localhost:8080`

   Access the API documentation at `http://localhost:8080/swagger-ui/index.html`

## 🔌 API Endpoints

### Authentication

- `POST /api/v1/auth/register-company` - Register a new user (company role)
- `POST /api/v1/auth/register-jobseeker` - Register a new user (job seeker role)
- `POST /api/v1/auth/login` - Authenticate and receive JWT token

### Job Postings

- `GET /api/v1/job` - Get all job postings
- `GET /api/v1/job/{id}` - Get job posting by ID
- `POST /api/v1/job/create` - Create a new job posting (Company role)
- `PUT /api/v1/job/update/{id}` - Update a job posting (Company role)
- `DELETE /api/v1/job/delete/{id}` - Delete a job posting (Company role)
- `GET /api/v1/job/my-postings` - Get job postings created by the authenticated company
- `GET /api/v1/job/company/{companyId}` - Get job postings by company ID

### Job Applications

- `POST /api/v1/application/apply` - Submit a job application (Job Seeker role)
- `GET /api/v1/application/my-applications` - Get applications submitted by the authenticated job seeker
- `GET /api/v1/application/job/{jobId}` - Get applications for a specific job posting (Company role)

## 🔒 Security

The API uses JWT (JSON Web Token) for authentication. To access protected endpoints:

1. Obtain a JWT token by authenticating via the login endpoint
2. Include the token in the Authorization header of subsequent requests:
   ```
   Authorization: Bearer your_jwt_token
   ```

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.