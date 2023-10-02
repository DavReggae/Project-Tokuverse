<?php
    try
    {
        $pdo = new PDO("mysql:host=sql3.freesqldatabase.com;dbname=sql3649857", "sql3649857", "Lplvvb2Ajh");
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        echo "success";
    }
    catch(PDOException $e)
    {
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    }
    
    
    
?>
