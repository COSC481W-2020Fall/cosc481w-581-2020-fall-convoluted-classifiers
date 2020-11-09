import predict
import tensorflow as tf
from os.path import join, dirname, realpath
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
    HOME           = dirname(dirname(realpath(__file__)))
    SCAN_PATH      = join(HOME, "images")
    OUTPUT_DIR     = join(HOME, "output")
    COMPLETE_DIR   = join(HOME, "complete")
    SECONDS_IN_DAY = 86400
    debug = True

    model = predict.make_model(join(HOME, "models", "model_4"))
    labels = predict.load_labels("labels.txt")

    t0 = time()
    while (time() - t0 < SECONDS_IN_DAY):
        sleep(.05)
        try:
            dir_contents = scandir(SCAN_PATH)
            image     = join(SCAN_PATH, dir_contents[0])
            moved_img = join(COMPLETE_DIR, dir_contents[0])
            prediction = predict.predict(join(SCAN_PATH, dir_contents), labels, model)
            with open(join(OUTPUT_DIR, image.split(".")+".txt", "w+")) as file:
                file.write(prediction)
            rename(image, moved_img)
        except Exception as e:
            if debug: print(e)


    
if __name__ == '__main__':
   main()
