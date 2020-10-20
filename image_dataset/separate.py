from download_images import download_images as di
from os.path import isdir, join
from os import listdir, mkdir
from shutil import rmtree, copyfile
from random import random

def separate(train_prc=.90):
    # Directories to ensure propper usage
    image_dir = join(".", "image_dataset", "Images")
    train_dir = join(".", "image_dataset", "Training")
    test_dir = join(".", "image_dataset", "Testing")
    
    # Making sure images are already downloaded, and if not downloading them
    if not isdir(image_dir):
        di()
    else:
        print("Images already downloaded")
    
    # ensures that Training, and Testing folder are created and empty
    print("Cleaning and creating the directory")
    clean(train_dir, test_dir)
    print("Done cleaning")
    
    breeds = listdir(image_dir)
    count = 0
    train = 0
    test  = 0
    for breed in breeds:
        dogs = listdir(join(image_dir, breed))
        mkdir(join(train_dir, breed))
        mkdir(join(test_dir, breed))

        for doggo in dogs:
            count += 1
            spl = random()
            if spl < train_prc:
                copyfile(
                    join(image_dir, breed, doggo),
                    join(train_dir, breed, doggo)
                )
                train += 1
            else:
                copyfile(
                    join(image_dir, breed, doggo),
                    join(test_dir, breed, doggo)
                )
                test += 1
    
    print(f"Of a total {count} dog images, {train} went to training and {test} went to testing")
    


def clean(train_dir, test_dir):
    # Making sure Training folder is created and empty
    if isdir(train_dir):
        rmtree(train_dir)
        mkdir(train_dir)
    else:
        mkdir(train_dir)
    
    # Making sure Training folder is created and empty
    if isdir(test_dir):
        rmtree(test_dir)
        mkdir(test_dir)
    else:
        mkdir(test_dir)
    


def main():
    separate()

if __name__ == '__main__':
    main()