<?php
    header('Content-type: json/application; charset=utf-8');
    $host = "localhost";
    $db_name = "l90858az_fitboom";
    $username = "l90858az_fitboom";
    $password = "VDSfromChel!2004@";
    $connect = mysqli_connect($host, $username, $password, $db_name);
    $connect->set_charset("utf8");
    if (isset($_POST['email']) && isset($_POST['password']) && isset ($_GET['date_day'])){
        $counter_users = 0;
        $sql = mysqli_query($connect, "SELECT * FROM `users` WHERE `email` = '{$_POST['email']}' AND `password` = '{$_POST['password']}'");
        while ($result = mysqli_fetch_array($sql)){
            $counter_users += 1;
        }
        if ($counter_users > 0) {
            $sql = mysqli_query($connect, "SELECT * FROM `monthlyfood` WHERE `date_day` = '{$_GET['date_day']}'");
            if ($result = mysqli_fetch_array($sql)) {
                $res = [
                    "code" => 100,
                    "id" => $result['id'],
                    "date_day" => $result['date_day'],
                    "breakfast_name" => $result['breakfast_name'],
                    "lunch_name" => $result['lunch_name'],
                    "dinner_name" => $result['dinner_name'],
                    "breakfast_recept" => $result['breakfast_recept'],
                    "lunch_recept" => $result['lunch_recept'],
                    "dinner_recept" => $result['dinner_recept'],
                    "breakfast_kkal" => $result['breakfast_kkal'],
                    "lunch_kkal" => $result['lunch_kkal'],
                    "dinner_kkal" => $result['dinner_kkal']
                ];
                $res = array($res);
                $array = array(
                    'monthlyfood' => $res,
                );
                echo json_encode($array, JSON_UNESCAPED_UNICODE);
                exit();
            }
            else {
                $res = [
                    "code" => 103,
                    "message" => "The wrong date_day was entered"
                ];
                $res = array($res);
                $array = array(
                    'monthlyfood' => $res,
                );
                echo json_encode($array);
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
                'monthlyfood' => $res,
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
            'monthlyfood' => $res,
        );
        echo json_encode($array);
        exit();
    }
    
?>