# https://stackabuse.com/object-detection-with-imageai-in-python/
from imageai.Detection import ObjectDetection
from PIL import Image 
from os import path, listdir, makedirs
from sys import exit


def scan_image(image_file, output_file, min_prob, detector):
    return_ = False

    

    returned_image, detection = detector.detectCustomObjectsFromImage(      #everything that is detected from the image
        custom_objects=detector.CustomObjects(dog=True),        #dog is set to true for presence of a dog
        input_image=image_file,             #image file is stored for positive dog
        output_type='array',
        display_percentage_probability=False,       #Don't display the probability here 
        display_object_name=False,                  #Don't display the object name
        minimum_percentage_probability=min_prob         #setting the minimum probability to 60
    )

    if detection != []:
        for item in detection:
            print(f'{image_file} => yes - {item["name"]}: {item["percentage_probability"]}')    #Output if dog is positive, image file, yes, probability
        img = Image.fromarray(returned_image)       #Save the returned image
        img.save(output_file)           #Save the image in the output file
        return_ = True
    else:
        print(f'{image_file} => no dog found with {min_prob}% probability')         #There is no dog found message
    return return_

def get_images(input):          #Find the images
    output = []             #output is empty
    if path.isfile(input):          #If the path is valid for file, continue
        output = [input]
    elif path.isdir(input):         #If the path is valid for a directory, traverse through the directory
        output = [path.join(input, i) for i in listdir(input)]
    else:
        print(f"{input} is not found to be a file or directory.")       #If no file or directory is found, display error message.
        exit()              #Exit program
    return output

def detect (input, output_path):            #Detection function
    images = get_images(input)          #input image
    if not path.isdir(output_path):
        print("not a valid directory, creating it")     #If the path is not a valid directory, it will create it
        makedirs(output_path)

    detector = ObjectDetection()            #Call the ObjectDetection method
    
    model_dir = path.join(".", "models")            #New directory that combines the path with .models 
    model_path = path.join(model_dir,"yolo.h5")     #Takes new directory and attaches it to yolo.h5
    if not path.isfile(model_path):
        from download_model import download_model           #Download model if it is not valid
        download_model()

    detector.setModelTypeAsYOLOv3()             #Call these 3 Model methods for detection
    detector.setModelPath(model_path)
    detector.loadModel()

    found = 1
    min_probability = 60            #Minimum probability is set to 60
    for image in images:
        if image.split('.')[-1].lower() in ['png', 'jpg', 'jpeg']:          #Check for a valid image file
            find = scan_image(
                image,      #Get the image
                path.join(output_path,f"dog{found}.jpg"),           #Name of the output file, with the number of found images
                min_probability,        #Check probability of dog
                detector            #run detector
            )
            if find:
                found += 1          #Add 1 to found number each time of positive result
        else:
            print(f'{image} is not a valid image file')         #Print error if not a valid image file


if __name__ == '__main__':
    detect(path.join(".", ".positives"), path.join(".", ".output"))         #Set defaults for input and output
        
