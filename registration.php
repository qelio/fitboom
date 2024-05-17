<?php
    // POST: login, name, pass, family, city, pol, email, dolg, imghref; GET: authorization_key
    header('Content-type: json/application; charset=utf-8');
    $host = "localhost";
    $db_name = "l90858az_fitboom";
    $username = "l90858az_fitboom";
    $password = "VDSfromChel!2004@";
    $connect = mysqli_connect($host, $username, $password, $db_name);
    $connect->set_charset("utf8");
    if (isset($_POST['email']) && isset($_POST['password']) && isset($_POST['first_name']) && isset($_POST['last_name'])){
        $counter_users = 0;
        $sql = mysqli_query($connect, "SELECT * FROM `users` WHERE `email` = '{$_POST['email']}'");
        while ($result = mysqli_fetch_array($sql)){
            $counter_users += 1;
        }
        if ($counter_users > 0) {
            $res = [
                "code" => 103,
                "message" => "A user with this email already exists"
            ];
            $res = array($res);
            $array = array(
                'registration' => $res,
            );
            echo json_encode($array);
            exit();
        }
        else {
            $device_info = "";
            $client  = @$_SERVER['HTTP_CLIENT_IP'];
            $forward = @$_SERVER['HTTP_X_FORWARDED_FOR'];
            $remote  = @$_SERVER['REMOTE_ADDR'];
            if (filter_var($client, FILTER_VALIDATE_IP)){
                $ip = $client;
            } else if(filter_var($forward, FILTER_VALIDATE_IP)){
                $ip = $forward;
            }
            else{
                $ip = $remote;
            }
            $device_info = $_SERVER['HTTP_USER_AGENT'];
            $sql = mysqli_query($connect, "REPLACE INTO `users` (`first_name`, `last_name`, `email`, `password`, `device_info`) VALUES ('{$_POST['first_name']}', '{$_POST['last_name']}', '{$_POST['email']}', '{$_POST['password']}', '{$device_info}')");
            $id = 0;
            $first_name = "";
            $last_name = "";
            $email = "";
            $password = "";
            $gender = "";
            $age = 0;
            $current_weight = 0;
            $desired_weight = 0;
            $growth = 0;
            $monthly_budget = 0;
            $intolerance = "";
            $favorite_foods = "";
            $unloved_foods = "";
            $device_info = "";
            $sql = mysqli_query($connect, "SELECT * FROM `users` WHERE `email` = '{$_POST['email']}' AND `password` = '{$_POST['password']}'");
            if ($result = mysqli_fetch_array($sql)){
                $id = $result['id'];
                $first_name = $result['first_name'];
                $last_name = $result['last_name'];
                $email = $result['email'];
                $password = $result['password'];
                $device_info = $result['device_info'];
                $gender = $result['gender'];
                $age = $result['age'];
                $current_weight = $result['current_weight'];
                $desired_weight = $result['desired_weight'];
                $growth = $result['growth'];
                $monthly_budget = $result['monthly_budget'];
                $intolerance = $result['intolerance'];
                $favorite_foods = $result['favorite_foods'];
                $unloved_foods = $result['unloved_foods'];
            }
            $device_info_current = "";
            $client  = @$_SERVER['HTTP_CLIENT_IP'];
            $forward = @$_SERVER['HTTP_X_FORWARDED_FOR'];
            $remote  = @$_SERVER['REMOTE_ADDR'];
            if (filter_var($client, FILTER_VALIDATE_IP)){
                $ip = $client;
            } else if(filter_var($forward, FILTER_VALIDATE_IP)){
                $ip = $forward;
            }
            else{
                $ip = $remote;
            }
            $device_info_current = $_SERVER['HTTP_USER_AGENT'];
            $date = date ("d.m.Y H:i");
            $sql = mysqli_query($connect, "REPLACE INTO `authorization_list` (`device_info`, `device_ip`, `auth_date`, `users_id`) VALUES ('{$device_info_current}', '{$ip}', '{$date}', '{$id}')");
            $res = [
                "code" => 100,
                "id" => $id,
                "first_name" => $first_name,
                "last_name" => $last_name,
                "email" => $email,
                "password" => $password,
                "device_info" => $device_info,
                "gender" => $gender,
                "age" => $age,
                "current_weight" => $current_weight,
                "desired_weight" => $desired_weight,
                "growth" => $growth,
                "monthly_budget" => $monthly_budget,
                "intolerance" => $intolerance,
                "favorite_foods" => $favorite_foods,
                "unloved_foods" => $unloved_foods
            ];
            $res = array($res);
            $array = array(
                'registration' => $res,
            );
            echo json_encode($array, JSON_UNESCAPED_UNICODE);
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
            'registration' => $res,
        );
        echo json_encode($array);
        exit();
    }
    
?>