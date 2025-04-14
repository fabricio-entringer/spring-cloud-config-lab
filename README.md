# 🌩️ Spring Cloud Config Lab

This repository demonstrates a complete Spring Cloud Config setup with both server and client components. It provides centralized configuration management with support for multiple environments (dev, prod, test).

## 🏗️ Project Structure

- **config-server**: Spring Cloud Config Server that serves configuration files from GitHub
  - Main class: `ConfigServerApplication.java` - initializes the Config Server with `@EnableConfigServer`
  - Configuration in `application.yml` specifies GitHub repository location and server port (8888)
  - Responsible for serving configuration properties to all client applications
  - Supports multiple environments through profile-specific configuration files

- **config-client**: Spring Boot application that consumes configuration from the Config Server
  - Main class: `ConfigClientApplication.java` - bootstraps the client application
  - `bootstrap.yml` - contains Config Server connection details and application name
  - `JacksonConfig.java` - configures JSON serialization for configuration properties
  - `ConfigController.java` - REST endpoints to retrieve and display configuration values
  - `ApplicationConfig.java` - configuration properties model with nested objects for different categories
  - `ApplicationConfigDTO.java` - data transfer object for exposing configurations via API

- **config-repo**: Repository of configuration files organized by application and environment
  - Structure follows Spring Cloud Config convention: `/{application}/{profile}/{label}`
  - Contains environment-specific configurations (dev/prod/test) for each application
  - Each YAML file (`config-client.yml`) contains the same property structure with environment-specific values
  - Changes to these files can be dynamically applied to running applications via refresh mechanism

The project demonstrates a complete microservice configuration pattern where:
1. The Config Server centralizes all configuration management
2. The Config Client shows how applications can consume external configuration
3. The Config Repo organizes configurations in a version-controlled, environment-specific way

## 🚀 Technology Stack

## 🚀 Technology Stack

- **Java 17**
  - Latest LTS version with improved performance and modern language features
  - Supports sealed classes, records, and enhanced switch expressions for cleaner code

- **Spring Boot 3.2.3**
  - Provides auto-configuration and embedded server capabilities
  - Simplifies dependency management and application bootstrapping
  - Includes production-ready features like health checks and metrics

- **Spring Cloud 2023.0.0**
  - Comprehensive framework for building cloud-native applications
  - Enables distributed system patterns for microservices architecture

- **Spring Cloud Config Server**
  - Centralizes configuration management across multiple services and environments
  - Supports versioning through Git backend integration

- **Spring Boot Actuator**
  - Adds production-ready features for monitoring and managing the application
  - Provides endpoints for health checks, metrics, and configuration refresh

- **Spring Web**
  - Supports building RESTful web services
  - Includes Spring MVC for creating web controllers

- **Spring Security** (optional)
  - Secures the Config Server with authentication and authorization
  - Protects sensitive configuration data

- **Jackson Databind**
  - Handles JSON serialization and deserialization
  - Used for converting configuration objects to JSON responses

- **Lombok**
  - Reduces boilerplate code through annotations
  - Simplifies model classes with automatic getters, setters, and constructors

- **Maven**
  - Build automation and dependency management tool
  - Provides consistent, reproducible builds with defined project structure

## ✨ Features

- Centralized configuration management using Spring Cloud Config
- Multiple environment support (dev, prod, test)
- GitHub-based configuration storage
- Security with basic authentication
- Actuator endpoints for monitoring and management
- Dynamic configuration refresh
- REST API to query configuration properties

## 🚦 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Git

### Running the Config Server

1. Navigate to the config-server directory:
   ```
   cd config-server
   ```

2. Build the project:
   ```
   mvn clean package
   ```

3. Run the server:
   ```
   java -jar target/config-server-0.0.1-SNAPSHOT.jar
   ```

The Config Server will start on port 8888 and will serve configurations from the GitHub repository: https://github.com/fabricio-entringer/spring-cloud-config-lab

### Running the Config Client

1. Navigate to the config-client directory:
   ```
   cd config-client
   ```

2. Build the project:
   ```
   mvn clean package
   ```

3. Run the client with a specific profile (dev, prod, or test):
   ```
   java -jar target/config-client-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
   ```

The Config Client will start on port 8080 and fetch its configuration from the Config Server.

## 🔌 API Endpoints

The Config Client exposes the following REST endpoints:

- **GET /api/config**: Returns all configuration properties
- **GET /api/config/info**: Returns basic application information
- **GET /api/config/database**: Returns database configuration
- **GET /api/config/security**: Returns security configuration
- **GET /api/config/cache**: Returns cache configuration
- **GET /api/config/integration**: Returns integration configuration

## ⚙️ Configuration Properties

The application uses a comprehensive set of configuration properties:

### Basic Properties
- `application.name`: Application name
- `application.description`: Application description
- `application.environment`: Current environment (dev, prod, test)

### Database Properties
- `application.database.url`: Database connection URL
- `application.database.username`: Database username
- `application.database.password`: Database password
- `application.database.maxPoolSize`: Maximum connection pool size
- `application.database.minIdle`: Minimum idle connections
- `application.database.connectionTimeout`: Connection timeout in milliseconds
- `application.database.showSql`: Whether to show SQL statements

### Security Properties
- `application.security.tokenSecret`: Secret key for token generation
- `application.security.tokenExpirationMs`: Token expiration time in milliseconds
- `application.security.allowedOrigins`: CORS allowed origins
- `application.security.enableCsrf`: Whether to enable CSRF protection

### Cache Properties
- `application.cache.enabled`: Whether caching is enabled
- `application.cache.timeToLiveSeconds`: Cache TTL in seconds
- `application.cache.maxSize`: Maximum cache size

### Integration Properties
- `application.integration.apiUrl`: External API URL
- `application.integration.timeout`: API request timeout in milliseconds
- `application.integration.maxRetries`: Maximum number of retry attempts
- `application.integration.enabled`: Whether integration is enabled

## 🔄 Configuration Refresh

The Config Client supports dynamic configuration refresh without requiring a restart:

1. Make changes to your configuration files in the config-repo
2. Push changes to the GitHub repository
3. Trigger a refresh by sending a POST request to the client's refresh endpoint:
   ```
   curl -X POST http://localhost:8080/actuator/refresh
   ```

## 🔮 Future Enhancements

1. Add support for additional configuration backends:
   - 📁 File System
   - 🗄️ Database
   - 🔐 Vault
   - 🔍 Consul

2. Implement encryption/decryption for sensitive properties
3. Add Spring Cloud Bus for broadcast configuration changes
4. Implement circuit breaker for improved resilience
5. Add Docker support for containerized deployment
6. Implement monitoring with Prometheus and Grafana

## 🌐 Profiles

This project includes configuration for three environments:

1. **dev**: Development environment with debug logging and configuration for local development
2. **prod**: Production environment with optimized settings for performance and security
3. **test**: Testing environment with settings suitable for automated testing

## 🔒 Security Considerations

- The Config Server uses Basic Authentication to secure access
- Sensitive properties should be encrypted in production
- Access to the Config Server should be restricted to internal networks in production
- Consider using HTTPS for all communication in production environments
- Rotate secrets and credentials regularly

## 🛠️ Troubleshooting

- If the Config Client fails to start, ensure the Config Server is running
- Check the connection settings in bootstrap.yml
- Verify that the configuration files exist in the Git repository
- Enable debug logging for more detailed information:
  ```
  logging.level.org.springframework.cloud.config=DEBUG
  ```
- Check server logs for any connection or authentication issues
- Verify network connectivity between client and server

## 📖 Additional Resources

- [Spring Cloud Config Documentation](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/)
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Cloud Documentation](https://docs.spring.io/spring-cloud/docs/current/reference/html/)

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👨‍💻 Contributors

- Fabricio Entringer