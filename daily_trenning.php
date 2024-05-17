<?php
    header('Content-type: json/application; charset=utf-8');
    $host = "localhost";
    $db_name = "l90858az_fitboom";
    $username = "l90858az_fitboom";
    $password = "VDSfromChel!2004@";
    $connect = mysqli_connect($host, $username, $password, $db_name);
    $connect->set_charset("utf8");
    if (isset($_POST['email']) && isset($_POST['password']) && isset($_GET['day']) && isset($_GET['month']) && isset($_GET['year'])){
        $id = 0;
        $sql = mysqli_query($connect, "SELECT * FROM `users` WHERE `email` = '{$_POST['email']}' AND `password` = '{$_POST['password']}'");
        if ($result = mysqli_fetch_array($sql)){
            $id = $result['id'];
        }
        if ($id != 0) {
            $date = date ("d.m.Y H:i");
            $sql = mysqli_query($connect, "SELECT * FROM `daily_trenning` WHERE `user_id` = '{$id}' AND `date_day` = '{$_GET['day']}' AND `date_month` = '{$_GET['month']}' AND `date_year` = '{$_GET['year']}'");
            if ($result = mysqli_fetch_array($sql)){
                $res = [
                    "code" => 100,
                    "id" => $result['id'],
                    "user_id" => $result['user_id'],
                    "date_day" => $result['date_day'],
                    "date_month" => $result['date_month'],
                    "date_year" => $result['date_year'],
                    "exercise_1" => $result['exercise_1'],
                    "exercise_2" => $result['exercise_2'],
                    "exercise_3" => $result['exercise_3'],
                    "exercise_4" => $result['exercise_4'],
                    "exercise_5" => $result['exercise_5'],
                    "exercise_6" => $result['exercise_6'],
                    "trenning_date" => $result['trenning_date']
                ];
                $res = array($res);
                $array = array(
                    'daily_trenning' => $res,
                );
                echo json_encode($array, JSON_UNESCAPED_UNICODE);
                exit();
            }
            else {
                $date = date ("d.m.Y H:i");
                $sql = mysqli_query($connect, "INSERT INTO `daily_trenning` (`user_id`, `date_day`, `date_month`, `date_year`, `exercise_1`, `exercise_2`, `exercise_3`, `exercise_4`, `exercise_5`, `exercise_6`, `trenning_date`) VALUES ('{$id}', '{$_GET['day']}', '{$_GET['month']}', '{$_GET['year']}', '0', '0', '0', '0', '0', '0', '{$date}')");
                $sql = mysqli_query($connect, "SELECT * FROM `daily_trenning` WHERE `user_id` = '{$id}' AND `date_day` = '{$_GET['day']}' AND `date_month` = '{$_GET['month']}' AND `date_year` = '{$_GET['year']}'");
                if ($result = mysqli_fetch_array($sql)){
                    $res = [
                        "code" => 100,
                        "id" => $result['id'],
                        "user_id" => $result['user_id'],
                        "date_day" => $result['date_day'],
                        "date_month" => $result['date_month'],
                        "date_year" => $result['date_year'],
                        "exercise_1" => $result['exercise_1'],
                        "exercise_2" => $result['exercise_2'],
                        "exercise_3" => $result['exercise_3'],
                        "exercise_4" => $result['exercise_4'],
                        "exercise_5" => $result['exercise_5'],
                        "exercise_6" => $result['exercise_6'],
                        "trenning_date" => $result['trenning_date']
                    ];
                    $res = array($res);
                    $array = array(
                        'daily_trenning' => $res,
                    );
                    echo json_encode($array, JSON_UNESCAPED_UNICODE);
                    exit();
                }
            }
        }
        else {
            $res = [
                "code" => 101,
                "message" => "The wrong username or password was entered"
            ];
            $res = array($res);
            $array = array(
                'daily_trenning' => $res,
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
            'daily_trenning' => $res,
        );
        echo json_encode($array);
        exit();
    }
    
?>