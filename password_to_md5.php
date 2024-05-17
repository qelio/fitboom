<?php
    if (isset($_POST['password'])){
        $res = [
            "code" => 100,
            "password" => md5($_POST['password']."slava2012")
        ];
        $res = array($res);
        $array = array(
            'password_to_md5' => $res,
        );
        echo json_encode($array);
        exit();
    }
    else {
        $res = [
            "code" => 102,
            "message" => "One of the required fields was not passed"
        ];
        $res = array($res);
        $array = array(
            'password_to_md5' => $res,
        );
        echo json_encode($array);
        exit();
    }
?>