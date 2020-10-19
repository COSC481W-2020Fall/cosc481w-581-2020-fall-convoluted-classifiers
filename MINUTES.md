# Table of Contents
- [Table of Contents](#table-of-contents)
- [09-08-2020](#09-08-2020)
- [09-11-2020](#09-11-2020)
- [09-18-2020](#09-18-2020)
- [09-25-2020](#09-25-2020)
- [10-05-2020](#10-05-2020)
- [10-12-2020](#10-12-2020)
- [Meeting 10-19-2020](#meeting-10-19-2020)

# 09-08-2020
- Discuss tentative items and make them actual
- Discuss prototype parameters
  - Python script that detects if a dog(?) is in an image
- Discuss what we are going to have for next meeting
  - Modules that detect items in images
  - (?) Detect dogs

# 09-11-2020
- Meeting started at 11:30 am
- Attendance:
  - All present
- [Main modules to use](https://stackabuse.com/object-detection-with-imageai-in-python/)
  - TensorFlow
  - Keras
  - OpenCV
  - ImageAI
  - TinyYOLOv3
- Training data
  - http://imagenet.org/
  - https://www.kaggle.com/chetankv/dogs-cats-images
  - http://vision.stanford.edu/aditya86/ImageNetDogs/ 
  - Ideas for negatives?
- Finding data
  - Find 20+ positive each
  - Positive image folder
  - Due Tuesday 09-15-2020
  - At least a few images by the 14th
  - Images should be uploaded in branch called "positives"
- Next meeting
  - 09-18-2020 @ 11:00 am
  - In same Zoom room
- Discussed prototype
  - Command line input/output
  - input: path to an image
  - output: has dog yes/no
- Karen and Baylie are going to begin work on front end
- Baylie finished prototype description
- Preferred Communication
  - Slack
  - Check a few times a day
- Jacob, Yao, and Cassie will have a proto-prototype deliverable by next meeting
  - Make sure to comment everything very well
- Meeting ended at 12:13pm

# 09-18-2020
- Minutes of this meeting mostly lost
- Video and Transcription of running demo
  - https://emich.zoom.us/rec/share/dT4l6nBjBXh0pBqhy7dtikEqk4Sj5kxmrLJ3uOdee2jF8Ot77XThOSpyM2HXmamU.UuCBWlLoPO0uSZyD

# 09-25-2020
- 11:03 AM
  - everyone is present
- Take a break if you need to, just let us know
- Small demo of the app emulator
- For backend EC2 or lambda
- AWS gives us a credit, if we need more contact Dr. Jiang
  - We have gotten an email at the beginning at the semester for a student
- Cassie is working on commenting the code
  - Has main done is working on detect
  - Will do download_model too
- Cassie will begin on gathering cropped images of dogs
- Yao is working on research for classification model
- Working on the persona
- Jacob will look-up how to silence warning
- Add more positives and negatives if you have some
  - create a branch with them and create a pull request
- Meeting time change to Monday at 7:30pm
  - Next meeting is monday Oct 5th

# 10-05-2020
- Meeting called to order at 7:35 pm
  - All present
- Possible datasets
  - [x] [Stanford](http://vision.stanford.edu/aditya86/ImageNetDogs/)
  - [ ] [Pedigree](https://dogs.pedigreeonline.com/breeds/)
- Yao and Jacob will get to work training the model (Yao will lead)
  - Example Models
    - https://github.com/saksham789/DOG-BREED-CLASSIFICATION-STANFORD-DOG-DATASET 
    - https://github.com/zrsmithson/Stanford-dogs 
- Architecture
  - Capture Image
  - Send to S3 bucket
  - Trigger lambda function(s)
  - return box around dog and breed
- App
  - Send/Recieve Bitmap image and receive breed as text
  - Find out how to export a bitmap from the app (for the request)
- Image cropping
  - Working on it, likely done tomorrow
  - https://imageai.readthedocs.io/en/latest/detection/
- Make an activities log for items that don't necessarily have commits
- Make tasks clearer and more actionable as research goes on
- Adjourn meet at 8:02pm

# 10-12-2020
- Meeting called to order at 7:38
  - Everyone present
  - Yao is tardy
- Cropping images
  - Complete ready to merge
  - Cropped image will be returned
  - Front end different sizing will be fine
- Classification
  - Transfer learning a model
  - 2 weeks will have a model for continued training
    - Small progress report for next week
  - Local training
    - If too long, will do on AWS sagemaker
  - Following some tutorials
    - [Kaggle](https://www.kaggle.com/halmogdady/dl-transfer-learning-multi-cnn-s-comparison)
  - Going to find a stanford model?
  - Looking at a YOLO model, training and using it as a classifier
- Pivot?
  - No
  - AWS cli will be able to handle sending image to S3
  - AWS can handle rest
  - Looking at AWS stuff thursday 7pm
    - Will record meeting for cassie
- Wrap-up
  - Jacob will look at yolo to detect dog breeds
  - Yao will look at different models
  - Jacob, Karen, Baylie, Yao, and Dr. Jiang will set up AWS thursday
- Meeting ended at 8:04pm

# Meeting 10-19-2020
- 