## La Quinta Pata Backend

## 🎯 Project Overview

La Muestra Archivo Comunitario de Memorias Migrantes (MACMM) es una plataforma web creada para catalogar y difundir registros audiovisuales que narran experiencias migrantes. Los vídeos están organizados en cinco ejes temáticos: autobiografía, objetos, discriminación por origen, discriminación de género y resistencia. Además del catálogo, la plataforma permite visualizar los desplazamientos de las personas participantes hacia Barcelona en un mapa interactivo y consultar la información general del proyecto y su equipo. Built with React (frontend), Spring Boot (backend), and PostgreSQL (database), the application follows MVC architecture and implements a RESTful API.

## 🚀 Getting Started

### Installation Steps

#### 1. Clone the Repository

```bash
git clone https://github.com/La-Quinta-Pata/back.git
cd back
```

#### 2. Configure the Database

Create a PostgreSQL database for the application:

```sql
CREATE DATABASE laquintapata;
```

#### 3. Set Up Environment Variables

Create a `.env` file in the project root:

```bash
# Database Credentials
DB_PASSWORD=your_database_password

# JWT Configuration
JWT_SECRET=your_jwt_secret_key_must_be_at_least_256_bits_long
```

The application uses `spring-dotenv` to load these variables automatically.

#### 4. Run the Application

**Using Maven Wrapper (if available):**

```bash
./mvnw spring-boot:run
```

**Using System Maven:**

```bash
mvn spring-boot:run
```

#### 5. Verify Installation

The API will be available at: `http://localhost:8080`

```bash
curl http://localhost:8080/api/concerts
```

## 🧪 Testing

### Running Tests

```bash
# Run all tests
mvn test

```

## 🔄 Development Workflow

1. Create a feature branch from `dev`:

   ```bash
   git checkout dev
   git checkout -b feature/your-feature-name
   ```

2. Make your changes and commit:

   ```bash
   git add .
   git commit -m "Description of changes"
   ```

3. Push and create a Pull Request:
   ```bash
   git push origin feature/your-feature-name
   ```

## 👥 Team Members

| Role | Name | GitHub | LinkedIn |
|------|------|--------|----------|
| 🧠 Product Owner & Developer | **Suraya Matter** | [GitHub](https://github.com/surayac) | [LinkedIn](https://www.linkedin.com/in/suraya-mattar/) |
| 🧩 Scrum Master & Developer | **Daniella Pacheco** | [GitHub](https://github.com/DaniPacheco8) | [LinkedIn](https://www.linkedin.com/in/daniellapacheco/) |
| 💻 Developer | **Anna Aguilera** | [GitHub](https://github.com/AnaAguileraMorales88) | [LinkedIn](https://www.linkedin.com/in/ana-aguilera-morales-011b1a238/) |
| 💻 Developer | **Montserrat Muñoz** | [GitHub](https://github.com/Montc027) | [LinkedIn](https://www.linkedin.com/in/montse-mu%C3%B1oz-ba202b227/) |
| 💻 Developer | **Estefanía Secanell** | [GitHub](https://github.com/Abaraira) | [LinkedIn]() |
| 💻 Developer | **Mio Ogura** | [GitHub](https://github.com/miaryl) | [LinkedIn](https://www.linkedin.com/in/mio-ogura/) |
