<?php
    header('Content-type: json/application; charset=utf-8');
    $host = "localhost";
    $db_name = "l90858az_fitboom";
    $username = "l90858az_fitboom";
    $password = "VDSfromChel!2004@";
    $connect = mysqli_connect($host, $username, $password, $db_name);
    $connect->set_charset("utf8");
    if (isset($_POST['email']) && isset($_POST['password']) && isset($_GET['day']) && isset($_GET['month']) && isset($_GET['year']) && isset($_GET['trenning_type']) && isset($_GET['edit_exercise'])){
        $id = 0;
        $sql = mysqli_query($connect, "SELECT * FROM `users` WHERE `email` = '{$_POST['email']}' AND `password` = '{$_POST['password']}'");
        if ($result = mysqli_fetch_array($sql)){
            $id = $result['id'];
        }
        if ($id != 0) {
            if ($_GET['edit_exercise'] == 1) {
                $sql = mysqli_query($connect, "UPDATE `daily_trenning` SET `exercise_1` = '1' WHERE `daily_trenning`.`user_id` = '{$id}' AND `daily_trenning`.`date_day` = '{$_GET['day']}' AND `daily_trenning`.`date_month` = '{$_GET['month']}' AND `daily_trenning`.`date_year` = '{$_GET['year']}' AND `daily_trenning`.`trenning_type` = '{$_GET['trenning_type']}'");
                if ($sql) {
                    $res = [
                        "code" => 100,
                        "message" => "Update successfully!"
                    ];
                    $res = array($res);
                    $array = array(
                        'update_daily_trenning' => $res,
                    );
                    echo json_encode($array);
                    exit();
                }
                else {
                    $res = [
                        "code" => 104,
                        "message" => "The selected entry does not exist!"
                    ];
                    $res = array($res);
                    $array = array(
                        'update_daily_trenning' => $res,
                    );
                    echo json_encode($array);
                    exit();
                }
            }
            else if ($_GET['edit_exercise'] == 2) {
                $sql = mysqli_query($connect, "UPDATE `daily_trenning` SET `exercise_2` = '1' WHERE `daily_trenning`.`user_id` = '{$id}' AND `daily_trenning`.`date_day` = '{$_GET['day']}' AND `daily_trenning`.`date_month` = '{$_GET['month']}' AND `daily_trenning`.`date_year` = '{$_GET['year']}' AND `daily_trenning`.`trenning_type` = '{$_GET['trenning_type']}'");
                if ($sql) {
                    $res = [
                        "code" => 100,
                        "message" => "Update successfully!"
                    ];
                    $res = array($res);
                    $array = array(
                        'update_daily_trenning' => $res,
                    );
                    echo json_encode($array);
                    exit();
                }
                else {
                    $res = [
                        "code" => 104,
                        "message" => "The selected entry does not exist!"
                    ];
                    $res = array($res);
                    $array = array(
                        'update_daily_trenning' => $res,
                    );
                    echo json_encode($array);
                    exit();
                }
            }
            else if ($_GET['edit_exercise'] == 3) {
                $sql = mysqli_query($connect, "UPDATE `daily_trenning` SET `exercise_3` = '1' WHERE `daily_trenning`.`user_id` = '{$id}' AND `daily_trenning`.`date_day` = '{$_GET['day']}' AND `daily_trenning`.`date_month` = '{$_GET['month']}' AND `daily_trenning`.`date_year` = '{$_GET['year']}' AND `daily_trenning`.`trenning_type` = '{$_GET['trenning_type']}'");
                if ($sql) {
                    $res = [
                        "code" => 100,
                        "message" => "Update successfully!"
                    ];
                    $res = array($res);
                    $array = array(
                        'update_daily_trenning' => $res,
                    );
                    echo json_encode($array);
                    exit();
                }
                else {
                    $res = [
                        "code" => 104,
                        "message" => "The selected entry does not exist!"
                    ];
                    $res = array($res);
                    $array = array(
                        'update_daily_trenning' => $res,
                    );
                    echo json_encode($array);
                    exit();
                }
            }
            else if ($_GET['edit_exercise'] == 4) {
                $sql = mysqli_query($connect, "UPDATE `daily_trenning` SET `exercise_4` = '1' WHERE `daily_trenning`.`user_id` = '{$id}' AND `daily_trenning`.`date_day` = '{$_GET['day']}' AND `daily_trenning`.`date_month` = '{$_GET['month']}' AND `daily_trenning`.`date_year` = '{$_GET['year']}' AND `daily_trenning`.`trenning_type` = '{$_GET['trenning_type']}'");
                if ($sql) {
                    $res = [
                        "code" => 100,
                        "message" => "Update successfully!"
                    ];
                    $res = array($res);
                    $array = array(
                        'update_daily_trenning' => $res,
                    );
                    echo json_encode($array);
                    exit();
                }
                else {
                    $res = [
                        "code" => 104,
                        "message" => "The selected entry does not exist!"
                    ];
                    $res = array($res);
                    $array = array(
                        'update_daily_trenning' => $res,
                    );
                    echo json_encode($array);
                    exit();
                }
            }
            else if ($_GET['edit_exercise'] == 5) {
                $sql = mysqli_query($connect, "UPDATE `daily_trenning` SET `exercise_5` = '1' WHERE `daily_trenning`.`user_id` = '{$id}' AND `daily_trenning`.`date_day` = '{$_GET['day']}' AND `daily_trenning`.`date_month` = '{$_GET['month']}' AND `daily_trenning`.`date_year` = '{$_GET['year']}' AND `daily_trenning`.`trenning_type` = '{$_GET['trenning_type']}'");
                if ($sql) {
                    $res = [
                        "code" => 100,
                        "message" => "Update successfully!"
                    ];
                    $res = array($res);
                    $array = array(
                        'update_daily_trenning' => $res,
                    );
                    echo json_encode($array);
                    exit();
                }
                else {
                    $res = [
                        "code" => 104,
                        "message" => "The selected entry does not exist!"
                    ];
                    $res = array($res);
                    $array = array(
                        'update_daily_trenning' => $res,
                    );
                    echo json_encode($array);
                    exit();
                }
            }
            else if ($_GET['edit_exercise'] == 6) {
                $sql = mysqli_query($connect, "UPDATE `daily_trenning` SET `exercise_6` = '1' WHERE `daily_trenning`.`user_id` = '{$id}' AND `daily_trenning`.`date_day` = '{$_GET['day']}' AND `daily_trenning`.`date_month` = '{$_GET['month']}' AND `daily_trenning`.`date_year` = '{$_GET['year']}' AND `daily_trenning`.`trenning_type` = '{$_GET['trenning_type']}'");
                if ($sql) {
                    $res = [
                        "code" => 100,
                        "message" => "Update successfully!"
                    ];
                    $res = array($res);
                    $array = array(
                        'update_daily_trenning' => $res,
                    );
                    echo json_encode($array);
                    exit();
                }
                else {
                    $res = [
                        "code" => 104,
                        "message" => "The selected entry does not exist!"
                    ];
                    $res = array($res);
                    $array = array(
                        'update_daily_trenning' => $res,
                    );
                    echo json_encode($array);
                    exit();
                }
            }
            else {
                $res = [
                    "code" => 103,
                    "message" => "The wrong number of exercise (from 1 to 6)"
                ];
                $res = array($res);
                $array = array(
                    'update_daily_trenning' => $res,
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
                'update_daily_trenning' => $res,
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
            'update_daily_trenning' => $res,
        );
        echo json_encode($array);
        exit();
    }
    
?>