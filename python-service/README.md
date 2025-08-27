# Python Chatbot Service

This README provides instructions for setting up and running the Python microservice for the office chatbot system.

## Overview

The Python microservice is built using FastAPI and is responsible for preprocessing and postprocessing chat messages. It interacts with the Ollama model to generate responses based on user input.

## Requirements

- Python 3.8 or higher
- Docker (for containerization)
- Docker Compose (for multi-container management)

## Installation

1. **Clone the repository:**

   ```bash
   git clone <repository-url>
   cd office-chatbot/python-service
   ```

2. **Install dependencies:**

   You can install the required Python packages using pip:

   ```bash
   pip install -r requirements.txt
   ```

## Running the Service

### Using Docker

To run the Python microservice using Docker, you can use the provided Dockerfile. Follow these steps:

1. **Build the Docker image:**

   ```bash
   docker build -t office-chatbot-python-service .
   ```

2. **Run the Docker container:**

   ```bash
   docker run -d -p 8000:8000 office-chatbot-python-service
   ```

### Using FastAPI Directly

If you prefer to run the service directly without Docker, you can use Uvicorn:

```bash
uvicorn app.main:app --host 0.0.0.0 --port 8000 --reload
```

## API Endpoints

- **POST /preprocess**: Accepts user input for preprocessing.
- **POST /postprocess**: Accepts the response from Ollama for postprocessing.

## Usage

Once the service is running, it will be accessible at `http://localhost:8000`. You can send requests to the API endpoints to interact with the chatbot functionality.

## Documentation

For detailed information on the implementation, refer to the source code in the `app` directory, which includes:

- `main.py`: Entry point for the FastAPI application.
- `preprocess.py`: Functions for preprocessing input text.
- `postprocess.py`: Functions for cleaning and formatting responses.
- `utils.py`: Utility functions for text manipulation.

## Contributing

Contributions are welcome! Please submit a pull request or open an issue for any enhancements or bug fixes.

## License

This project is licensed under the MIT License. See the LICENSE file for details.