package com.fitexpert.fitboom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

public class CurrentTrenningActivivty extends AppCompatActivity {

    SharedPreferences settings_profile;
    LinearLayout exercise_1_linear, exercise_2_linear, exercise_3_linear, exercise_4_linear, exercise_5_linear, exercise_6_linear;
    LinearLayout exercise_1_status, exercise_2_status, exercise_3_status, exercise_4_status, exercise_5_status, exercise_6_status;
    TextView exercise_1_title, exercise_2_title, exercise_3_title, exercise_4_title, exercise_5_title, exercise_6_title;
    TextView exercise_1_param, exercise_2_param, exercise_3_param, exercise_4_param, exercise_5_param, exercise_6_param;
    LinearLayout exercises_layout, loading_layout, weekend_layout, start_trenning_layout;
    DailyTrenning dailyTrenning;
    DailyExercise daily_exercise;
    // Элементы навигации для меню упражнений
    LinearLayout status_bar[] = new LinearLayout[7];
    TextView current_exercise_title;
    LinearLayout current_exercise_count, current_exercise_time;
    TextView current_exercise_count_text, current_exercise_time_text;
    Button exercise_next_exercise;

    Button start_trenning;
    boolean flags[] = new boolean[7];
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_trenning);
        Window window = this.getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.primary_color));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.primary_color));
        dailyTrenning = (DailyTrenning) getIntent().getSerializableExtra("daily_trenning");
        actionBar.setTitle("Ежедневная тренировка");
        settings_profile = getSharedPreferences("settings_profile", MODE_PRIVATE);
        start_trenning = (Button) findViewById(R.id.start_trenning);
        Date current_date = new Date();
        Log.d("CURRENT_DATE", Integer.toString(current_date.getDate()));
        if (dailyTrenning.getDate_day() != current_date.getDate() || dailyTrenning.getCurrentExercise() == -1) {
            start_trenning.setVisibility(View.GONE);
        }

        exercises_layout = (LinearLayout) findViewById(R.id.exercises_layout);
        loading_layout = (LinearLayout) findViewById(R.id.loading_layout);
        weekend_layout = (LinearLayout) findViewById(R.id.weekend_layout);
        start_trenning_layout = (LinearLayout) findViewById(R.id.start_trenning_layout);
        exercises_layout.setVisibility(View.GONE);
        loading_layout.setVisibility(View.VISIBLE);
        weekend_layout.setVisibility(View.GONE);
        start_trenning_layout.setVisibility(View.GONE);

        // Инициализация элементов навигации для меню упражнений
        status_bar[1] = (LinearLayout) findViewById(R.id.status_bar_1);
        status_bar[2] = (LinearLayout) findViewById(R.id.status_bar_2);
        status_bar[3] = (LinearLayout) findViewById(R.id.status_bar_3);
        status_bar[4] = (LinearLayout) findViewById(R.id.status_bar_4);
        status_bar[5] = (LinearLayout) findViewById(R.id.status_bar_5);
        status_bar[6] = (LinearLayout) findViewById(R.id.status_bar_6);
        current_exercise_title = (TextView) findViewById(R.id.current_exercise_title);
        current_exercise_count = (LinearLayout) findViewById(R.id.current_exercise_count);
        current_exercise_time = (LinearLayout) findViewById(R.id.current_exercise_time);
        current_exercise_count_text = (TextView) findViewById(R.id.current_exercise_count_text);
        current_exercise_time_text = (TextView) findViewById(R.id.current_exercise_time_text);
        exercise_next_exercise = (Button) findViewById(R.id.exercise_next_exercise);

        exercise_1_linear = (LinearLayout) findViewById(R.id.exercise_1_linear);
        exercise_2_linear = (LinearLayout) findViewById(R.id.exercise_2_linear);
        exercise_3_linear = (LinearLayout) findViewById(R.id.exercise_3_linear);
        exercise_4_linear = (LinearLayout) findViewById(R.id.exercise_4_linear);
        exercise_5_linear = (LinearLayout) findViewById(R.id.exercise_5_linear);
        exercise_6_linear = (LinearLayout) findViewById(R.id.exercise_6_linear);

        exercise_1_status = (LinearLayout) findViewById(R.id.exercise_1_status);
        exercise_2_status = (LinearLayout) findViewById(R.id.exercise_2_status);
        exercise_3_status = (LinearLayout) findViewById(R.id.exercise_3_status);
        exercise_4_status = (LinearLayout) findViewById(R.id.exercise_4_status);
        exercise_5_status = (LinearLayout) findViewById(R.id.exercise_5_status);
        exercise_6_status = (LinearLayout) findViewById(R.id.exercise_6_status);

        if (dailyTrenning.getExercise_1() == 1) {
            exercise_1_status.setBackground(getDrawable(R.drawable.type_trenning_true));
        }
        if (dailyTrenning.getExercise_2() == 1) {
            exercise_2_status.setBackground(getDrawable(R.drawable.type_trenning_true));
        }
        if (dailyTrenning.getExercise_3() == 1) {
            exercise_3_status.setBackground(getDrawable(R.drawable.type_trenning_true));
        }
        if (dailyTrenning.getExercise_4() == 1) {
            exercise_4_status.setBackground(getDrawable(R.drawable.type_trenning_true));
        }
        if (dailyTrenning.getExercise_5() == 1) {
            exercise_5_status.setBackground(getDrawable(R.drawable.type_trenning_true));
        }
        if (dailyTrenning.getExercise_6() == 1) {
            exercise_6_status.setBackground(getDrawable(R.drawable.type_trenning_true));
        }

        exercise_1_title = (TextView) findViewById(R.id.exercise_1_title);
        exercise_2_title = (TextView) findViewById(R.id.exercise_2_title);
        exercise_3_title = (TextView) findViewById(R.id.exercise_3_title);
        exercise_4_title = (TextView) findViewById(R.id.exercise_4_title);
        exercise_5_title = (TextView) findViewById(R.id.exercise_5_title);
        exercise_6_title = (TextView) findViewById(R.id.exercise_6_title);

        exercise_1_param = (TextView) findViewById(R.id.exercise_1_param);
        exercise_2_param = (TextView) findViewById(R.id.exercise_2_param);
        exercise_3_param = (TextView) findViewById(R.id.exercise_3_param);
        exercise_4_param = (TextView) findViewById(R.id.exercise_4_param);
        exercise_5_param = (TextView) findViewById(R.id.exercise_5_param);
        exercise_6_param = (TextView) findViewById(R.id.exercise_6_param);

        if (dailyTrenning.getExercise_id_1() != 0) {
            for (int i = 0; i < 6; i++) {
                flags[i] = false;
            }
            new GetExercise(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), dailyTrenning.getExercise_1_type(), dailyTrenning.getExercise_id_1(), dailyTrenning.getExercise_1_param(), dailyTrenning.getExercise_1(), exercise_1_title, exercise_1_param, exercise_1_linear, this, 0).execute("https://testmatica.ru/fitboom_api/current_exercise.php");
            new GetExercise(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), dailyTrenning.getExercise_2_type(), dailyTrenning.getExercise_id_2(), dailyTrenning.getExercise_2_param(), dailyTrenning.getExercise_2(), exercise_2_title, exercise_2_param, exercise_2_linear, this, 1).execute("https://testmatica.ru/fitboom_api/current_exercise.php");
            new GetExercise(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), dailyTrenning.getExercise_3_type(), dailyTrenning.getExercise_id_3(), dailyTrenning.getExercise_3_param(), dailyTrenning.getExercise_3(), exercise_3_title, exercise_3_param, exercise_3_linear, this, 2).execute("https://testmatica.ru/fitboom_api/current_exercise.php");
            new GetExercise(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), dailyTrenning.getExercise_4_type(), dailyTrenning.getExercise_id_4(), dailyTrenning.getExercise_4_param(), dailyTrenning.getExercise_4(), exercise_4_title, exercise_4_param, exercise_4_linear, this, 3).execute("https://testmatica.ru/fitboom_api/current_exercise.php");
            new GetExercise(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), dailyTrenning.getExercise_5_type(), dailyTrenning.getExercise_id_5(), dailyTrenning.getExercise_5_param(), dailyTrenning.getExercise_5(), exercise_5_title, exercise_5_param, exercise_5_linear, this, 4).execute("https://testmatica.ru/fitboom_api/current_exercise.php");
            new GetExercise(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), dailyTrenning.getExercise_6_type(), dailyTrenning.getExercise_id_6(), dailyTrenning.getExercise_6_param(), dailyTrenning.getExercise_6(), exercise_6_title, exercise_6_param, exercise_6_linear, this, 5).execute("https://testmatica.ru/fitboom_api/current_exercise.php");
        }
        else {
            new SetSuccessWeekendDay(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), dailyTrenning.getDate_day(), dailyTrenning.getDate_month(), dailyTrenning.getDate_year(), dailyTrenning.getTrenning_type(), 1).execute("https://testmatica.ru/fitboom_api/update_daily_trenning.php");
            new SetSuccessWeekendDay(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), dailyTrenning.getDate_day(), dailyTrenning.getDate_month(), dailyTrenning.getDate_year(), dailyTrenning.getTrenning_type(), 2).execute("https://testmatica.ru/fitboom_api/update_daily_trenning.php");
            new SetSuccessWeekendDay(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), dailyTrenning.getDate_day(), dailyTrenning.getDate_month(), dailyTrenning.getDate_year(), dailyTrenning.getTrenning_type(), 3).execute("https://testmatica.ru/fitboom_api/update_daily_trenning.php");
            new SetSuccessWeekendDay(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), dailyTrenning.getDate_day(), dailyTrenning.getDate_month(), dailyTrenning.getDate_year(), dailyTrenning.getTrenning_type(), 4).execute("https://testmatica.ru/fitboom_api/update_daily_trenning.php");
            new SetSuccessWeekendDay(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), dailyTrenning.getDate_day(), dailyTrenning.getDate_month(), dailyTrenning.getDate_year(), dailyTrenning.getTrenning_type(), 5).execute("https://testmatica.ru/fitboom_api/update_daily_trenning.php");
            new SetSuccessWeekendDay(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), dailyTrenning.getDate_day(), dailyTrenning.getDate_month(), dailyTrenning.getDate_year(), dailyTrenning.getTrenning_type(), 6).execute("https://testmatica.ru/fitboom_api/update_daily_trenning.php");
            exercises_layout.setVisibility(View.GONE);
            loading_layout.setVisibility(View.GONE);
            weekend_layout.setVisibility(View.VISIBLE);
        }

        current_exercise_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog.Builder alert = new AlertDialog.Builder(CurrentTrenningActivivty.this);
                        alert.setTitle("Инструкция по выполнению");
                        alert.setMessage(daily_exercise.getDescription());
                        alert.setIcon(getDrawable(R.drawable.question_exercise));
                        alert.setPositiveButton("Понятно", null);
                        alert.show();
                    }
                }, 300);
            }
        });

        start_trenning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PUSH", "11");
                new UpdateDayInfo(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), dailyTrenning.getDate_day(), dailyTrenning.getDate_month(), dailyTrenning.getDate_year(), dailyTrenning.getTrenning_type()).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });
        exercise_next_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("GETTYPE", daily_exercise.getType());
                if (!daily_exercise.getType().equals("tm")) {
                    new SetSuccessExercise(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), dailyTrenning.getDate_day(), dailyTrenning.getDate_month(), dailyTrenning.getDate_year(), dailyTrenning.getTrenning_type(), dailyTrenning.getCurrentExercise()).execute("https://testmatica.ru/fitboom_api/update_daily_trenning.php");
                }
                else {
                    exercise_next_exercise.setVisibility(View.GONE);
                    new CountDownTimer(daily_exercise.getParam() * 1000, 1000) {
                        public void onTick(long millisUntilFinished) {
                            // Used for formatting digit to be in 2 digits only
                            NumberFormat f = new DecimalFormat("00");
                            long hour = (millisUntilFinished / 3600000) % 24;
                            long min = (millisUntilFinished / 60000) % 60;
                            long sec = (millisUntilFinished / 1000) % 60;
                            current_exercise_time_text.setText(f.format(min) + ":" + f.format(sec));
                        }
                        // When the task is over it will print 00:00:00 there
                        public void onFinish() {
                            exercise_next_exercise.setVisibility(View.VISIBLE);
                            daily_exercise.setType("cnt");
                            current_exercise_time_text.setText("00:00");
                            exercise_next_exercise.setText("Готово!");
                            // new UpdateDayInfo(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), dailyTrenning.getDate_day(), dailyTrenning.getDate_month(), dailyTrenning.getDate_year(), dailyTrenning.getTrenning_type()).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                        }
                    }.start();
                }
            }
        });

        // new GetExercise(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), dailyTrenning.getExercise__type(), dailyTrenning.getExercise_id_1(), dailyTrenning.getExercise_1_param(), dailyTrenning.getExercise_1(), exercise_1_title, exercise_1_param, exercise_1_linear, this).execute("https://testmatica.ru/fitboom_api/current_exercise.php");
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(RESULT_OK);
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class GetExercise extends AsyncTask<String, String, String> {
        String email, password, type;
        int exercise_id, param, exercise, exer_num;
        TextView exercise_title, exercise_param;
        LinearLayout exercise_linear;
        Context context;

        public GetExercise(String email, String password, String type, int exercise_id, int param, int exercise, TextView exercise_title, TextView exercise_param, LinearLayout exercise_linear, Context context, int exer_num) {
            this.email = email;
            this.password = password;
            this.type = type;
            this.exercise_id = exercise_id;
            this.param = param;
            this.exercise = exercise;
            this.exercise_title = exercise_title;
            this.exercise_param = exercise_param;
            this.exercise_linear = exercise_linear;
            this.context = context;
            this.exer_num = exer_num;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... url1) {
            String current = "";
            String JSON_URL = url1[0] + "?exercise_id=" + exercise_id;
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);
                    urlConnection.connect();
                    String request = "email=" + email + "&password=" + password;
                    urlConnection.getOutputStream().write((request).getBytes());
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(in);
                    int data = isr.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isr.read();
                    }
                    return current;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("current_exercise");
                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                if (jsonObject1.getInt("code") == 100) {
                    flags[exer_num] = true;
                    DailyExercise daily_exercise = new DailyExercise(jsonObject1.getString("title"), jsonObject1.getString("description"), type, param);
                    exercise_title.setText(daily_exercise.getTitle());
                    if (daily_exercise.getType().equals("cnt")) {
                        exercise_param.setText(daily_exercise.getParam() + " раз");
                    }
                    else {
                        if (daily_exercise.getParam() >= 60) {
                            if (daily_exercise.getParam() % 60 < 10) {
                                exercise_param.setText("01:" + Integer.toString(daily_exercise.getParam() % 60) + "0");
                            }
                            else {
                                exercise_param.setText("01:" + Integer.toString(daily_exercise.getParam() % 60));
                            }
                        }
                        else {
                            exercise_param.setText("00:" + Integer.toString(daily_exercise.getParam()));
                        }
                    }
                    boolean check_flag = true;
                    for (int i = 0; i < 6; i++) {
                        if (flags[i] == false) {
                            check_flag = false;
                        }
                    }
                    if (check_flag) {
                        exercises_layout.setVisibility(View.VISIBLE);
                        loading_layout.setVisibility(View.GONE);
                        weekend_layout.setVisibility(View.GONE);
                    }
                }
                if (jsonObject1.getInt("code") == 101) {
                    Intent auth_activity = new Intent(CurrentTrenningActivivty.this, SplashActivity.class);
                    startActivity(auth_activity);
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class UpdateDayInfo extends AsyncTask<String, String, String> {
        String email, password;
        int day, month, year, trenning_type;
        public UpdateDayInfo(String email, String password, int day, int month, int year, int trenning_type) {
            this.email = email;
            this.password = password;
            this.day = day;
            this.month = month;
            this.year = year;
            this.trenning_type = trenning_type;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            exercises_layout.setVisibility(View.GONE);
            loading_layout.setVisibility(View.VISIBLE);
            weekend_layout.setVisibility(View.GONE);
            start_trenning_layout.setVisibility(View.GONE);
        }

        @Override
        protected String doInBackground(String... url1) {
            String current = "";
            String JSON_URL = url1[0] + "?day=" + day + "&month=" + month + "&year=" + year + "&trenning_type=" + trenning_type;
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);
                    urlConnection.connect();
                    String request = "email=" + email + "&password=" + password;
                    urlConnection.getOutputStream().write((request).getBytes());
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(in);
                    int data = isr.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isr.read();
                    }
                    return current;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("daily_trenning");
                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                Date current_date = new Date();
                if (jsonObject1.getInt("code") == 100) {
                    dailyTrenning.setId(jsonObject1.getInt("id"));
                    dailyTrenning.setUser_id(jsonObject1.getInt("user_id"));
                    dailyTrenning.setTrenning_type(jsonObject1.getInt("trenning_type"));
                    dailyTrenning.setDate_day(jsonObject1.getInt("date_day"));
                    dailyTrenning.setDate_month(jsonObject1.getInt("date_month"));
                    dailyTrenning.setDate_year(jsonObject1.getInt("date_year"));
                    dailyTrenning.setExercise_1(jsonObject1.getInt("exercise_1"));
                    dailyTrenning.setExercise_2(jsonObject1.getInt("exercise_2"));
                    dailyTrenning.setExercise_3(jsonObject1.getInt("exercise_3"));
                    dailyTrenning.setExercise_4(jsonObject1.getInt("exercise_4"));
                    dailyTrenning.setExercise_5(jsonObject1.getInt("exercise_5"));
                    dailyTrenning.setExercise_6(jsonObject1.getInt("exercise_6"));
                    dailyTrenning.setExercise_id_1(jsonObject1.getInt("exercise_id_1"));
                    dailyTrenning.setExercise_id_2(jsonObject1.getInt("exercise_id_2"));
                    dailyTrenning.setExercise_id_3(jsonObject1.getInt("exercise_id_3"));
                    dailyTrenning.setExercise_id_4(jsonObject1.getInt("exercise_id_4"));
                    dailyTrenning.setExercise_id_5(jsonObject1.getInt("exercise_id_5"));
                    dailyTrenning.setExercise_id_6(jsonObject1.getInt("exercise_id_6"));
                    dailyTrenning.setExercise_1_param(jsonObject1.getInt("exercise_1_param"));
                    dailyTrenning.setExercise_2_param(jsonObject1.getInt("exercise_2_param"));
                    dailyTrenning.setExercise_3_param(jsonObject1.getInt("exercise_3_param"));
                    dailyTrenning.setExercise_4_param(jsonObject1.getInt("exercise_4_param"));
                    dailyTrenning.setExercise_5_param(jsonObject1.getInt("exercise_5_param"));
                    dailyTrenning.setExercise_6_param(jsonObject1.getInt("exercise_6_param"));
                    dailyTrenning.setExercise_1_type(jsonObject1.getString("exercise_1_type"));
                    dailyTrenning.setExercise_2_type(jsonObject1.getString("exercise_2_type"));
                    dailyTrenning.setExercise_3_type(jsonObject1.getString("exercise_3_type"));
                    dailyTrenning.setExercise_4_type(jsonObject1.getString("exercise_4_type"));
                    dailyTrenning.setExercise_5_type(jsonObject1.getString("exercise_5_type"));
                    dailyTrenning.setExercise_6_type(jsonObject1.getString("exercise_6_type"));
                    dailyTrenning.setTrenning_date(jsonObject1.getString("trenning_date"));
                    if (dailyTrenning.getExercise_1() == 1){
                        status_bar[1].setBackground(getDrawable(R.drawable.status_bar_true));
                    }
                    if (dailyTrenning.getExercise_2() == 1){
                        status_bar[2].setBackground(getDrawable(R.drawable.status_bar_true));
                    }
                    if (dailyTrenning.getExercise_3() == 1){
                        status_bar[3].setBackground(getDrawable(R.drawable.status_bar_true));
                    }
                    if (dailyTrenning.getExercise_4() == 1){
                        status_bar[4].setBackground(getDrawable(R.drawable.status_bar_true));
                    }
                    if (dailyTrenning.getExercise_5() == 1){
                        status_bar[5].setBackground(getDrawable(R.drawable.status_bar_true));
                    }
                    if (dailyTrenning.getExercise_6() == 1){
                        status_bar[6].setBackground(getDrawable(R.drawable.status_bar_true));
                    }

                    new UpdateCurrentExercise(email, password, dailyTrenning.getTypeOfId(dailyTrenning.getCurrentExercise()), dailyTrenning.getExerciseIdOfId(dailyTrenning.getCurrentExercise()), dailyTrenning.getParamOfId(dailyTrenning.getCurrentExercise())).execute("https://testmatica.ru/fitboom_api/current_exercise.php");
                }
                if (jsonObject1.getInt("code") == 101) {
                    Intent main_activity = new Intent(CurrentTrenningActivivty.this, SplashActivity.class);
                    startActivity(main_activity);
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class UpdateCurrentExercise extends AsyncTask<String, String, String> {
        String email, password, type;
        int exercise_id, param;

        public UpdateCurrentExercise(String email, String password, String type, int exercise_id, int param) {
            this.email = email;
            this.password = password;
            this.type = type;
            this.exercise_id = exercise_id;
            this.param = param;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            exercises_layout.setVisibility(View.GONE);
            loading_layout.setVisibility(View.VISIBLE);
            weekend_layout.setVisibility(View.GONE);
            start_trenning_layout.setVisibility(View.GONE);
        }

        @Override
        protected String doInBackground(String... url1) {
            String current = "";
            String JSON_URL = url1[0] + "?exercise_id=" + exercise_id;
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);
                    urlConnection.connect();
                    String request = "email=" + email + "&password=" + password;
                    urlConnection.getOutputStream().write((request).getBytes());
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(in);
                    int data = isr.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isr.read();
                    }
                    return current;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }
        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("current_exercise");
                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                if (jsonObject1.getInt("code") == 100) {
                    daily_exercise = new DailyExercise(jsonObject1.getString("title"), jsonObject1.getString("description"), type, param);
                    current_exercise_title.setText(Html.fromHtml(daily_exercise.getTitle() + "\uD83D\uDCA1"));
                    if (daily_exercise.getType().equals("cnt")) {
                        current_exercise_count.setVisibility(View.VISIBLE);
                        current_exercise_time.setVisibility(View.GONE);
                        current_exercise_count_text.setText(daily_exercise.getParam() + " раз");
                        exercise_next_exercise.setText("Готово!");
                    }
                    else {
                        current_exercise_count.setVisibility(View.GONE);
                        current_exercise_time.setVisibility(View.VISIBLE);
                        exercise_next_exercise.setText("Начать!");
                        if (daily_exercise.getParam() >= 60) {
                            if (daily_exercise.getParam() % 60 < 10) {
                                current_exercise_time_text.setText("01:" + Integer.toString(daily_exercise.getParam() % 60) + "0");
                            }
                            else {
                                current_exercise_time_text.setText("01:" + Integer.toString(daily_exercise.getParam() % 60));
                            }
                        }
                        else {
                            current_exercise_time_text.setText("00:" + Integer.toString(daily_exercise.getParam()));
                        }

                    }
                    exercises_layout.setVisibility(View.GONE);
                    loading_layout.setVisibility(View.GONE);
                    weekend_layout.setVisibility(View.GONE);
                    start_trenning_layout.setVisibility(View.VISIBLE);
                }
                if (jsonObject1.getInt("code") == 101) {
                    Intent auth_activity = new Intent(CurrentTrenningActivivty.this, SplashActivity.class);
                    startActivity(auth_activity);
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class SetSuccessExercise extends AsyncTask<String, String, String> {
        String email, password;
        int day, month, year, trenning_type, edit_exercise;
        public SetSuccessExercise(String email, String password, int day, int month, int year, int trenning_type, int edit_exercise) {
            this.email = email;
            this.password = password;
            this.day = day;
            this.month = month;
            this.year = year;
            this.trenning_type = trenning_type;
            this.edit_exercise = edit_exercise;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            exercises_layout.setVisibility(View.GONE);
            loading_layout.setVisibility(View.VISIBLE);
            weekend_layout.setVisibility(View.GONE);
            start_trenning_layout.setVisibility(View.GONE);
        }

        @Override
        protected String doInBackground(String... url1) {
            String current = "";
            String JSON_URL = url1[0] + "?day=" + day + "&month=" + month + "&year=" + year + "&trenning_type=" + trenning_type  + "&edit_exercise=" + edit_exercise;
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);
                    urlConnection.connect();
                    String request = "email=" + email + "&password=" + password;
                    urlConnection.getOutputStream().write((request).getBytes());
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(in);
                    int data = isr.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isr.read();
                    }
                    return current;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("update_daily_trenning");
                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                if (jsonObject1.getInt("code") == 100) {
                    if (dailyTrenning.getCurrentExercise() == 6) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Поздравляем! Вы выполнили все задания!", Toast.LENGTH_SHORT);
                        toast.show();
                        setResult(RESULT_OK);
                        finish();
                    }
                    else {
                        new UpdateDayInfo(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), dailyTrenning.getDate_day(), dailyTrenning.getDate_month(), dailyTrenning.getDate_year(), dailyTrenning.getTrenning_type()).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                    }
                    Log.d("SUCCESS_UPDATE_CURRENT", Integer.toString(edit_exercise));
                }
                else if (jsonObject1.getInt("code") == 101) {
                    Intent main_activity = new Intent(CurrentTrenningActivivty.this, SplashActivity.class);
                    startActivity(main_activity);
                    finish();
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Ошибка при обработке запроса!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class SetSuccessWeekendDay extends AsyncTask<String, String, String> {
        String email, password;
        int day, month, year, trenning_type, edit_exercise;
        public SetSuccessWeekendDay(String email, String password, int day, int month, int year, int trenning_type, int edit_exercise) {
            this.email = email;
            this.password = password;
            this.day = day;
            this.month = month;
            this.year = year;
            this.trenning_type = trenning_type;
            this.edit_exercise = edit_exercise;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... url1) {
            String current = "";
            String JSON_URL = url1[0] + "?day=" + day + "&month=" + month + "&year=" + year + "&trenning_type=" + trenning_type  + "&edit_exercise=" + edit_exercise;
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);
                    urlConnection.connect();
                    String request = "email=" + email + "&password=" + password;
                    urlConnection.getOutputStream().write((request).getBytes());
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(in);
                    int data = isr.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isr.read();
                    }
                    return current;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("update_daily_trenning");
                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                if (jsonObject1.getInt("code") == 100) {

                }
                else if (jsonObject1.getInt("code") == 101) {
                    Intent main_activity = new Intent(CurrentTrenningActivivty.this, SplashActivity.class);
                    startActivity(main_activity);
                    finish();
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Ошибка при обработке запроса!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}