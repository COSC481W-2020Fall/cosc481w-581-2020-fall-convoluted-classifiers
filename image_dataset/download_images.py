from requests import get
from os.path import join, isfile, isdir
from os import listdir
from os import rename
from tarfile import open

def download_images () :                  # To download image dataset
    image_loc = join(".", "image_dataset")
    image_tar = join(image_loc, "images.tar")                   # set location
    if not isfile(image_tar):
        print("Downloading stanford dog dataset images, this will take a moment...")               # Print message so user doesn't think program isn't working
        url = "http://vision.stanford.edu/aditya86/ImageNetDogs/images.tar"                  # URL for the download
        r = get(url)

        with open(image_tar,'wb') as f:                        # open model and write content into it to run the program
            print("writing to file")
            f.write(r.content)
    
    image_folder = join(image_loc, "Images")
    if not isdir(image_folder):
        tar = open(image_tar)
        tar.extractall(image_loc)
        tar.close()

    dirs = [f for f in listdir(image_folder) if isdir(join(image_folder, f))]
    for d in dirs:
        breed = d.split("-", 1)[-1]
        rename(join(image_folder, d), join(image_folder, breed))

if __name__ == '__main__':
    download_images()
