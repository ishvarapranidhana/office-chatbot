from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from preprocess import preprocess_text
from postprocess import postprocess_response

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

@app.post("/preprocess")
async def preprocess(data: dict):
    text = data.get("text", "")
    return {"processed_text": preprocess_text(text)}

@app.post("/postprocess")
async def postprocess(data: dict):
    response = data.get("response", "")
    return {"formatted_response": postprocess_response(response)}

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)