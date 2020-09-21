# https://stackabuse.com/object-detection-with-imageai-in-python/
from imageai.Detection import ObjectDetection
from PIL import Image 
from os import path, listdir, makedirs
from sys import exit


def scan_image(image_file, output_file, min_prob):
    return_ = False

    detector = ObjectDetection()
    
    model_dir = path.join(".", "models")
    model_path = path.join(model_dir,"yolo.h5")
    if not path.isfile(model_path):
        from download_model import download_model
        download_model()

    detector.setModelTypeAsYOLOv3()
    detector.setModelPath(model_path)
    detector.loadModel()
    dog_detect = detector.CustomObjects(dog=True)

    returned_image, detection = detector.detectCustomObjectsFromImage(
        custom_objects=dog_detect,
        input_image=image_file,
        output_type='array',
        display_percentage_probability=False,
        display_object_name=False,
        minimum_percentage_probability=min_prob
    )

    if detection != []:
        for item in detection:
            print(f'{image_file} => {item["name"]}: {item["percentage_probability"]}')
        img = Image.fromarray(returned_image)
        img.save(output_file)
        return_ = True
    else:
        print(f'{image_file} => no dog found with {min_prob}% probability')
    return return_

def get_images(input):
    output = []
    if path.isfile(input):
        output = [input]
    elif path.isdir(input):
        output = [path.join(input, i) for i in listdir(input)]
    else:
        print(f"{input} is not found to be a file or directory.")
        exit()
    return output

def detect (input, output_path):
    images = get_images(input)
    if not path.isdir(output_path):
        print("not a valid directory, creating it")
        makedirs(output_path)

    found = 1
    min_probability = 60
    for image in images:
        if image.split('.')[-1].lower() in ['png', 'jpg', 'jpeg']:
            find = scan_image(
                image,
                path.join(output_path,f"dog{found}.jpg"),
                min_probability
            )
            if find:
                found += 1
        else:
            print(f'{image} is not a valid image file')


if __name__ == '__main__':
    detect(path.join(".", ".positives"), path.join(".", ".output"))
        
