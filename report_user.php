<?php
    header('Content-type: json/application; charset=utf-8');
    $host = "localhost";
    $db_name = "l90858az_fitboom";
    $username = "l90858az_fitboom";
    $password = "VDSfromChel!2004@";
    $connect = mysqli_connect($host, $username, $password, $db_name);
    $connect->set_charset("utf8");
    if (isset($_POST['email']) && isset($_POST['password']) && isset($_GET['report_type']) && isset($_GET['date_day']) && isset($_GET['date_month']) && isset($_GET['date_year'])){
        $id = 0;
        $sql = mysqli_query($connect, "SELECT * FROM `users` WHERE `email` = '{$_POST['email']}' AND `password` = '{$_POST['password']}'");
        if ($result = mysqli_fetch_array($sql)){
            $id = $result['id'];
        }
        if ($id != 0) {
            if ($_GET['report_type'] == "exercises") {
                $exercises = 0;
                for ($i = 0; $i <= $_GET['date_day']; $i++) {
                    $sql = mysqli_query($connect, "SELECT `daily_trenning`.*, `trennings`.`exercise_id_1`, `trennings`.`exercise_id_2`, `trennings`.`exercise_id_3`, `trennings`.`exercise_id_4`, `trennings`.`exercise_id_5`, `trennings`.`exercise_id_6`, `trennings`.`exercise_1_param`, `trennings`.`exercise_2_param`, `trennings`.`exercise_3_param`, `trennings`.`exercise_4_param`, `trennings`.`exercise_5_param`, `trennings`.`exercise_6_param`, `trennings`.`exercise_1_type`, `trennings`.`exercise_2_type`, `trennings`.`exercise_3_type`, `trennings`.`exercise_4_type`, `trennings`.`exercise_5_type`, `trennings`.`exercise_6_type` FROM `daily_trenning` LEFT JOIN `trennings` ON `trennings`.`trenning_day` = `daily_trenning`.`date_day` AND `trennings`.`trenning_type` = `daily_trenning`.`trenning_type` WHERE `daily_trenning`.`user_id` = '{$id}' AND `daily_trenning`.`date_day` = '{$i}' AND `daily_trenning`.`date_month` = '{$_GET['date_month']}' AND `daily_trenning`.`date_year` = '{$_GET['date_year']}'");
                    while ($result = mysqli_fetch_array($sql)){
                        if ($i % 4 != 0) {
                            $exercises += $result['exercise_1'];
                            $exercises += $result['exercise_2'];
                            $exercises += $result['exercise_3'];
                            $exercises += $result['exercise_4'];
                            $exercises += $result['exercise_5'];
                            $exercises += $result['exercise_6'];
                        }
                    }
                }
                $res = [
                    "code" => 100,
                    "return" => $exercises
                ];
                $res = array($res);
                $array = array(
                    'report_user' => $res,
                );
                echo json_encode($array);
                exit();
            }
            if ($_GET['report_type'] == "time") {
                $time_sec = 0;
                for ($i = 0; $i <= $_GET['date_day']; $i++) {
                    $sql = mysqli_query($connect, "SELECT `daily_trenning`.*, `trennings`.`exercise_id_1`, `trennings`.`exercise_id_2`, `trennings`.`exercise_id_3`, `trennings`.`exercise_id_4`, `trennings`.`exercise_id_5`, `trennings`.`exercise_id_6`, `trennings`.`exercise_1_param`, `trennings`.`exercise_2_param`, `trennings`.`exercise_3_param`, `trennings`.`exercise_4_param`, `trennings`.`exercise_5_param`, `trennings`.`exercise_6_param`, `trennings`.`exercise_1_type`, `trennings`.`exercise_2_type`, `trennings`.`exercise_3_type`, `trennings`.`exercise_4_type`, `trennings`.`exercise_5_type`, `trennings`.`exercise_6_type` FROM `daily_trenning` LEFT JOIN `trennings` ON `trennings`.`trenning_day` = `daily_trenning`.`date_day` AND `trennings`.`trenning_type` = `daily_trenning`.`trenning_type` WHERE `daily_trenning`.`user_id` = '{$id}' AND `daily_trenning`.`date_day` = '{$i}' AND `daily_trenning`.`date_month` = '{$_GET['date_month']}' AND `daily_trenning`.`date_year` = '{$_GET['date_year']}'");
                    while ($result = mysqli_fetch_array($sql)){
                        if ($i % 4 != 0) {
                            if ($result['exercise_1_type'] == 'tm' && $result['exercise_1'] == 1) {
                                $time_sec += $result['exercise_1_param'];
                            }
                            if ($result['exercise_2_type'] == 'tm' && $result['exercise_2'] == 1) {
                                $time_sec += $result['exercise_2_param'];
                            }
                            if ($result['exercise_3_type'] == 'tm' && $result['exercise_3'] == 1) {
                                $time_sec += $result['exercise_3_param'];
                            }
                            if ($result['exercise_4_type'] == 'tm' && $result['exercise_4'] == 1) {
                                $time_sec += $result['exercise_4_param'];
                            }
                            if ($result['exercise_5_type'] == 'tm' && $result['exercise_5'] == 1) {
                                $time_sec += $result['exercise_5_param'];
                            }
                            if ($result['exercise_6_type'] == 'tm' && $result['exercise_6'] == 1) {
                                $time_sec += $result['exercise_6_param'];
                            }
                        }
                    }
                }
                $res = [
                    "code" => 100,
                    "return" => round($time_sec / 60, 0)
                ];
                $res = array($res);
                $array = array(
                    'report_user' => $res,
                );
                echo json_encode($array);
                exit();
            }
            if ($_GET['report_type'] == "kkal") {
                $kkal = 0;
                for ($i = 0; $i <= $_GET['date_day']; $i++) {
                    $sql = mysqli_query($connect, "SELECT * FROM `monthlyfood` WHERE `date_day` = '{$i}'");
                    if ($result = mysqli_fetch_array($sql)) {
                        $kkal += $result['breakfast_kkal'];
                        $kkal += $result['lunch_kkal'];
                        $kkal += $result['dinner_kkal'];
                    }
                }
                $res = [
                    "code" => 100,
                    "return" => $kkal
                ];
                $res = array($res);
                $array = array(
                    'report_user' => $res,
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