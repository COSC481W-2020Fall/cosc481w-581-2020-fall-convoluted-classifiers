### During previous training, the cpu and files I/O have been significantly affect training process and speed.
 - [ ] find a new way to train the model faster
 - [ ] update tensorflow from 2.2 to 2.3
 - [ ] using new functions to use buffered prefetching to load images and do the image augmentation in the model while training instead of doing it before compile the model.

Result:
 - [ ] GPU utility increase from 40%~60% to 98%
 - [ ] training model would not be limited by other hardware

### Our current model was trained on the InceptionV3 pre-trained model
 - [ ] current result is not good enough for our application
 - [ ] find a new pre-trained model for training on it to get a better result
 - [ ] using ResNet152V2 (232 MB)
 - [ ] using MoblieNetv2 (14 MB)

 Result:
 - [ ] The accuracy of the model using MobileNetV2 can go up to 0.7 really quick in 10 epochs
 - [ ] have to increase the accuracy




```python

```
