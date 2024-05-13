package com.fitexpert.fitboom;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SplashActivity extends AppCompatActivity {
    SharedPreferences settings_profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Window window = this.getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.status_bar));
        LinearLayout loadingView = (LinearLayout) findViewById(R.id.loadingView);
        LinearLayout registryView = (LinearLayout) findViewById(R.id.registryView);
        Button registryButton = (Button) findViewById(R.id.registryButton);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        settings_profile = getSharedPreferences("settings_profile", MODE_PRIVATE);
        loadingView.setVisibility(View.VISIBLE);
        registryView.setVisibility(View.GONE);
        if (settings_profile.contains("email") && settings_profile.contains("password")) {
            // Log.d("ShPref", settings_profile.getString("email", "-") + " " + settings_profile.getString("password", "-"));
            new AuthorizationUser(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), loadingView, registryView).execute("https://testmatica.ru/fitboom_api/authorization.php");
        }
        else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    loadingView.setVisibility(View.GONE);
                    registryView.setVisibility(View.VISIBLE);
                }
            }, 1500);   //5 seconds
        }
        registryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    Intent registryActivity = new Intent(SplashActivity.this, RegistryActivity.class);
                    startActivity(registryActivity);
                    finish();
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Необходимо установить чек-бокс", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
    // Авторизация пользователя
    private class AuthorizationUser extends AsyncTask<String, String, String> {
        String email, password;
        LinearLayout loadingView, registryView;
        public AuthorizationUser(String email, String password, LinearLayout loadingView, LinearLayout registryView){
            this.email = email;
            this.password = password;
            this.loadingView = loadingView;
            this.registryView = registryView;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadingView.setVisibility(View.VISIBLE);
            registryView.setVisibility(View.GONE);
        }
        @Override
        protected String doInBackground(String... url1){
            String current = "";
            String JSON_URL = url1[0];
            try{
                URL url;
                HttpURLConnection urlConnection = null;
                try{
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);
                    urlConnection.connect();
                    urlConnection.getOutputStream().write(("email=" + this.email + "&password=" + this.password).getBytes());
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(in);
                    int data = isr.read();
                    while(data != - 1){
                        current += (char) data;
                        data = isr.read();
                    }
                    return current;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if (urlConnection != null){
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            return current;
        }
        @Override
        protected void onPostExecute(String s) {
            try {
                // Log.d("ERR", s);
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("authorization");
                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                if (jsonObject1.getInt("code") == 101) {
                    SharedPreferences.Editor ed = settings_profile.edit();
                    ed.remove("email");
                    ed.remove("password");
                    ed.apply();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            loadingView.setVisibility(View.GONE);
                            registryView.setVisibility(View.VISIBLE);
                        }
                    }, 1500);   //5 seconds
                }
                else if (jsonObject1.getInt("code") == 100) {
                    // Log.d("SUCCESS", s);
                    FitUser fitUser = new FitUser();
                    fitUser.setId(jsonObject1.getInt("id"));
                    fitUser.setFirst_name(jsonObject1.getString("first_name"));
                    fitUser.setLast_name(jsonObject1.getString("last_name"));
                    fitUser.setEmail(jsonObject1.getString("email"));
                    fitUser.setPassword(jsonObject1.getString("password"));
                    fitUser.setDevice_info(jsonObject1.getString("device_info"));
                    fitUser.setGender(jsonObject1.getString("gender"));
                    fitUser.setAge(jsonObject1.getInt("age"));
                    fitUser.setCurrent_weight(jsonObject1.getDouble("current_weight"));
                    fitUser.setDesired_weight(jsonObject1.getDouble("desired_weight"));
                    fitUser.setGrowth(jsonObject1.getDouble("growth"));
                    fitUser.setMonthly_budget(jsonObject1.getDouble("monthly_budget"));
                    fitUser.setIntolerance(jsonObject1.getString("intolerance"));
                    fitUser.setFavorite_foods(jsonObject1.getString("favorite_foods"));
                    fitUser.setUnloved_foods(jsonObject1.getString("unloved_foods"));
                    Intent main_activity = new Intent(SplashActivity.this, MainActivity.class);
                    main_activity.putExtra("fitUser", fitUser);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            startActivity(main_activity);
                            finish();
                        }
                    }, 1500);   //5 seconds
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}