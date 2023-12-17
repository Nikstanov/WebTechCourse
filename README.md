# Movie Hub Project

This repository contains multiple versions of the Movie Hub project, showcasing the evolution of the application using various Java-based technologies and design patterns.

## Versions:

### Version 1 - JavaEE + JDBC

The initial version of Movie Hub was built using Java Enterprise Edition (JavaEE) and JDBC for database connectivity. This version follows the Model-View-Controller (MVC) pattern, providing a foundation for managing movie data. **JSP** (JavaServer Pages) was utilized for the views in this version.

### Version 2 - Java Spring + Spring Security + Hibernate

In the second iteration, Movie Hub was migrated to the Spring Framework, incorporating Spring Security for authentication and authorization, and Hibernate for data persistence. This version further enhances security features and introduces the power of the Spring ecosystem. **JSP** continued to be the view technology for this version.

### Version 3 - Java + Spring Boot + Spring JPA + Spring Security

The latest version of Movie Hub utilizes the Spring Boot framework, which simplifies the development process by providing conventions and defaults. This version continues to leverage Spring Data JPA for streamlined data access and management, and Spring Security for robust authentication and authorization. In this version, **Thymeleaf** is introduced as the new view template engine.

## Features:

- **Model-View-Controller (MVC) Pattern:** Each version adheres to the MVC pattern, ensuring a separation of concerns and maintainability.

- **Security:** Incorporates Spring Security in later versions to manage user authentication and authorization, enhancing the application's overall security.

- **Data Persistence:** Evolves from JDBC to Hibernate and eventually to Spring Data JPA for efficient and standardized data access.

- **Spring Boot:** The latest version embraces Spring Boot for a simplified and convention-over-configuration approach, improving development efficiency.

- **Pagination:** In all versions, users can browse a list of movies with a paginated view, providing a seamless and organized movie-watching experience.

- **Authentication and Authorization:** The project includes a secure user authentication and authorization system to protect user data and ensure controlled access.

- **Movie Reviews:** Users have the ability to add reviews for movies, contributing to a collaborative and engaging platform for sharing opinions about the featured films.

- **User Scores:** The system calculates users' scores based on their reviews, providing a dynamic and personalized experience.

- **Administration Page:** There is an administration page where administrators can control users, manage content, and oversee the overall functioning of the application.

- **Testing with JUnit 5 and Mockito:** For testing in Spring applications, JUnit 5 and Mockito were employed to ensure the reliability and correctness of the codebase.

## Technologies Used:

- **Java**
- **JavaEE**
- **Spring Framework**
- **Spring Boot**
- **Spring Security**
- **Hibernate**
- **Spring Data JPA**
- **JDBC**
- **JUnit 5**
- **Mockito**
- **JSP (JavaServer Pages)**
- **Thymeleaf**

Feel free to explore the different branches to view the source code for each version.
