# Java-Spring-React TODO App

This project is a TODO application built using Java Spring Boot and React. Users can add, edit, mark as completed, and delete tasks. Additionally, users can delete completed tasks and all tasks.

## Table of Contents

- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [API Endpoints](#api-endpoints)
- [Swagger Documentation](#swagger-documentation)

## Features

- Add tasks
- Edit tasks
- Mark tasks as completed/uncompleted
- Delete tasks
- Delete completed tasks
- Delete all tasks
- Paginate tasks display

## Requirements

- Java 17+
- Node.js 14+
- PostgreSQL

## Installation

### Backend

1. Clone the repository:

```bash
git clone https://github.com/username/java-spring-react-todo-app.git
cd java-spring-react-todo-app/backend
```

2. Clone the repository:
```
   ./mvnw clean install
   ./mvnw spring-boot:run
```

3. Create your PostgreSQL database and update the application.properties file with your database credentials:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/tododb
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Frontend

1. Navigate to the frontend directory:
   
   ``` cd ../frontend ```

2. Install dependencies and start the application:
   ```
   npm install
   npm start
   ```

## Usage

   1. The backend application will run on http://localhost:6767 by default.
   2. The frontend application will run on http://localhost:3000 by default.
   3. Open your browser and navigate to http://localhost:3000 to start using the TODO application.

## Project Structure

### Backend
```
backend
├── src
│   ├── main
│   │   ├── java
│   │   │   └── org
│   │   │       └── bugrahan
│   │   │           └── todoapp
│   │   │               ├── controller
│   │   │               ├── dal
│   │   │               ├── dto
│   │   │               ├── entity
│   │   │               ├── mapper
│   │   │               ├── repository
│   │   │               └── TodoAppDataService.java
│   │   ├── resources
│   │   │   └── application.properties
│   └── test
│       └── java
│           └── org
│               └── bugrahan
│                   └── todoapp
```

### Frontend
```
frontend
├── src
│   ├── components
│   │   ├── TodoInput.js
│   │   ├── TodoItem.js
│   │   ├── TodoList.js
│   │   └── Popup.js
│   ├── App.js
│   ├── App.css
│   └── index.js
```

### API Endpoints

- POST /api/techcareer/todo/add - Adds a new task.

- PUT /api/techcareer/todo/update - Updates an existing task.

- DELETE /api/techcareer/todo/delete/{id} - Deletes the task with the specified ID.

- GET /api/techcareer/todo/find/all - Retrieves all tasks.

- GET /api/techcareer/todo/find/completed - Retrieves completed tasks.

- GET /api/techcareer/todo/find/notcompleted - Retrieves uncompleted tasks.

- DELETE /api/techcareer/todo/deleteCompletedAll - Deletes all completed tasks.

- DELETE /api/techcareer/todo/deleteAll - Deletes all tasks.


## Swagger Documentation

The TODO application includes Swagger for API documentation and testing. Swagger provides a user-friendly interface to interact with the API endpoints.

### Accessing Swagger UI

```
http://localhost:6767/swagger-ui.html
```


### Using Swagger UI

1. Open your browser and navigate to `http://localhost:6767/swagger-ui.html`.
2. Use the "Authorize" button to provide your credentials for authenticated endpoints.
```
   username: admin
   password: root
```
3. Explore and test the API endpoints directly from the Swagger UI interface.

### Swagger Configuration

Swagger is configured in the project using the `SwaggerConfig` class. The configuration ensures that all API endpoints are documented and accessible through the Swagger UI.

```java
package org.bugrahan.todoapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("basicAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("basicAuth",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
                .info(new Info()
                        .title("TodoApp API")
                        .version("1.0.0")
                        .description("Todo Application API documentation")
                        .termsOfService("Terms of service")
                        .contact(new Contact()
                                .name("Bugrahan")
                                .email("bugrahan@example.com")
                                .url("https://example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}
```

### Requirements for Swagger
```
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.0.3</version>
</dependency>
```
