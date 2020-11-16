from os import path
import tensorflow as tf
from tensorflow import keras
import numpy as np

# allocate GPU memory
gpus = tf.config.experimental.list_physical_devices('GPU')
if gpus:
    for gpu in gpus:
        tf.config.experimental.set_memory_growth(gpu, True)

# read in the labels of breeds
def load_labels(labels_path):
    labels_of_breeds = np.loadtxt("labels.txt", dtype=str)
    return labels_of_breeds

# load model will cost much time, so I take it out of prediction function.
def make_model(model_path):
    model = keras.models.load_model(model_path)
    return model

def scan_image(image_path):
    images = tf.keras.preprocessing.image.load_img(image_path,
                                                   target_size=(224,224)
                                                   )
    images_array = tf.keras.preprocessing.image.img_to_array(images)
    images_array = np.expand_dims(images_array, axis=0)

    return images_array

def predict(image_path, labels, my_model):
    prediction = my_model.predict(scan_image(image_path))
    pred = {
        "label": labels[np.argmax(prediction)],
        "confidence": 100 * np.max(prediction)
    }
    print("This image most likely belongs to {} with a {:.2f} percent confidence.".format(
        pred["label"],
        pred["confidence"]
    ))
    return pred

if __name__ == '__main__':
    prediction_model = make_model("model_4")
    predict("data/Training/n02097130-giant_schnauzer/n02097130_603.jpg", "labels.txt", prediction_model)

