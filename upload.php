<?php
$target_dir = "upload/";
$target_file = $target_dir . basename($_FILES["file"]["name"]);

if (!file_exists($target_dir)) {
    mkdir($target_dir, 0777, true);
}
if (move_uploaded_file($_FILES["file"]["tmp_name"], $target_dir.$_FILES['file']['name'])) {
   $status = 1;
}
