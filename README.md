[![Build Status](https://travis-ci.com/givanthak/spring-boot-rest-api-auth-jwt-tutorial.svg?branch=master)](https://travis-ci.com/givanthak/spring-boot-rest-api-auth-jwt-tutorial)
[![Known Vulnerabilities](https://snyk.io/test/github/givanthak/spring-boot-rest-api-tutorial/badge.svg)](https://snyk.io/test/github/givanthak/spring-boot-rest-api-tutorial)

# Sample REST CRUD API Securing with Spring Security and JWT

## Steps to Setup

**1. Clone the application**

```bash
https://github.com/givanthak/spring-boot-rest-api-auth-jwt-tutorial.git
```

**2. Create Mysql database**
```bash
create database user_database
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Build and run the app using maven**

```bash
mvn package
java -jar target/spring-boot-rest-api-auth-jwt-tutorial-0.0.1-SNAPSHOT.jar
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following CRUD APIs.

    GET /api/v1/users
    
    POST /api/v1/users
    
    GET /api/v1/users/{userId}
    
    PUT /api/v1/users/{userId}
    
    DELETE /api/v1/users/{userId}

    POST  /api/v1/auth/login
    
Create User 
    
    POST /api/v1/users
    
    {
    	"username": "givantha90",
    	"password": "welcome@123",
        "firstName": "Givantha",
        "lastName": "Kalansuriya",
        "email": "givanhta@gmail.com",
        "createdBy": "Givantha",
        "updatedBy": "Givantha"
    }
    
    POST  /api/v1/auth/login
    
    {
        "username": "givantha12",
        "password": "welcome@123"
    }
    
   
You can find the tutorial for this application on my blog -

<https://www.prathapgivantha.wordpress.com>
