from detect import *
import argparse


def main(args):
    if (args.test_text):
        print(args.test_text)

if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument('--test_text', default=None,
                        help='Test text to just print to see if working [default: None]')
    parser.add_argument('--test_locatoin', default='.positives/',
                        help='The location of the image, or directory of images, for testing')
    parser.add_argument('--output_locatoin', default='.output/',
                        help='The location of the output directory for positve outputs')


    args = parser.parse_args()

    main(args)