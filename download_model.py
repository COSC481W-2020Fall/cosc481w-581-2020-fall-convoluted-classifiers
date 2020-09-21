import requests
import os.path.join

def download_model () :
    print("Downloading yolo v3 model, this may take a moment...")
    url = "https://github.com/OlafenwaMoses/ImageAI/releases/download/1.0/yolo.h5"
    r = requests.get(url)
    local_model = join(".", join("models", "yolo.h5"))
    with open(local_model,'wb') as f:
        f.write(r.content) 