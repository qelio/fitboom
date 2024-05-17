<?php
    // POST: login, name, pass, family, city, pol, email, dolg, imghref; GET: authorization_key
    header('Content-type: json/application; charset=utf-8');
    $host = "localhost";
    $db_name = "l90858az_fitboom";
    $username = "l90858az_fitboom";
    $password = "VDSfromChel!2004@";
    $connect = mysqli_connect($host, $username, $password, $db_name);
    $connect->set_charset("utf8");
    if (isset($_POST['email'])){
        $counter_users = 0;
        $sql = mysqli_query($connect, "SELECT * FROM `users` WHERE `email` = '{$_POST['email']}'");
        while ($result = mysqli_fetch_array($sql)){
            $counter_users += 1;
        }
        if ($counter_users > 0) {
            $res = [
                "code" => 100,
                "message" => "The user was successfully found"
            ];
            $res = array($res);
            $array = array(
                'сheck_user' => $res,
            );
            echo json_encode($array);
            exit();
        }
        else {
            $res = [
                "code" => 101,
                "message" => "The user was not found"
            ];
            $res = array($res);
            $array = array(
                'сheck_user' => $res,
            );
            echo json_encode($array);
            exit();
        }
        
    }
    else {
        $res = [
            "code" => 102,
            "message" => "One of the required fields was not passed"
        ];
        $res = array($res);
        $array = array(
            'сheck_user' => $res,
        );
        echo json_encode($array);
        exit();
    }
    
?>