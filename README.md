## La Quinta Pata Backend

## 🎯 Project Overview

The Community Archive of Migrant Memories Exhibition (MACMM) is a web platform created to catalog and disseminate audiovisual records that narrate migrant experiences. The videos are organized into five thematic axes: autobiography, objects, discrimination based on origin, gender discrimination, and resistance. In addition to the catalog, the platform allows users to view the participants’ journeys to Barcelona on an interactive map and to consult general information about the project and its team. Built with React (frontend), Spring Boot (backend), and PostgreSQL (database), the application follows an MVC architecture and implements a RESTful API.

## ⚙️ Technology Stack

  | Category                  | Technologies                                       |
  | ------------------------- | -------------------------------------------------- |
  | **Backend Framework**     | Spring Boot 3.x                                    |
  | **Language**              | Java 21                                            |
  | **Database**              | PostgreSQL                                         |
  | **ORM**                   | Spring Data JPA + Hibernate                        |
  | **Authentication**        | JWT (JSON Web Tokens) in Authorization header      |
  | **Security**              | Spring Security                                    |
  | **Build Tool**            | Maven 3.x                                          |
  | **Testing**               | JUnit 5 + Mockito                                  |
  | **Validation**            | Jakarta Validation (Bean Validation)               |
  | **CORS**                  | Spring Web CORS Configuration                      |
  | **Version Control**       | Git + GitHub                                       |

  ## ✨ Features

  ### 🔐 Authentication & Authorization
  - User registration with email and password
  - Secure login with JWT tokens in Authorization header
  - Password hashing with bcrypt
  - Role-based access control (Admin, User)
  - CORS configuration for secure cross-origin requests
  - Secure token refresh mechanism

  ### 📹 Video Management
  - Create, read, update, and delete video records
  - Organize videos by thematic axes
  - Store video metadata (title, description, URLs)
  - Video thumbnail management
  - RESTful endpoints for video operations

  ### 👥 User Management
  - User registration and profile management
  - User role assignment (Admin, User)
  - User data validation
  - Secure user operations

  ### 🗺️ Origin & Location Management
  - Manage origin/country information for participants
  - Link migrants to their origin
  - Support for geographic data

  ### 📊 Axis Management
  - Define and manage thematic axes
  - Link videos to specific axes
  - Categorization system for content organization

  ### ✔️ Data Validation
  - Comprehensive input validation on all DTOs
  - Size constraints on entity fields
  - Email format validation
  - Password strength requirements
  - Duplicate resource prevention

  ## 🧭 Architecture

  The application follows a layered architecture with clear separation of concerns:

  ### Directory Structure

  ```
  src/main/java/com/laquintapata/app/
  ├── config/                          
  │   ├── CorsConfig.java             
  │   └── JwtConfig.java              
  │
  ├── controller/                      
  │   ├── AuthController.java        
  │   ├── UserController.java        
  │   ├── VideoController.java        
  │   ├── AxisController.java        
  │   ├── OriginController.java      
  │   └── MigrantController.java      
  │
  ├── service/                        
  │   ├── interfaces/
  │   │   ├── AuthService.java
  │   │   ├── UserService.java
  │   │   ├── VideoService.java
  │   │   ├── AxisService.java
  │   │   ├── OriginService.java
  │   │   └── MigrantService.java
  │   └── impl/
  │       ├── AuthServiceImpl.java
  │       ├── UserServiceImpl.java
  │       ├── VideoServiceImpl.java
  │       ├── AxisServiceImpl.java
  │       ├── OriginServiceImpl.java
  │       └── MigrantServiceImpl.java
  │
  ├── repository/                      
  │   ├── UserRepository.java
  │   ├── VideoRepository.java
  │   ├── AxisRepository.java
  │   ├── OriginRepository.java
  │   └── MigrantRepository.java
  │
  ├── entity/                          
  │   ├── User.java
  │   ├── Video.java
  │   ├── Axis.java
  │   ├── Origin.java
  │   └── Migrant.java
  │
  ├── dto/                           
  │   ├── request/
  │   │   ├── AuthRequest.java
  │   │   ├── UserRequest.java
  │   │   ├── VideoRequest.java
  │   │   ├── AxisRequest.java
  │   │   ├── OriginRequest.java
  │   │   └── MigrantRequest.java
  │   └── response/
  │       ├── AuthResponse.java
  │       ├── UserResponse.java
  │       ├── VideoResponse.java
  │       ├── AxisResponse.java
  │       ├── OriginResponse.java
  │       └── MigrantResponse.java
  │
  ├── mapper/                          
  │   ├── UserMapper.java
  │   ├── VideoMapper.java
  │   ├── AxisMapper.java
  │   ├── OriginMapper.java
  │   └── MigrantMapper.java
  │
  ├── exception/                       
  │   ├── ResourceNotFoundException.java
  │   ├── DuplicateResourceException.java
  │   └── GlobalExceptionHandler.java
  │
  ├── security/                        
  │   ├── JwtAuthenticationFilter.java
  │   ├── JwtProvider.java
  │   └── SecurityConfig.java
  │
  └── Application.java                 

  src/main/resources/
  ├── application.properties          
  └── application-dev.properties      
```

  ## 🔌 API Endpoints

  ### Authentication
  - `POST /api/auth/register` - User registration
  - `POST /api/auth/login` - User login
  - `POST /api/auth/logout` - User logout

  ### Users
  - `GET /api/users` - Get all users (Admin only)
  - `GET /api/users/{id}` - Get user by ID
  - `GET /api/users/name/{name}` - Get user by name
  - `PUT /api/users/{id}` - Update user
  - `DELETE /api/users/{id}` - Delete user

  ### Videos
  - `GET /api/videos` - Get all videos
  - `GET /api/videos/{id}` - Get video by ID
  - `POST /api/videos` - Create new video (Admin only)
  - `PUT /api/videos/{id}` - Update video (Admin only)
  - `DELETE /api/videos/{id}` - Delete video (Admin only)

  ### Axes
  - `GET /api/axes` - Get all axes
  - `GET /api/axes/{id}` - Get axis by ID
  - `POST /api/axes` - Create new axis (Admin only)
  - `PUT /api/axes/{id}` - Update axis (Admin only)
  - `DELETE /api/axes/{id}` - Delete axis (Admin only)

  ### Origins
  - `GET /api/origins` - Get all origins
  - `GET /api/origins/{id}` - Get origin by ID
  - `POST /api/origins` - Create new origin (Admin only)
  - `PUT /api/origins/{id}` - Update origin (Admin only)
  - `DELETE /api/origins/{id}` - Delete origin (Admin only)

  ### Migrants
  - `GET /api/migrants` - Get all migrants
  - `GET /api/migrants/{id}` - Get migrant by ID
  - `GET /api/migrants/name/{name}` - Get migrant by name
  - `POST /api/migrants` - Create new migrant (Admin only)
  - `PUT /api/migrants/{id}` - Update migrant (Admin only)
  - `DELETE /api/migrants/{id}` - Delete migrant (Admin only)

  ## 🗄️ Database Schema

  ### Key Tables
  - **users** - User accounts and authentication
  - **videos** - Video content and metadata
  - **axes** - Thematic categories for videos
  - **origins** - Geographic origin/country information
  - **migrants** - Participant information with origin reference

  ## 🚀 Getting Started

  ### Prerequisites

  - Java 21
  - Maven 3.6 or higher
  - PostgreSQL 12 or higher
  - Frontend running on `http://localhost:5173` (for CORS)

  ### Installation Steps

  #### 1. Clone the Repository

  ```bash
  git clone https://github.com/La-Quinta-Pata/back.git
  cd back
  ```

  2. Configure the Database

  Create a PostgreSQL database for the application:

  CREATE DATABASE laquintapata;

  3. Set Up Environment Variables

  Create a .env file in the project root:

  ### Database Credentials
  DB_PASSWORD=your_database_password
  DB_URL=jdbc:postgresql://localhost:5432/laquintapata
  DB_USERNAME=postgres

  ### JWT Configuration
  JWT_SECRET=your_jwt_secret_key_must_be_at_least_256_bits_long

  ### Application
  APP_PORT=8080

  The application uses spring-dotenv to load these variables automatically.

  4. Run the Application

  Using System Maven:

  mvn spring-boot:run

  5. Verify Installation

  The API will be available at: http://localhost:8080

  ### 🧪 Testing

  Running Tests

  Run all tests:

  mvn test

  ## 📝 Git Workflow

  The project uses feature branch workflow:

  - main - Production-ready code
  - dev - Development integration branch
  - feature/* - Feature branches for new functionality
  - fix/* - Bug fix branches
  - chore/* - Maintenance and polish branches

## 👥 Team Members

| Role | Name | GitHub | LinkedIn |
|------|------|--------|----------|
| 🧠 Product Owner & Developer | **Suraya Mattar** | [GitHub](https://github.com/surayac) | [LinkedIn](https://www.linkedin.com/in/suraya-mattar/) |
| 🧩 Scrum Master & Developer | **Daniella Pacheco** | [GitHub](https://github.com/DaniPacheco8) | [LinkedIn](https://www.linkedin.com/in/daniellapacheco/) |
| 💻 Developer | **Ana Aguilera** | [GitHub](https://github.com/AnaAguileraMorales88) | [LinkedIn](https://www.linkedin.com/in/ana-aguilera-morales-011b1a238/) |
| 💻 Developer | **Montserrat Muñoz** | [GitHub](https://github.com/Montc027) | [LinkedIn](https://www.linkedin.com/in/montse-mu%C3%B1oz-ba202b227/) |
| 💻 Developer | **Estefanía Secanell** | [GitHub](https://github.com/Abaraira) | [LinkedIn]() |
| 💻 Developer | **Mio Ogura** | [GitHub](https://github.com/miaryl) | [LinkedIn](https://www.linkedin.com/in/mio-ogura/) |
