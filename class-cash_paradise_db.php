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

    /**
     * @param $username username of the user to register into the database
     * @param $password password of the user to register into the database 
     */
    function register_user($username, $password)
    {
        if (self::check_user_exists($username) == true) {
            echo "User already exists";
        } else {
            echo "User can be added";

            // Password entered is hashed
            $hashed_password = password_hash($password, PASSWORD_DEFAULT);

            //Register user into database
            $mysqli_query = "INSERT INTO users(username,password) values ('$username','$hashed_password')";

            // Checking if registration was successful 
            if (self::$connection->query($mysqli_query) === TRUE) {
                echo "Registration successful";
            } else {
                echo "Error has occured " . $mysqli_query . "<br>" . self::$connection->error;
            }
        }
    }

    /**
     * @param $username username of the user to register into the database
     * @param $password password of the user to register into the database 
     */
    function login_user($username, $password)
    {
        if (self::check_user_exists($username)) {
            $mysqli_query = "SELECT users.username, users.password FROM users WHERE username LIKE '$username';";
            $result = mysqli_query(self::$connection, $mysqli_query);
            $fetchedRow = mysqli_fetch_assoc($result);

            // Verifies password fetched and password entered by the user
            if (password_verify($password, $fetchedRow["password"])) {
                echo "Welcome" . $fetchedRow["username"];
            } else {
                echo "Incorrect password";
            }
        } else {
            echo "User does not exist";
        }
    }

    /**
     * Carries out an MySQL SELECT query to check if a user within the database has the same username
     * that was entered
     * 
     * @param string $username is the username that is to be checked to see if it exists in database
     * @return boolean true is returned if a user already exists within the database else false
     */
    static function check_user_exists($username)
    {
        // MySQL SELECT query*
        $mysqli_query = "SELECT users.username FROM users WHERE username LIKE '$username';";
        // Result of the query made
        $result = mysqli_query(self::$connection, $mysqli_query);
        // If number of table rows have been returned is greater than 0 then 
        if (mysqli_num_rows($result) > 0) {
            return true;
        } else {
            return false;
        }
    }

    function __destruct()
    {
        self::$connection->close();
        echo "Connection CLOSED";
    }
}
//EOF
