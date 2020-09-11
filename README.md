# Dog Breed Detector
This is an open source dog breed detector.

# Install

## Windows
- Download python 3.8+
- create a venv **NAMED VENV**
- activate venv 
- install modules

## Linux
- Download python 3.8+
- create a venv **NAMED VENV**
- activate venv 
- install modules

# Prototype Description

For our prototype, we would like to create a way to automatically detect a canine animal inside an image file using Python. We will have a folder in our Github repository containing approximately 100 canine images that our program will search through. Our Python program will use the tensorflow, openCV, keras, and imageAI modules and it will be able to detect which of the images in that folder are of canines and output "yes" if it is a dog and "no" if it is not. 

The user will simply be prompted to enter the given file/directory that they wish to examine. After the file/directory is entered no more prompts are given and no more input is needed from the user.Using the os module in Python, the program will be able to accept both image files and directories. If the user selects for a file, the program will output just a "yes" or "no". If the user selects for a directory, the program will output the image filename followed by a "yes" or "no". The program will also create and output files in the hardcoded directory ./.output/dog{n}.png, where n is the number of images found with dogs, that will contain the images entered by the user now each marked with a box around the dog that was detected in every positive image file. The program will then exit. If no valid image extensions are found, the program will say "no valid images" and will exit.

We will be using the command line prompt to prompt the user, provide user input,run the program, and display any output. To verify that our program is accurate we will manually check the output against the correctly labled images that do identify a dog. 