from imageai.Prediction.Custom import ModelTraining
from os.path import join
from os import listdir

model_trainer = ModelTraining()
model_trainer.setModelTypeAsResNet()
model_trainer.setDataDirectory(join(".", "image_dataset"))
model_trainer.trainModel(
    num_objects=len(listdir(join(".", "image_dataset", "Images"))),
    num_experiments=10,
    batch_size=32,
    show_network_summary=True,
    # transfer_from_model=join(".", "models", "resnet.h5"),
    # save_full_model=join(".", "models", "resnet_custom.h5")
    )
