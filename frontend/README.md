# 📱 SMS Splitter API - Design & Analysis

## 1. **Architecture Decisions**  
I used a clean **layered architecture** with Spring Boot backend and React frontend. The backend follows MVC pattern with clear separation: Controllers handle HTTP, Services contain business logic, and Models manage data validation. Frontend uses component composition with TypeScript for type safety and Tailwind for the styling. This structure ensures maintainability and testability.

## 2. **API Design**  
I implemented **header-based versioning** (`X-API-Version: 1.0.0`) instead of URL versioning for cleaner endpoints. The API follows REST conventions with consistent error responses containing field-level validation details. I chose semantic versioning to clearly communicate breaking vs. non-breaking changes to API consumers.

## 3. **State Management**  
I used **React's built-in useState** rather than other state management like Redux or AppContext because was a simple project. For this SMS splitting app there is no complex state sharing between components. This avoids overengineering while maintaining clean component boundaries and fast performance.

## 4. **Security**  
I implemented **input validation** with Bean Validation annotations, **CORS configuration** for cross-origin requests, and **secure error handling** that doesn't expose internal details. For production, I would add JWT authentication, rate limiting, and HTTPS enforcement, but they weren't necessary for this demo scope.

## 5. **Scalability & Maintainability**  
For enterprise scale, I would add: **database persistence** (PostgreSQL), **caching layer** (Redis), **containerization** (Docker/Kubernetes), and **monitoring** (Prometheus/Grafana). The current modular architecture supports these additions without major refactoring.

## 6. **Time Constraints**  
I focused on core functionality and skipped: User Authentication and writing all the posible cases for the tests.