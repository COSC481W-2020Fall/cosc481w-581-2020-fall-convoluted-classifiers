### During previous training, the cpu and files I/O have been significantly affect training process and speed.
 - [x] find a new way to train the model faster
 - [x] update tensorflow from 2.2 to 2.3
 - [x] using new functions to use buffered prefetching to load images and do the image augmentation in the model while training instead of doing it before compile the model.

Result:
 - [x] GPU utility increase from 40%~60% to 98%
 - [x] training model would not be limited by other hardware

### Our current model was trained on the InceptionV3 pre-trained model
 - [x] current result is not good enough for our application
 - [x] find a new pre-trained model for training on it to get a better result
 - [x] using ResNet152V2 (232 MB)
 - [x] using MoblieNetv2 (14 MB)

 Result:
 - [x] The accuracy of the model using MobileNetV2 can go up to 0.7 really quick in 10 epochs
 - [x] have to increase the accuracy

### Hyperparameter tuning
 - [x] have not got an ideal result
 
### Problems
 - [x] the accuracy does not increase over 0.8
 - [x] need help on build a model for better performance


### Nov 30th (update)
 - the model trained with the pre-trained model NasNetLarge has 94% accuracy which is good enough so far.

### Dec 7th (update)
 - prediction of cross breed dog will be displayed when the top 2 or top 3 predictions have similar confidence of results.
 - For higher confidence of cross breed dog prediction, we should retrain our model with more images and make more detailed features in each image be detected by the model.
