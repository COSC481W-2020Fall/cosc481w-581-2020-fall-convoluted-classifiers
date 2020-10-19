from download_images import download_images as di
from os.path import isdir, join

def separate():
    if not isdir(join(".", "image_dataset", "Images")):
        di()
    
    
    