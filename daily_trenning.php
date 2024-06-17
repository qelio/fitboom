<?php
    header('Content-type: json/application; charset=utf-8');
    $host = "localhost";
    $db_name = "l90858az_fitboom";
    $username = "l90858az_fitboom";
    $password = "VDSfromChel!2004@";
    $connect = mysqli_connect($host, $username, $password, $db_name);
    $connect->set_charset("utf8");
    if (isset($_POST['email']) && isset($_POST['password']) && isset($_GET['day']) && isset($_GET['month']) && isset($_GET['year']) && isset($_GET['trenning_type'])){
        $id = 0;
        $sql = mysqli_query($connect, "SELECT * FROM `users` WHERE `email` = '{$_POST['email']}' AND `password` = '{$_POST['password']}'");
        if ($result = mysqli_fetch_array($sql)){
            $id = $result['id'];
        }
        if ($id != 0) {
            $date = date ("d.m.Y H:i");
            //$sql = mysqli_query($connect, "SELECT * FROM `daily_trenning` WHERE `user_id` = '{$id}' AND `date_day` = '{$_GET['day']}' AND `date_month` = '{$_GET['month']}' AND `date_year` = '{$_GET['year']}' AND `trenning_type` = '{$_GET['trenning_type']}'");
            $sql = mysqli_query($connect, "SELECT `daily_trenning`.*, `trennings`.`exercise_id_1`, `trennings`.`exercise_id_2`, `trennings`.`exercise_id_3`, `trennings`.`exercise_id_4`, `trennings`.`exercise_id_5`, `trennings`.`exercise_id_6`, `trennings`.`exercise_1_param`, `trennings`.`exercise_2_param`, `trennings`.`exercise_3_param`, `trennings`.`exercise_4_param`, `trennings`.`exercise_5_param`, `trennings`.`exercise_6_param`, `trennings`.`exercise_1_type`, `trennings`.`exercise_2_type`, `trennings`.`exercise_3_type`, `trennings`.`exercise_4_type`, `trennings`.`exercise_5_type`, `trennings`.`exercise_6_type` FROM `daily_trenning` LEFT JOIN `trennings` ON `trennings`.`trenning_day` = `daily_trenning`.`date_day` AND `trennings`.`trenning_type` = `daily_trenning`.`trenning_type` WHERE `daily_trenning`.`user_id` = '{$id}' AND `daily_trenning`.`date_day` = '{$_GET['day']}' AND `daily_trenning`.`date_month` = '{$_GET['month']}' AND `daily_trenning`.`date_year` = '{$_GET['year']}' AND `daily_trenning`.`trenning_type` = '{$_GET['trenning_type']}'");
            if ($result = mysqli_fetch_array($sql)){
                $res = [
                    "code" => 100,
                    "id" => $result['id'],
                    "user_id" => $result['user_id'],
                    "trenning_type" => $result['trenning_type'],
                    "date_day" => $result['date_day'],
                    "date_month" => $result['date_month'],
                    "date_year" => $result['date_year'],
                    "exercise_1" => $result['exercise_1'],
                    "exercise_2" => $result['exercise_2'],
                    "exercise_3" => $result['exercise_3'],
                    "exercise_4" => $result['exercise_4'],
                    "exercise_5" => $result['exercise_5'],
                    "exercise_6" => $result['exercise_6'],
                    "exercise_id_1" => $result['exercise_id_1'],
                    "exercise_id_2" => $result['exercise_id_2'],
                    "exercise_id_3" => $result['exercise_id_3'],
                    "exercise_id_4" => $result['exercise_id_4'],
                    "exercise_id_5" => $result['exercise_id_5'],
                    "exercise_id_6" => $result['exercise_id_6'],
                    "exercise_1_param" => $result['exercise_1_param'],
                    "exercise_2_param" => $result['exercise_2_param'],
                    "exercise_3_param" => $result['exercise_3_param'],
                    "exercise_4_param" => $result['exercise_4_param'],
                    "exercise_5_param" => $result['exercise_5_param'],
                    "exercise_6_param" => $result['exercise_6_param'],
                    "exercise_1_type" => $result['exercise_1_type'],
                    "exercise_2_type" => $result['exercise_2_type'],
                    "exercise_3_type" => $result['exercise_3_type'],
                    "exercise_4_type" => $result['exercise_4_type'],
                    "exercise_5_type" => $result['exercise_5_type'],
                    "exercise_6_type" => $result['exercise_6_type'],
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
                if ($_GET['day'] % 4 == 0) {
                    $sql = mysqli_query($connect, "INSERT INTO `daily_trenning` (`user_id`, `trenning_type`, `date_day`, `date_month`, `date_year`, `exercise_1`, `exercise_2`, `exercise_3`, `exercise_4`, `exercise_5`, `exercise_6`, `trenning_date`) VALUES ('{$id}', '{$_GET['trenning_type']}', '{$_GET['day']}', '{$_GET['month']}', '{$_GET['year']}', '1', '1', '1', '1', '1', '1', '{$date}')");
                }
                else {
                    $sql = mysqli_query($connect, "INSERT INTO `daily_trenning` (`user_id`, `trenning_type`, `date_day`, `date_month`, `date_year`, `exercise_1`, `exercise_2`, `exercise_3`, `exercise_4`, `exercise_5`, `exercise_6`, `trenning_date`) VALUES ('{$id}', '{$_GET['trenning_type']}', '{$_GET['day']}', '{$_GET['month']}', '{$_GET['year']}', '0', '0', '0', '0', '0', '0', '{$date}')");
                }
                $sql = mysqli_query($connect, "SELECT `daily_trenning`.*, `trennings`.`exercise_id_1`, `trennings`.`exercise_id_2`, `trennings`.`exercise_id_3`, `trennings`.`exercise_id_4`, `trennings`.`exercise_id_5`, `trennings`.`exercise_id_6`, `trennings`.`exercise_1_param`, `trennings`.`exercise_2_param`, `trennings`.`exercise_3_param`, `trennings`.`exercise_4_param`, `trennings`.`exercise_5_param`, `trennings`.`exercise_6_param`, `trennings`.`exercise_1_type`, `trennings`.`exercise_2_type`, `trennings`.`exercise_3_type`, `trennings`.`exercise_4_type`, `trennings`.`exercise_5_type`, `trennings`.`exercise_6_type` FROM `daily_trenning` LEFT JOIN `trennings` ON `trennings`.`trenning_day` = `daily_trenning`.`date_day` AND `trennings`.`trenning_type` = `daily_trenning`.`trenning_type` WHERE `daily_trenning`.`user_id` = '{$id}' AND `daily_trenning`.`date_day` = '{$_GET['day']}' AND `daily_trenning`.`date_month` = '{$_GET['month']}' AND `daily_trenning`.`date_year` = '{$_GET['year']}' AND `daily_trenning`.`trenning_type` = '{$_GET['trenning_type']}'");
                if ($result = mysqli_fetch_array($sql)){
                    $res = [
                        "code" => 100,
                        "id" => $result['id'],
                        "user_id" => $result['user_id'],
                        "trenning_type" => $result['trenning_type'],
                        "date_day" => $result['date_day'],
                        "date_month" => $result['date_month'],
                        "date_year" => $result['date_year'],
                        "exercise_1" => $result['exercise_1'],
                        "exercise_2" => $result['exercise_2'],
                        "exercise_3" => $result['exercise_3'],
                        "exercise_4" => $result['exercise_4'],
                        "exercise_5" => $result['exercise_5'],
                        "exercise_6" => $result['exercise_6'],
                        "exercise_id_1" => $result['exercise_id_1'],
                        "exercise_id_2" => $result['exercise_id_2'],
                        "exercise_id_3" => $result['exercise_id_3'],
                        "exercise_id_4" => $result['exercise_id_4'],
                        "exercise_id_5" => $result['exercise_id_5'],
                        "exercise_id_6" => $result['exercise_id_6'],
                        "exercise_1_param" => $result['exercise_1_param'],
                        "exercise_2_param" => $result['exercise_2_param'],
                        "exercise_3_param" => $result['exercise_3_param'],
                        "exercise_4_param" => $result['exercise_4_param'],
                        "exercise_5_param" => $result['exercise_5_param'],
                        "exercise_6_param" => $result['exercise_6_param'],
                        "exercise_1_type" => $result['exercise_1_type'],
                        "exercise_2_type" => $result['exercise_2_type'],
                        "exercise_3_type" => $result['exercise_3_type'],
                        "exercise_4_type" => $result['exercise_4_type'],
                        "exercise_5_type" => $result['exercise_5_type'],
                        "exercise_6_type" => $result['exercise_6_type'],
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