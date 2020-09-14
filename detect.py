# https://stackabuse.com/object-detection-with-imageai-in-python/
from imageai.Detection import ObjectDetection
import os


if __name__ == '__main__':
    detector = ObjectDetection()
    
    model_path = "./models/yolo-tiny.h5"
    input_dir = "./.positives/"
    images = os.listdir(input_dir)
    output_dir = "./.output/"
    
    detector.setModelTypeAsTinyYOLOv3()
    detector.setModelPath(model_path)
    detector.loadModel()
    dog_detect = detector.CustomObjects(dog=True)

    for image in images:
        print(image)
        if image.split('.')[-1].lower() in ['png', 'jpg', 'jpeg']:
            detection = detector.detectCustomObjectsFromImage(
                custom_objects=dog_detect,
                input_image=f'{os.path.join(input_dir, image)}',
                output_image_path=f'{os.path.join(output_dir, image)}',
                minimum_percentage_probability=0
            )
            for eachItem in detection:
                print(f'{eachItem["name"]} : {eachItem["percentage_probability"]}')