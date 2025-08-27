# Office Chatbot Spring Boot Application

This README provides an overview of the Spring Boot application for the Office Chatbot system. The application serves as the backend for handling chat functionalities, including sending and receiving messages.

## Project Structure

The project is organized as follows:

```
spring-boot-app
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── officechat
│   │   │           ├── controller
│   │   │           │   └── ChatController.java
│   │   │           ├── service
│   │   │           │   └── ChatService.java
│   │   │           └── OfficeChatApplication.java
│   │   └── resources
│   │       ├── templates
│   │       │   └── chat.html
│   │       └── application.properties
├── Dockerfile
└── README.md
```

## Prerequisites

- Java 11 or higher
- Maven
- Docker (for containerization)

## Setup Instructions

1. **Clone the Repository**

   Clone the repository to your local machine:

   ```
   git clone <repository-url>
   cd office-chatbot/spring-boot-app
   ```

2. **Build the Application**

   Use Maven to build the application:

   ```
   mvn clean install
   ```

3. **Run the Application Locally**

   You can run the application using the following command:

   ```
   mvn spring-boot:run
   ```

   The application will start on `http://localhost:8080`.

4. **Docker Setup**

   To build and run the application using Docker, execute the following commands:

   ```
   docker build -t office-chatbot-spring-boot .
   docker run -p 8080:8080 office-chatbot-spring-boot
   ```

## Usage

- Access the chat interface by navigating to `http://localhost:8080/chat` in your web browser.
- The application supports multiple concurrent sessions for up to 10 users.

## Integration

This Spring Boot application communicates with a Python microservice for preprocessing and postprocessing chat messages. Ensure that the Python service is running and accessible for the chat functionality to work correctly.

## Documentation

For detailed information on the Python microservice, refer to the `README.md` file located in the `python-service` directory.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.