def postprocess_response(response):
    # Clean and format the response from the Ollama model
    cleaned_response = response.strip()  # Remove leading/trailing whitespace
    # Additional formatting can be added here as needed
    return cleaned_response

def format_response(response):
    # Format the response for better readability
    formatted_response = f"Chatbot: {response}"
    return formatted_response

def postprocess(input_data):
    response = input_data.get("response", "")
    cleaned_response = postprocess_response(response)
    formatted_response = format_response(cleaned_response)
    return {"formatted_response": formatted_response}