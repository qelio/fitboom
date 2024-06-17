<?php
    header('Content-type: json/application; charset=utf-8');
    $host = "localhost";
    $db_name = "l90858az_fitboom";
    $username = "l90858az_fitboom";
    $password = "VDSfromChel!2004@";
    $connect = mysqli_connect($host, $username, $password, $db_name);
    $connect->set_charset("utf8");
    if (isset($_POST['email']) && isset($_POST['password']) && isset($_GET['exercise_id'])){
        $id = 0;
        $sql = mysqli_query($connect, "SELECT * FROM `users` WHERE `email` = '{$_POST['email']}' AND `password` = '{$_POST['password']}'");
        if ($result = mysqli_fetch_array($sql)){
            $id = $result['id'];
        }
        if ($id != 0) {
            $sql = mysqli_query($connect, "SELECT * FROM `exercises` WHERE `id` = '{$_GET['exercise_id']}'");
            if ($result = mysqli_fetch_array($sql)) {
                $res = [
                    "code" => 100,
                    "id" => $result['id'],
                    "title" => $result['title'],
                    "description" => $result['description']
                ];
                $res = array($res);
                $array = array(
                    'current_exercise' => $res,
                );
                echo json_encode($array, JSON_UNESCAPED_UNICODE);
                exit();
            }
        }
        else {
            $res = [
                "code" => 101,
                "message" => "The wrong username or password was entered"
            ];
            $res = array($res);
            $array = array(
                'current_exercise' => $res,
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
            'current_exercise' => $res,
        );
        echo json_encode($array);
        exit();
    }
    
?>