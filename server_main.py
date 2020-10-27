from predict import *
import tensorflow as tf
import os

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
    pass

if __name__ == '__main__':
   main()
