def truncate_text(text, max_tokens):
    tokens = text.split()  # Simple tokenization by whitespace
    if len(tokens) > max_tokens:
        return ' '.join(tokens[:max_tokens])  # Truncate to max_tokens
    return text

def clean_response(response):
    # Implement any necessary cleaning for the response from Ollama
    return response.strip()  # Example: stripping whitespace from the response

def format_response(response):
    # Format the response as needed (e.g., JSON, plain text)
    return {
        "response": response
    }