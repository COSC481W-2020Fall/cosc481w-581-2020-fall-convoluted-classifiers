import requests
from os.path import join

def download_model () :                  #To download yolo
    print("Downloading yolo v3 model, this may take a moment...")               #Print message so user doesn't think program isn't working
    url = "https://github.com/OlafenwaMoses/ImageAI/releases/download/1.0/yolo.h5"                  #URL for the download
    r = requests.get(url)
    local_model = join(".", join("models", "yolo.h5"))                      #join models and yolo into 1 model variable
    with open(local_model,'wb') as f:                        #open model and write content into it to run the program
        f.write(r.content) 
