from detect import *
import argparse
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

def main(args):
    if (args.test_text):
        print(args.test_text)
    
    detect(args.test_location, args.output_location)

if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument('--test_text', default=None,
                        help='Test text to just print to see if working [default: None]')
    parser.add_argument('--test_location', default='.positives/',
                        help='The location of the image, or directory of images, for testing')
    parser.add_argument('--output_location', default='.output/',
                        help='The location of the output directory for positve outputs')


    args = parser.parse_args()

    main(args)
