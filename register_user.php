<?php

/**
 * The folowing is a copy of the php script for registering user into the MySQL database
 */
require_once "class-cash_paradise_db.php";

// Username entered by the user
$username = $_POST["username"];

// Password entered by the user
$password = $_POST["password"];

$cashParadiseDB = new CashParadiseDB();

// Calling method to register user from MySQL database class
$cashParadiseDB->register_user($username, $password);

//EOF
