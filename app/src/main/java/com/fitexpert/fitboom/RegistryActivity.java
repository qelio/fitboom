package com.fitexpert.fitboom;

import static android.app.PendingIntent.getActivity;

import static java.nio.charset.StandardCharsets.UTF_8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.fitexpert.fitboom.fragments.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RegistryActivity extends AppCompatActivity {
    SharedPreferences settings_profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        Window window = this.getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.primary_color));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.primary_color));
        actionBar.setTitle("Вход или регистрация");
        TextView go_to_sign_up = (TextView) findViewById(R.id.go_to_sign_up);
        TextView go_to_recovery = (TextView) findViewById(R.id.go_to_recovery);
        TextView error_auth = (TextView) findViewById(R.id.error_auth);
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.scale);
        LinearLayout loading_layout = (LinearLayout) findViewById(R.id.loading_layout);
        ScrollView sign_in_layout = (ScrollView) findViewById(R.id.sign_in_layout);
        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.password);
        Button registry_button = (Button) findViewById(R.id.registryButton);
        email.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                error_auth.setVisibility(View.GONE);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        password.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                error_auth.setVisibility(View.GONE);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        registry_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                if (email.getText().toString().equals("") || password.getText().toString().equals("")) {
                    if (email.getText().toString().equals("")) {
                        email.setError("Данное поле не должно быть пустым!");
                    }
                    if (password.getText().toString().equals("")) {
                        password.setError("Данное поле не должно быть пустым!");
                    }
                    return;
                }
                new GetMD5Password(email.getText().toString(), password.getText().toString(), sign_in_layout, loading_layout, error_auth).execute("https://testmatica.ru/fitboom_api/password_to_md5.php");
            }
        });

        go_to_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_to_sign = new Intent(RegistryActivity.this, SignUpActivity.class);
                v.startAnimation(animAlpha);
                startActivity(go_to_sign);
                // new GetMD5Password("sdfsdf", loading_layout, sign_in_layout).execute("https://testmatica.ru/fitboom_api/password_to_md5.php");
            }
        });
        go_to_recovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_to_recovery = new Intent(RegistryActivity.this, RecoveryPasswordActivity.class);
                v.startAnimation(animAlpha);
                startActivity(go_to_recovery);
            }
        });
    }
    // Получение пароля в хэшированном виде (MD5)
    private class GetMD5Password extends AsyncTask<String, String, String> {
        String email, password, md5_password;
        LinearLayout loading_layout;
        ScrollView sign_in_layout;
        TextView error_auth;
        public GetMD5Password(String email, String password, ScrollView sign_in_layout, LinearLayout loading_layout, TextView error_auth){
            this.email = email;
            this.password = password;
            this.sign_in_layout = sign_in_layout;
            this.loading_layout = loading_layout;
            this.error_auth = error_auth;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            sign_in_layout.setVisibility(View.GONE);
            loading_layout.setVisibility(View.VISIBLE);
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
                    urlConnection.getOutputStream().write(("password=" + this.password).getBytes());
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
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("password_to_md5");
                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                this.md5_password = jsonObject1.getString("password");
                new AuthorizationUser(email, md5_password, sign_in_layout, loading_layout, error_auth).execute("https://testmatica.ru/fitboom_api/authorization.php");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    // Авторизация пользователя
    private class AuthorizationUser extends AsyncTask<String, String, String> {
        String email, password;
        LinearLayout loading_layout;
        ScrollView sign_in_layout;
        TextView error_auth;
        public AuthorizationUser(String email, String password, ScrollView sign_in_layout, LinearLayout loading_layout, TextView error_auth){
            this.email = email;
            this.password = password;
            this.sign_in_layout = sign_in_layout;
            this.loading_layout = loading_layout;
            this.error_auth = error_auth;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("authorization");
                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                if (jsonObject1.getInt("code") == 101) {
                    loading_layout.setVisibility(View.GONE);
                    sign_in_layout.setVisibility(View.VISIBLE);
                    error_auth.setVisibility(View.VISIBLE);
                }
                else if (jsonObject1.getInt("code") == 100) {
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
                    Log.d("DEBUG", fitUser.getIntolerance() + " " + fitUser.getFavorite_foods() + " " + fitUser.getUnloved_foods());
                    settings_profile = getSharedPreferences("settings_profile", MODE_PRIVATE);
                    SharedPreferences.Editor ed = settings_profile.edit();
                    ed.putString("email", email);
                    ed.putString("password", password);
                    ed.apply();
                    Intent main_activity = new Intent(RegistryActivity.this, MainActivity.class);
                    main_activity.putExtra("fitUser", fitUser);
                    startActivity(main_activity);
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

