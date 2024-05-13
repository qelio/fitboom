package com.fitexpert.fitboom;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
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
import java.util.ArrayList;
import java.util.Collections;

public class PreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        Window window = this.getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.primary_color));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.primary_color));
        actionBar.setTitle("Данные о себе");
        FitUser fitUser = (FitUser) getIntent().getSerializableExtra("fitUser");
        String[] products_array = {
                "Хлеб",
                "Молоко",
                "Яйца",
                "Масло",
                "Сахар",
                "Мука",
                "Рис",
                "Картофель",
                "Мясо",
                "Рыба",
                "Макароны",
                "Сыр",
                "Овощи",
                "Фрукты",
                "Чай"
        };
        boolean[] selectedProducts1;
        ArrayList<Integer> Products1List = new ArrayList<>();
        selectedProducts1 = new boolean[products_array.length];
        TextView intolerance = (TextView) findViewById(R.id.intolerance);
        intolerance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PreferencesActivity.this);
                builder.setTitle("Выберите продукты");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(products_array, selectedProducts1, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if (b) {
                            Products1List.add(i);
                            Collections.sort(Products1List);
                        } else {
                            Products1List.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j = 0; j < Products1List.size(); j++) {
                            stringBuilder.append(products_array[Products1List.get(j)]);
                            if (j != Products1List.size() - 1) {
                                stringBuilder.append(", ");
                            }
                        }
                        intolerance.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Очистить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (int j = 0; j < selectedProducts1.length; j++) {
                            selectedProducts1[j] = false;
                            Products1List.clear();
                            intolerance.setText("");
                        }
                    }
                });
                builder.show();
            }
        });

        boolean[] selectedProducts2;
        ArrayList<Integer> Products2List = new ArrayList<>();
        selectedProducts2 = new boolean[products_array.length];
        TextView favorite_foods = (TextView) findViewById(R.id.favorite_foods);
        favorite_foods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PreferencesActivity.this);
                builder.setTitle("Выберите продукты");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(products_array, selectedProducts2, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if (b) {
                            Products2List.add(i);
                            Collections.sort(Products2List);
                        } else {
                            Products2List.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j = 0; j < Products2List.size(); j++) {
                            stringBuilder.append(products_array[Products2List.get(j)]);
                            if (j != Products2List.size() - 1) {
                                stringBuilder.append(", ");
                            }
                        }
                        favorite_foods.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Очистить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (int j = 0; j < selectedProducts2.length; j++) {
                            selectedProducts2[j] = false;
                            Products2List.clear();
                            favorite_foods.setText("");
                        }
                    }
                });
                builder.show();
            }
        });

        boolean[] selectedProducts3;
        ArrayList<Integer> Products3List = new ArrayList<>();
        selectedProducts3 = new boolean[products_array.length];
        TextView unloved_foods = (TextView) findViewById(R.id.unloved_foods);
        unloved_foods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PreferencesActivity.this);
                builder.setTitle("Выберите продукты");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(products_array, selectedProducts3, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if (b) {
                            Products3List.add(i);
                            Collections.sort(Products3List);
                        } else {
                            Products3List.remove(Integer.valueOf(i));
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j = 0; j < Products3List.size(); j++) {
                            stringBuilder.append(products_array[Products3List.get(j)]);
                            if (j != Products3List.size() - 1) {
                                stringBuilder.append(", ");
                            }
                        }
                        unloved_foods.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setNeutralButton("Очистить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (int j = 0; j < selectedProducts3.length; j++) {
                            selectedProducts3[j] = false;
                            Products3List.clear();
                            unloved_foods.setText("");
                        }
                    }
                });
                builder.show();
            }
        });

        Spinner spinner_gender = (Spinner) findViewById(R.id.spinner_gender);
        EditText user_age = (EditText) findViewById(R.id.user_age);
        EditText user_current_weight = (EditText) findViewById(R.id.user_current_weight);
        EditText user_desired_weight = (EditText) findViewById(R.id.user_desired_weight);
        EditText user_growth = (EditText) findViewById(R.id.user_growth);
        EditText user_monthly_budget = (EditText) findViewById(R.id.user_monthly_budget);
        Button next_button_first = (Button) findViewById(R.id.next_button_first);
        Button next_button_second = (Button) findViewById(R.id.next_button_second);
        LinearLayout step_first = (LinearLayout) findViewById(R.id.step_first);
        LinearLayout step_second = (LinearLayout) findViewById(R.id.step_second);
        LinearLayout loading_layout = (LinearLayout) findViewById(R.id.loading_layout);
        next_button_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                if (user_age.getText().toString().equals("") || user_current_weight.getText().toString().equals("") || user_desired_weight.getText().toString().equals("") || user_growth.getText().toString().equals("") || user_monthly_budget.getText().toString().equals("")) {
                    if (user_age.getText().toString().equals("")) {
                        user_age.setError("Поле не может быть пустым!");
                    }
                    if (user_current_weight.getText().toString().equals("")) {
                        user_current_weight.setError("Поле не может быть пустым!");
                    }
                    if (user_desired_weight.getText().toString().equals("")) {
                        user_desired_weight.setError("Поле не может быть пустым!");
                    }
                    if (user_growth.getText().toString().equals("")) {
                        user_growth.setError("Поле не может быть пустым!");
                    }
                    if (user_monthly_budget.getText().toString().equals("")) {
                        user_monthly_budget.setError("Поле не может быть пустым!");
                    }
                    return;
                }
                step_first.setVisibility(View.GONE);
                step_second.setVisibility(View.VISIBLE);
            }
        });
        next_button_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                new EditUser(fitUser, spinner_gender.getSelectedItem().toString(),Integer.parseInt(user_age.getText().toString()), Double.parseDouble(user_current_weight.getText().toString()), Double.parseDouble(user_desired_weight.getText().toString()), Double.parseDouble(user_growth.getText().toString()), Double.parseDouble(user_monthly_budget.getText().toString()), intolerance.getText().toString(), favorite_foods.getText().toString(), unloved_foods.getText().toString(), step_second, loading_layout).execute("https://testmatica.ru/fitboom_api/edit_values.php");
            }
        });
    }

    // Регистрация нового пользователя
    private class EditUser extends AsyncTask<String, String, String> {
        String gender, intolerance, favorite_foods, unloved_foods;
        int age;
        double current_weight, desired_weight, growth, monthly_budget;
        FitUser fitUser;
        LinearLayout step_second, loading_layout;
        public EditUser (FitUser fitUser, String gender, int age, double current_weight, double desired_weight, double growth, double monthly_budget, String intolerance, String favorite_foods, String unloved_foods, LinearLayout step_second, LinearLayout loading_layout) {
            this.fitUser = fitUser;
            this.gender = gender;
            this.age = age;
            this.current_weight = current_weight;
            this.desired_weight = desired_weight;
            this.growth = growth;
            this.monthly_budget = monthly_budget;
            this.intolerance = intolerance;
            this.favorite_foods = favorite_foods;
            this.unloved_foods = unloved_foods;
            this.step_second = step_second;
            this.loading_layout = loading_layout;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            step_second.setVisibility(View.GONE);
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
                    if (intolerance.equals("")) {
                        intolerance = "Продукты не выбраны";
                    }
                    if (favorite_foods.equals("")) {
                        favorite_foods = "Продукты не выбраны";
                    }
                    if (unloved_foods.equals("")) {
                        unloved_foods = "Продукты не выбраны";
                    }
                    String request = "id=" + fitUser.getId() + "&email=" + fitUser.getEmail() + "&password=" + fitUser.getPassword() + "&first_name=" + fitUser.getFirst_name() + "&last_name=" + fitUser.getLast_name() + "&gender=" + gender + "&age=" + age + "&current_weight=" + current_weight + "&desired_weight=" + desired_weight + "&growth=" + growth + "&monthly_budget=" + monthly_budget + "&device_info=" + fitUser.getDevice_info() + "&intolerance=" + intolerance + "&favorite_foods=" + favorite_foods + "&unloved_foods=" + unloved_foods;
                    urlConnection.getOutputStream().write((request).getBytes());
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
                JSONArray jsonArray = jsonObject.getJSONArray("edit_values");
                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                if (jsonObject1.getInt("code") == 103) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            s, Toast.LENGTH_SHORT);
                    toast.show();
                    Intent splashActivity = new Intent(PreferencesActivity.this, SplashActivity.class);
                    splashActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(splashActivity);
                }
                if (jsonObject1.getInt("code") == 100) {
                    Intent main_activity = new Intent(PreferencesActivity.this, MainActivity.class);
                    main_activity.putExtra("fitUser", fitUser);
                    main_activity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(main_activity);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}