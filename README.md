
# JobOffer

Overview
This is a monolithic web application built using the Spring Boot framework. The primary functionality of the application is to collect and display job listings for junior Java developers.

# Features
**User registration**

**JWT token-based authentication**

**Job listing viewing**
# How It Works
**User Registration:** Users must register an account to use the application.

**Token Request:** After registration, users need to request a JWT (JSON Web Token) for authentication purposes.

**Job Listings:** Authenticated users can browse through job listings specifically targeted at junior Java developers.

**Scheduling:** Job listings are updated periodically using a scheduler to ensure the latest job opportunities are available.
# Technologies Used
**Java:** 17

**Framework:** Spring Boot

**Modules:** Web (RestControllers), Test, Data MongoDB, Validation, Security + JWT

**Additional:** Spring Scheduler

**Database:** MongoDB + MongoExpress

**Caching:** Redis & Jedis & Redis-Commander

**Testing:**

**Unit Testing:** JUnit5, Mockito, AssertJ

**Integration Testing:** SpringBootTest, MockMvc, SpringSecurityTest, Wiremock, Testcontainers, Awaitility

**Mocking:** Wiremock

**Logging:** Log4j2

**HTTP:** RestTemplate, JSON, HTTP, MockMvc

**Documentation:** Swagger

**Build Tool:** Maven

**Version Control:** Git, GitHub/GitLab

**IDE:** IntelliJ Ultimate

**Deployment:** Docker & Docker-Compose & Docker Desktop


## API Reference
![image](https://github.com/user-attachments/assets/e9116e74-0d88-4ecd-97e6-54861e3a50e2)

#### User register

```http
  POST /register
```

| REQUEST                             | RESPONSE | FUNCTION                |
| :--------                           | :------- | :------------------------- |
| `JSON BODY (username and password)` | `JSON (username,message,status)` | register a user|

```http
  POST /token
```

| REQUEST                             | RESPONSE | FUNCTION                |
| :--------                           | :------- | :------------------------- |
| `JSON BODY (username and password)` | `JSON (username,token,message)` | grant user a token|

```http
  GET /offers
```

| REQUEST                             | RESPONSE | FUNCTION                |
| :--------                           | :------- | :------------------------- |
| `NON+token` | `JSON (offers)` | list all offers|

```http
  GET /offers
```

| REQUEST                             | RESPONSE | FUNCTION                |
| :--------                           | :------- | :------------------------- |
| `PathVariable id+token` | `JSON (offer)` | list offer by id|

```http
  POST /offers
```

| REQUEST                             | RESPONSE | FUNCTION                |
| :--------                           | :------- | :------------------------- |
| `JSON BODY (offer)+token` | `JSON (message,status)` | add offer|



