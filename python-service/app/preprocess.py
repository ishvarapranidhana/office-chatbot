def preprocess_text(input_text, max_tokens=512):
    """
    Preprocess the input text for the Ollama model.
    
    Args:
        input_text (str): The text to preprocess.
        max_tokens (int): The maximum number of tokens allowed.
    
    Returns:
        str: The preprocessed text, truncated if necessary.
    """
    import tiktoken

    # Count tokens in the input text
    encoding = tiktoken.encoding_for_model("llama3")  # Adjust model name as needed
    tokens = encoding.encode(input_text)

    # Truncate if the number of tokens exceeds the limit
    if len(tokens) > max_tokens:
        tokens = tokens[:max_tokens]
        input_text = encoding.decode(tokens)

    return input_text

def validate_input(input_text):
    """
    Validate the input text to ensure it meets the requirements.
    
    Args:
        input_text (str): The text to validate.
    
    Returns:
        bool: True if valid, False otherwise.
    """
    if not input_text or len(input_text.strip()) == 0:
        return False
    return True