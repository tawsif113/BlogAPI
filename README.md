# CRUD Blog Application

## Overview
The **CRUD Blog Application** is a RESTful API built using Spring Boot, providing functionality to create, read, update, and delete blog posts. It integrates advanced exception handling, validation, and service-layer architecture for clean and maintainable code.

## Features

### Core Functionalities
- **Create Blogs**: Add new blogs with mandatory fields like title and content.
- **Read Blogs**: Retrieve all blogs or a specific blog by ID.
- **Update Blogs**: Modify existing blogs using their unique ID.
- **Delete Blogs**: Remove blogs from the system by ID.

### Additional Features
- **Validation**: Ensures that all required fields are provided and valid.
- **Exception Handling**:
  - `NotFoundException`: Thrown when a requested blog is not found.
  - `DuplicateItemException`: Thrown when attempting to create a blog with a duplicate title.
  - Validation exceptions for invalid input fields.
- **Global Exception Handling**: Centralized error responses using `@ControllerAdvice`.

## Technologies Used
- **Spring Boot**: Core framework for application development.
- **Spring Data JPA**: Integration with database using JPA for data persistence.
- **PostgreSQL**: Relational database for storing blog data.
- **Jakarta Validation**: Ensures input data validity.
- **REST API Design**: Exposes endpoints for CRUD operations.

## Project Structure
```
com.tawsif.CRUDBlog
├── controllers
│   ├── BlogController.java       # Handles incoming API requests
│   ├── GlobalExceptionHandler.java # Manages global exception responses
├── execptions
│   ├── DuplicateItemException.java # Custom exception for duplicates
│   ├── NotFoundException.java     # Custom exception for not-found cases
├── models
│   ├── Blog.java                 # Entity class for blogs
├── repositories
│   ├── BlogRepository.java       # JPA repository for Blog entity
├── blogservice
│   ├── BlogService.java          # Business logic for blog operations
```

## Endpoints

### Blog Controller Endpoints
| HTTP Method | Endpoint                 | Description                  |
|-------------|--------------------------|------------------------------|
| `POST`      | `/api/v1/blogs/post`     | Create a new blog            |
| `GET`       | `/api/v1/blogs`          | Retrieve all blogs           |
| `GET`       | `/api/v1/blogs/{id}`     | Retrieve a blog by ID        |
| `PUT`       | `/api/v1/blogs/update`   | Update an existing blog      |
| `DELETE`    | `/api/v1/blogs/delete/{id}` | Delete a blog by ID          |

## Validations
- **Blog Fields**:
  - `title`: Mandatory, cannot be blank.
  - `content`: Mandatory, cannot be blank.

## Exception Handling

### Exceptions
- **`NotFoundException`**: Thrown when a blog with the specified ID does not exist.
- **`DuplicateItemException`**: Thrown when attempting to create a blog with a duplicate title.
- **`MethodArgumentNotValidException`**: Thrown for invalid field inputs during request validation.

### Global Exception Handler
Centralized handling of exceptions to return consistent and meaningful error responses.

- **Example Error Responses**:
  - **404 Not Found**:
    ```json
    {
      "message": "Blog not found"
    }
    ```
  - **409 Conflict**:
    ```json
    {
      "message": "Blog Already Exists"
    }
    ```
  - **400 Bad Request**:
    ```json
    {
      "title": "Title is mandatory",
      "content": "Content is mandatory"
    }
    ```
