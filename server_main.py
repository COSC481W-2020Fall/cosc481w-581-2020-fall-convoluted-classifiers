from predict import *
import tensorflow as tf
from os.path import join
from os import scandir
from time import time

# slience debugging logs and warning messages or 'deprecated' warning
tf.compat.v1.logging.set_verbosity(tf.compat.v1.logging.ERROR)
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '3'

# set memory growth equal to True for each GPU
gpus = tf.config.experimental.list_physical_devices('GPU')
if gpus:
    for gpu in gpus:
        tf.config.experimental.set_memory_growth(gpu, True)

# Main function, will contain main loop and self reset protical
def main():
    SCAN_PATH      = join("..", "images")
    OUTPUT_DIR     = join("..", "output")
    SECONDS_IN_DAY = 86400

    model = make_model(join("..", "models", "model_4"))
    labels = load_labels("labels.txt")

    dir_contents = scandir(SCAN_PATH)
    t0 = time()
    while (time() - t0 < SECONDS_IN_DAY):
        if len(dir_contents) != 0:
            image = dir_contents.pop(0)
            prediction = predict(join(SCAN_PATH, dir_contents), labels, model)
            with open(join(OUTPUT_DIR, image.split(".")+".txt", "w+")) as file:
                file.write(predict)

    
if __name__ == '__main__':
   main()
