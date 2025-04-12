# Spring Cloud Config Lab

This repository demonstrates a complete Spring Cloud Config setup with both server and client components. It provides centralized configuration management with support for multiple environments (dev, prod, test).

## Project Structure

- **config-server**: Spring Cloud Config Server that serves configuration files from GitHub
- **config-client**: Spring Boot application that consumes configuration from the Config Server
- **config-repo**: Repository of configuration files organized by application and environment

## Technology Stack

- Java 17
- Spring Boot 3.2.3
- Spring Cloud 2023.0.0
- Maven

## Features

- Centralized configuration management using Spring Cloud Config
- Multiple environment support (dev, prod, test)
- GitHub-based configuration storage
- Security with basic authentication
- Actuator endpoints for monitoring and management
- Dynamic configuration refresh
- REST API to query configuration properties

## Getting Started

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

## API Endpoints

The Config Client exposes the following REST endpoints:

- **GET /api/config**: Returns all configuration properties
- **GET /api/config/info**: Returns basic application information
- **GET /api/config/database**: Returns database configuration
- **GET /api/config/security**: Returns security configuration
- **GET /api/config/cache**: Returns cache configuration
- **GET /api/config/integration**: Returns integration configuration

## Configuration Properties

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

## Future Enhancements

1. Add support for additional configuration backends:
   - File System
   - Database
   - Vault
   - Consul

2. Implement encryption/decryption for sensitive properties
3. Add Spring Cloud Bus for broadcast configuration changes
4. Implement circuit breaker for improved resilience

## Profiles

This project includes configuration for three environments:

1. **dev**: Development environment with debug logging and configuration for local development
2. **prod**: Production environment with optimized settings for performance and security
3. **test**: Testing environment with settings suitable for automated testing

## Security Considerations

- The Config Server uses Basic Authentication to secure access
- Sensitive properties should be encrypted in production
- Access to the Config Server should be restricted to internal networks in production

## Troubleshooting

- If the Config Client fails to start, ensure the Config Server is running
- Check the connection settings in bootstrap.yml
- Verify that the configuration files exist in the Git repository
- Enable debug logging for more detailed information

## License

This project is licensed under the MIT License - see the LICENSE file for details.