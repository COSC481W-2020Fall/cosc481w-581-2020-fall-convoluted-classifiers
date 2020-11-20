# Table of Contents
- [Table of Contents](#table-of-contents)
- [09-08-2020](#09-08-2020)
- [09-11-2020](#09-11-2020)
- [09-18-2020](#09-18-2020)
- [09-25-2020](#09-25-2020)
- [10-05-2020](#10-05-2020)
- [10-12-2020](#10-12-2020)
- [10-19-2020](#10-19-2020)
- [10-26-2020](#10-26-2020)
- [11-02-2020](#11-02-2020)
- [11-09-2020](#11-09-2020)
- [11-16-2020](#11-16-2020)
- [11-20-2020](#11-20-2020)

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

# 10-19-2020
- Meeting called to order at 7:33pm
  - All present
- [Meeting Recording](https://emich.zoom.us/rec/share/B5dDNAv6AICOHm40H_s2JmUhsx_SI_hdvexNR5y0kxVMWPGuRHs88_tA8D3JQP32.kzRHZ3k1AHfZUL4x)
- [Meeting Transcript](https://emich.zoom.us/rec/share/pXK_OCiVCN70EJh079XmtTwmeHInDEPAvD4xQqO7vJ-NFoQHMrVE6AQm6uwjDhks.08tKxHoCJSM_i2pc)
- Dr. Jiang shows us how to connect Android studio with AWS
  - [Her tutorial](https://www.youtube.com/watch?v=b7GPq6xUjfE&ab_channel=RobinsonProgramming.com)
  - https://stackoverflow.com/questions/2914105/what-is-install-parse-failed-no-certificates-error
  - https://stackoverflow.com/questions/45940861/android-8-cleartext-http-traffic-not-permitted 
- Meeting adjured at 8:22pm

# 10-26-2020
- Meeting called to order at 7:34 pm
  - Everybody present
- Karen won't be present at in-class discussion Monday Nov. 2nd
- This week should be able to get project feature complete
- Tasks:
  - Cassie and Jacob: (Meeting at 7:30 Wed)
    - Server stuff and flask
    - Python 3.7
    - Set up users also
    - GPUs on the server?
  - Yao and Jacob: Adjust code for an always hot due by Wed @ 7:30
  - Yao and Dr. Jiang: Will begin training more and better(?) model for eventually
  - Yao: Compress current model and upload to EC2 instance
  - Bailey, Karen, and Jacob: Well meet and iron everything out with the app
- Meeting adjured at 7:54pm
  
# 11-02-2020
- Meeting called to order at 7:32 pm
  - Yao Absent, else present
- Goals for the week
  - Connect app to server
  - Send image to server
  - Retrieve breed from server
  - Testing detection
  - Clean up github repo
  - Build better model
- Meeting adjured at 7:40 pm

# 11-09-2020
- Meeting called to order at 7:34 pm
  - All present
- Backend Code review
  - Jacob will copy the code and get a textual version for public view
- Frontend Code review
- [Code review](https://emich.zoom.us/rec/share/0Gn5rSo_2Dk1NlhY0B2o8mJXASYrpqicy3sbpTQ4H6v4YBz7symaWLfvl205JKkE.MkXQuwnz072ztiwX)

# 11-16-2020
- Meeting called to order at 7:35 pm
- Finalize tasks
  - Yao:
    - Continue work on model stuff
  - Karen and Baylie:
    - Implementing more of the wireframe?
    - Small list of things to do
    - Cosmetic changes
    - DataTitans informational
    - Progress bar
    - Place holder image
    - Prettify the result
    - Scaling
    - Concerns:
      - History page
      - Settings page, how to implement?
        - Figure out how to send info to server, backend will figure out everything else
  - Cassie:
    - Finishing up cleaning up repo
    - Getting a more appropriate sized EC2 to cost less
  - Jacob:
    - Work on flask server that receives setting input
    - Tweaking server_main to ensure better uptime
- Meeting Adjured at 7:51 pm

# 11-20-2020
- Meeting called to order at 11:02 am
- Look at contract and submit
- Additional tasks:
  - Jacob:
    - Figure out url '?add_db=True', etc. syntax
  - Yao:
    - More training!
  - Cassie:
    - Additional server maintenance to decrease cost
  - Baylie and Karen:
    - Gallery access for uploading photo
- Meeting adjured at 11:27 am