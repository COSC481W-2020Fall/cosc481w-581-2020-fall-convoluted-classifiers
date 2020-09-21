from detect import *
import argparse


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