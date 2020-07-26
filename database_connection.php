<?php
/**
 * The folowing is a copy of the php script used for creating a connection to the MySQL database used for 
 * this Android application
 */
require "../config_file.php";

// Creating Connection
$connection = mysqli_connect(DATABASE_SERVER, DATABASE_USERNAME, DATABASE_PASSWORD, DATABASE_NAME);

// Checking connection and if it fails an error is printed
if (!$connection) {
  echo "Connection NOT MADE";
}
