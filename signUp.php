<?php
    include "connect.php";
    $id = "";
    $username = $_POST['username'];
    $password = $_POST['pass'];
    $email = $_POST['email'];
    $phoneNumber = $_POST['phone_number'];

    $sql = "INSERT INTO USER(id, username, pass, email, phone_number)
            VALUE ('$id', '$username', '$password', '$email', '$phoneNumber')";

    try {
        $stmt = $pdo->prepare($sql);
        $stmt->execute();
    } catch (PDOException $e) {
        die("Query failed: " . $e->getMessage());
    }
?>