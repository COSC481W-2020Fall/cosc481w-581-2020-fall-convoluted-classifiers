import tensorflow as tf
from tensorflow import keras
import numpy as np
from tensorflow.keras.preprocessing.image import ImageDataGenerator

# allocate GPU memory
gpus = tf.config.experimental.list_physical_devices('GPU')
if gpus:
    for gpu in gpus:
        tf.config.experimental.set_memory_growth(gpu, True)

labels = np.loadtxt("labels.txt", dtype=str)
print(labels)

my_model = keras.models.load_model('model_4')
test_image = tf.keras.preprocessing.image.load_img(
    'data/Training/n02097130-giant_schnauzer/n02097130_603.jpg',
    target_size=(224,224)
)
test = tf.keras.preprocessing.image.img_to_array(test_image)
test = np.expand_dims(test, axis=0)

prediction = my_model.predict(test)
print(prediction)

classes = np.argmax(prediction, axis=1)
print(classes)

print(
    "This image most likely belongs to {} with a {:.2f} percent confidence."
    .format(labels[np.argmax(prediction)], 100 * np.max(prediction))
)

