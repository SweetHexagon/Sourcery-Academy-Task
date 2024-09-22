# Sourcery Academy Task

This is a project for the Sourcery Academy task.

## Project Overview

This project demonstrates the implementation of a Spring Boot application with a Docker setup.

## Features

- Spring Boot backend
- RESTful API for managing books and their ratings
- Dockerized for easy deployment

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/SweetHexagon/Sourcery-Academy-Task.git
   ```
   
2. Build the project:
   ```bash
   ./gradlew clean build
   ```
3. Run the application:
   ```bash
   java -jar build/libs/library-0.0.1-SNAPSHOT.jar
    ```
4. Run the application:
   ```bash
   java -jar build/libs/library-0.0.1-SNAPSHOT.jar
   ```

## Docker setup
1. Build the Docker image:
   ```bash
   docker build -t library-app .
   ```

2. Run the Docker container
   ```bash
   docker run -d -p 8080:8080 library-app
   ```
## Some HTTP methods to test: 
Mock data:
* Books: Book1 (by Author1), Book2 (by Author2)
* Users: User1, User2, User3
* Ratings:
  1. Book1: Ratings by User1 (5), User2 (3), User3 (4)
  2. Book2: Ratings by User1 (4), User2 (5)
  
Tests:
1. Get Books Sorted by Rating:
* Method: GET
* URL: http://localhost:8080/books/sorted-by-rating?page=0

2. Get Books by Author:
* Method: GET
* URL: http://localhost:8080/books/by-author?author=Author1&page=0

3. Get Book by Title and Author:
* Method: GET
* URL: http://localhost:8080/books/by-title-author?title=Book1&author=Author1



  
