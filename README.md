# 📱 SMS Splitter API

A modern full-stack application that splits long text messages into SMS-compliant parts with intelligent character limits and part numbering.

## 🚀 Quick Start

### Prerequisites
- **Java 17+**
- **Node.js 18+**
- **Git**

### Backend Setup
```bash
cd backend
./gradlew bootRun
# API runs on http://localhost:8080
```

### Frontend Setup
```bash
cd frontend
npm install
npm run dev
# UI runs on http://localhost:5173
```

## 🏗️ Architecture & Design Decisions

### **Backend (Spring Boot)**
- **Layered Architecture**: Clean separation with Controllers, Services, and Models
- **Header-Based API Versioning**: `X-API-Version: 1.0.0` for backward compatibility
- **Bean Validation**: Input validation with custom error responses
- **CORS Configuration**: Secure cross-origin setup for development

### **Frontend (React + TypeScript)**
- **Component Composition**: Modular UI with single-responsibility components
- **Type Safety**: TypeScript interfaces for API contracts
- **Modern Styling**: Tailwind CSS with glass morphism effects
- **Environment Configuration**: API endpoints configurable via environment variables

### **API Versioning Strategy**
- **v1.0.0**: Basic SMS splitting (160/70 character limits)
- **v1.1.0**: Enhanced with part numbering ("... - Part X of Y")
- **Semantic Versioning**: Clear communication of breaking changes

## 📋 API Endpoints

### SMS Operations
```http
POST /api/sms/send
Headers: X-API-Version: 1.1.0
Content-Type: application/json

{
  "message": "Your long message here",
  "phoneNumber": "+1234567890"
}
```

### Health & Metadata
```http
GET /api/sms/health          # Health check
GET /api/sms/versions        # Supported API versions
```

## 🧪 Testing Strategy

### **Test Coverage**
- **5 Tests Total**: Focused on core functionality
- **Unit Tests**: Service logic (SMS splitting algorithms)
- **Integration Tests**: HTTP API endpoints with validation
- **Simple & Maintainable**: No complex mocking or overengineering

### **Run Tests**
```bash
cd backend
./gradlew test --rerun-tasks
```

## 💡 Key Features

✅ **Smart Character Limits**: 160 chars (standard) / 70 chars (Unicode)  
✅ **Word Boundary Splitting**: Avoids breaking words mid-sentence  
✅ **Part Numbering**: "... - Part 1 of 3" (last part has no "...")  
✅ **Input Validation**: Phone number format and message length validation  
✅ **Modern UI**: Responsive design with copy-to-clipboard functionality  
✅ **CORS Support**: Cross-origin requests configured for development  

## 🛠️ Technologies

| Layer | Technology | Purpose |
|-------|------------|---------|
| **Backend** | Spring Boot 3.5 | REST API framework |
| **Frontend** | React 18 + TypeScript | Modern UI development |
| **Styling** | Tailwind CSS | Utility-first styling |
| **Build** | Gradle + Vite | Build automation |
| **Testing** | JUnit 5 + MockMvc | Test automation |

## 📁 Project Structure

```
📦 SMS Splitter API
├── 📁 backend/              # Spring Boot API
│   ├── 📁 src/main/java/    # Application code
│   ├── 📁 src/test/java/    # Test cases
│   └── 📄 build.gradle      # Dependencies
├── 📁 frontend/             # React application
│   ├── 📁 src/components/   # UI components
│   ├── 📁 src/services/     # API integration
│   └── 📄 package.json     # Frontend dependencies
└── 📄 README.md            # This file
```

## 🎯 Design Philosophy

**Simplicity Over Complexity**: Built with essential features and clean architecture rather than over-engineering. The focus was on demonstrating modern development practices, proper API design, and maintainable code structure within time constraints.

**User Experience First**: SMS splitting logic considers real-world usage with smart word boundaries and clear part numbering that doesn't confuse recipients.

**Developer Experience**: Simple setup, clear documentation, and comprehensive testing make this project easy to understand and extend.



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