from detect import *
import argparse


def main(args):                     #create the main function with input args
    if (args.test_text):            #if input is test, just run test
        print(args.test_text)
    
    detect(args.test_location, args.output_location)    #runs detect program with input and output locations

if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument('--test_text', default=None,
                        help='Test text to just print to see if working [default: None]')      #test to make sure program is working
    parser.add_argument('--test_location', default='.positives/',
                        help='The location of the image, or directory of images, for testing')  #input for location of image(s), default is .positives folder
    parser.add_argument('--output_location', default='.output/',
                        help='The location of the output directory for positve outputs')    #input for output location, default is output folder


    args = parser.parse_args()      #args input for function is parse module

    main(args)                  #call main function with args input