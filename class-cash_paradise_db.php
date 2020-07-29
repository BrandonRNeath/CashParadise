<?php
/**
 * The folowing is a copy of the php script containing the class used for MySQL database communication for the Android 
 * application
 */
require "../config_file.php";
class CashParadiseDB
{
    private static $connection;
    function __construct()
    {
        // Creating Connection
        self::$connection = mysqli_connect(DATABASE_SERVER, DATABASE_USERNAME, DATABASE_PASSWORD, DATABASE_NAME);
        // Checking connection and if it fails an error is printed
        if (!self::$connection) {
            echo "Connection NOT MADE";
        } else {
            echo "Connection MADE";
        }
    }
}
//EOF
