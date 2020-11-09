import predict
import tensorflow as tf
from os.path import join
from os import scandir, environ, rename
from time import time, sleep

# slience debugging logs and warning messages or 'deprecated' warning
tf.compat.v1.logging.set_verbosity(tf.compat.v1.logging.ERROR)
environ['TF_CPP_MIN_LOG_LEVEL'] = '3'

# set memory growth equal to True for each GPU
gpus = tf.config.experimental.list_physical_devices('GPU')
if gpus:
    for gpu in gpus:
        tf.config.experimental.set_memory_growth(gpu, True)

# Main function, will contain main loop and self reset protical
def main():
    SCAN_PATH      = join("~", "images")
    OUTPUT_DIR     = join("~", "output")
    COMPLETE_DIR   = join("~", "complete")
    SECONDS_IN_DAY = 86400

    model = predict.make_model(join("..", "models", "model_4"))
    labels = predict.load_labels("labels.txt")

    t0 = time()
    while (time() - t0 < SECONDS_IN_DAY):
        sleep(.05)
        try:
            dir_contents = scandir(SCAN_PATH)
            image     = dir_contents[0]
            moved_img = join(COMPLETE_DIR, image.split('/')[-1])
            prediction = predict.predict(join(SCAN_PATH, dir_contents), labels, model)
            with open(join(OUTPUT_DIR, image.split(".")+".txt", "w+")) as file:
                file.write(prediction)
            rename(image, moved_img)
        except:
            pass


    
if __name__ == '__main__':
   main()
