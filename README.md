# Dog Breed Detector v0.0.1
This is an open source dog breed detector.

# Install
1. [Install python from python.org](https://www.python.org/downloads/)
2. Clone/download the repository
   - Download zip
   - `git clone https://github.com/COSC481W-2020Fall/cosc481w-581-2020-fall-convoluted-classifiers.git`
3. Create a venv (using bash) (Windows recommended using git terminal, or ubuntu shell)
    1. `python -m venv venv`
    2. `pip install -r requirements.txt`

# Prototype Description

For our prototype, we would like to create a way to automatically detect a canine animal inside an image file using Python. We will have a folder in our Github repository containing approximately 100 canine images that our program will search through. We chose to start with 100 images so that we had a large enough sample size to test with. Our Python program will use the tensorflow, openCV, keras, and imageAI modules and it will be able to detect which of the images in that folder are of canines and output "yes" if it is a dog and "no" if it is not. 

The user will simply be prompted to enter the given file/directory that they wish to examine. After the file/directory is entered no more prompts are given and no more input is needed from the user.Using the os module in Python, the program will be able to accept both image files and directories. If the user selects for a file, the program will output just a "yes" or "no". If the user selects for a directory, the program will output the image filename followed by a "yes" or "no". The program will also create and output files in the hardcoded directory ./.output/dog{n}.png, where n is the number of images found with dogs, that will contain the images entered by the user now each marked with a box around the dog that was detected in every positive image file. The program will then exit. If no valid image extensions are found, the program will say "no valid images" and will exit.

We will be using the command line prompt to prompt the user, provide user input,run the program, and display any output. To verify that our program is accurate we will manually check the output against the correctly labeled images that do identify a dog. 

