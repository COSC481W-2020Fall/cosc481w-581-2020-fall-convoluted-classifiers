import requests
from os.path import join
from os.path import isfile
import tarfile

def download_images () :                  # To download image dataset
    local_images = join(".", join("image_dataset", "images.tar"))                      # set location
    if not isfile(local_images):
        print("Downloading stanford dog dataset images, this will take a moment...")               # Print message so user doesn't think program isn't working
        url = "http://vision.stanford.edu/aditya86/ImageNetDogs/images.tar"                  # URL for the download
        r = requests.get(url)

        with open(local_images,'wb') as f:                        # open model and write content into it to run the program
            print("writing to file")
            f.write(r.content)
    
    with open(local_images) as file:
        tar = tarfile.open(fileobj=file, bufsize=1000000)
        tar.extractall(members=tar.getmembers())

if __name__ == '__main__':
    download_images()
