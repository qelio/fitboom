package com.fitexpert.fitboom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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

public class SignUpActivity extends AppCompatActivity {
    SharedPreferences settings_profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Window window = this.getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.primary_color));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.primary_color));
        actionBar.setTitle("Регистрация");
        EditText user_name = (EditText) findViewById(R.id.user_name);
        EditText user_surname = (EditText) findViewById(R.id.user_surname);
        EditText user_email = (EditText) findViewById(R.id.user_email);
        EditText user_password = (EditText) findViewById(R.id.user_password);
        EditText user_password_again = (EditText) findViewById(R.id.user_password_again);
        Button registryButton = (Button) findViewById(R.id.registryButton);
        LinearLayout loading_layout = (LinearLayout) findViewById(R.id.loading_layout);
        ScrollView registry_scroll = (ScrollView) findViewById(R.id.registry_scroll);
        registryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                if (user_name.getText().toString().equals("") || user_surname.getText().toString().equals("") || user_email.getText().toString().equals("") || user_password.getText().toString().equals("") || user_password_again.getText().toString().equals("")) {
                    if (user_name.getText().toString().equals("")) {
                        user_name.setError("Данное поле не должно быть пустым!");
                    }
                    if (user_surname.getText().toString().equals("")) {
                        user_surname.setError("Данное поле не должно быть пустым!");
                    }
                    if (user_email.getText().toString().equals("")) {
                        user_email.setError("Данное поле не должно быть пустым!");
                    }
                    if (user_password.getText().toString().equals("")) {
                        user_password.setError("Данное поле не должно быть пустым!");
                    }
                    if (user_password_again.getText().toString().equals("")) {
                        user_password_again.setError("Данное поле не должно быть пустым!");
                    }
                    return;
                }
                if (!user_password.getText().toString().equals(user_password_again.getText().toString())) {
                    user_password_again.setError("Пароль не совпадает!");
                    return;
                }
                new GetMD5Password(user_name.getText().toString(), user_surname.getText().toString(), user_email.getText().toString(), user_password.getText().toString(), loading_layout, registry_scroll, user_email).execute("https://testmatica.ru/fitboom_api/password_to_md5.php");
            }
        });
    }

    // Получение пароля в хэшированном виде (MD5)
    private class GetMD5Password extends AsyncTask<String, String, String> {
        String user_name, user_surname, user_email, user_password, md5_password;
        LinearLayout loading_layout;
        ScrollView registry_scroll;
        EditText edit_email;
        public GetMD5Password (String user_name, String user_surname, String user_email, String user_password, LinearLayout loading_layout, ScrollView registry_scroll, EditText edit_email) {
            this.user_name = user_name;
            this.user_surname = user_surname;
            this.user_email = user_email;
            this.user_password = user_password;
            this.loading_layout = loading_layout;
            this.registry_scroll = registry_scroll;
            this.edit_email = edit_email;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            registry_scroll.setVisibility(View.GONE);
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
                    urlConnection.getOutputStream().write(("password=" + this.user_password).getBytes());
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
                new RegistryUser(user_name, user_surname, user_email, md5_password, loading_layout, registry_scroll, edit_email).execute("https://testmatica.ru/fitboom_api/registration.php");
               } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    // Регистрация нового пользователя
    private class RegistryUser extends AsyncTask<String, String, String> {
        String user_name, user_surname, user_email, user_password;
        LinearLayout loading_layout;
        ScrollView registry_scroll;
        EditText edit_email;
        public RegistryUser (String user_name, String user_surname, String user_email, String user_password, LinearLayout loading_layout, ScrollView registry_scroll, EditText edit_email) {
            this.user_name = user_name;
            this.user_surname = user_surname;
            this.user_email = user_email;
            this.user_password = user_password;
            this.loading_layout = loading_layout;
            this.registry_scroll = registry_scroll;
            this.edit_email = edit_email;
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
                    urlConnection.getOutputStream().write(("email=" + this.user_email + "&password=" + this.user_password + "&first_name=" + this.user_name + "&last_name=" + this.user_surname).getBytes());
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
                JSONArray jsonArray = jsonObject.getJSONArray("registration");
                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                if (jsonObject1.getInt("code") == 103) {
                    registry_scroll.setVisibility(View.VISIBLE);
                    loading_layout.setVisibility(View.GONE);
                    edit_email.setError("Данный email уже зарегистрирован!");
                }
                if (jsonObject1.getInt("code") == 100) {
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
                    settings_profile = getSharedPreferences("settings_profile", MODE_PRIVATE);
                    SharedPreferences.Editor ed = settings_profile.edit();
                    ed.putString("email", user_email);
                    ed.putString("password", user_password);
                    ed.apply();
                    Intent main_activity = new Intent(SignUpActivity.this, PreferencesActivity.class);
                    main_activity.putExtra("fitUser", fitUser);
                    startActivity(main_activity);
                    finish();
                }
            } catch (JSONException e) {
            e.printStackTrace();
            }
        }
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
}