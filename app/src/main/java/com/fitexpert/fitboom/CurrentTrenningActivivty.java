package com.fitexpert.fitboom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

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

public class CurrentTrenningActivivty extends AppCompatActivity {

    SharedPreferences settings_profile;
    LinearLayout exercise_1_linear, exercise_2_linear, exercise_3_linear, exercise_4_linear, exercise_5_linear, exercise_6_linear;
    LinearLayout exercise_1_status, exercise_2_status, exercise_3_status, exercise_4_status, exercise_5_status, exercise_6_status;
    TextView exercise_1_title, exercise_2_title, exercise_3_title, exercise_4_title, exercise_5_title, exercise_6_title;
    TextView exercise_1_param, exercise_2_param, exercise_3_param, exercise_4_param, exercise_5_param, exercise_6_param;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_trenning);
        Window window = this.getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.primary_color));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.primary_color));
        DailyTrenning dailyTrenning = (DailyTrenning) getIntent().getSerializableExtra("daily_trenning");
        actionBar.setTitle("Ежедневная тренировка");
        settings_profile = getSharedPreferences("settings_profile", MODE_PRIVATE);

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

        new GetExercise(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), dailyTrenning.getExercise_1_type(), dailyTrenning.getExercise_id_1(), dailyTrenning.getExercise_1_param(), dailyTrenning.getExercise_1(), exercise_1_title, exercise_1_param, exercise_1_linear, this).execute("https://testmatica.ru/fitboom_api/current_exercise.php");
        new GetExercise(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), dailyTrenning.getExercise_2_type(), dailyTrenning.getExercise_id_2(), dailyTrenning.getExercise_2_param(), dailyTrenning.getExercise_2(), exercise_2_title, exercise_2_param, exercise_2_linear, this).execute("https://testmatica.ru/fitboom_api/current_exercise.php");
        new GetExercise(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), dailyTrenning.getExercise_3_type(), dailyTrenning.getExercise_id_3(), dailyTrenning.getExercise_3_param(), dailyTrenning.getExercise_3(), exercise_3_title, exercise_3_param, exercise_3_linear, this).execute("https://testmatica.ru/fitboom_api/current_exercise.php");
        new GetExercise(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), dailyTrenning.getExercise_4_type(), dailyTrenning.getExercise_id_4(), dailyTrenning.getExercise_4_param(), dailyTrenning.getExercise_4(), exercise_4_title, exercise_4_param, exercise_4_linear, this).execute("https://testmatica.ru/fitboom_api/current_exercise.php");
        new GetExercise(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), dailyTrenning.getExercise_5_type(), dailyTrenning.getExercise_id_5(), dailyTrenning.getExercise_5_param(), dailyTrenning.getExercise_5(), exercise_5_title, exercise_5_param, exercise_5_linear, this).execute("https://testmatica.ru/fitboom_api/current_exercise.php");
        new GetExercise(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), dailyTrenning.getExercise_6_type(), dailyTrenning.getExercise_id_6(), dailyTrenning.getExercise_6_param(), dailyTrenning.getExercise_6(), exercise_6_title, exercise_6_param, exercise_6_linear, this).execute("https://testmatica.ru/fitboom_api/current_exercise.php");
        // new GetExercise(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), dailyTrenning.getExercise__type(), dailyTrenning.getExercise_id_1(), dailyTrenning.getExercise_1_param(), dailyTrenning.getExercise_1(), exercise_1_title, exercise_1_param, exercise_1_linear, this).execute("https://testmatica.ru/fitboom_api/current_exercise.php");
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class GetExercise extends AsyncTask<String, String, String> {
        String email, password, type;
        int exercise_id, param, exercise;
        TextView exercise_title, exercise_param;
        LinearLayout exercise_linear;
        Context context;

        public GetExercise(String email, String password, String type, int exercise_id, int param, int exercise, TextView exercise_title, TextView exercise_param, LinearLayout exercise_linear, Context context) {
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

}