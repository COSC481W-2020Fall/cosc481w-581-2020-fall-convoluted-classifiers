<?php
$target_dir = "upload/";
$target_file = $target_dir . basename($_FILES["file"]["name"]);

/* If file to store images does not exist, create it */
if (!file_exists($target_dir)) {
    mkdir($target_dir, 0777, true);
}
/* Store images in file */
if (move_uploaded_file($_FILES["file"]["tmp_name"], $target_dir.$_FILES['file']['name'])) {
   $status = 1;
}
