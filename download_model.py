import requests

def download_model () :
    print("Downloading yolo v3 model, this may take a moment...")
    url = "https://github.com/OlafenwaMoses/ImageAI/releases/download/1.0/yolo.h5"
    r = requests.get(url)
    with open("./models/yolo.h5",'wb') as f:
        f.write(r.content) 