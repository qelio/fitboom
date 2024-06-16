package com.fitexpert.fitboom.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fitexpert.fitboom.MonthlyFoodDB;
import com.fitexpert.fitboom.R;
import com.fitexpert.fitboom.SplashActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class ReportFragment extends Fragment {

    TextView count_exercises, count_kkal, count_time;
    SharedPreferences settings_profile;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_report, null);
        count_exercises = (TextView) v.findViewById(R.id.count_exercises);
        count_kkal = (TextView) v.findViewById(R.id.count_kkal);
        count_time = (TextView) v.findViewById(R.id.count_time);
        settings_profile = getActivity().getSharedPreferences("settings_profile", MODE_PRIVATE);
        Date current_date = new Date();
        new GetCountParam(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), "exercises", current_date.getDate(), current_date.getMonth() + 1, current_date.getYear() + 1900, count_exercises).execute("https://testmatica.ru/fitboom_api/report_user.php");
        new GetCountParam(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), "kkal", current_date.getDate(), current_date.getMonth() + 1, current_date.getYear() + 1900, count_kkal).execute("https://testmatica.ru/fitboom_api/report_user.php");
        new GetCountParam(settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), "time", current_date.getDate(), current_date.getMonth() + 1, current_date.getYear() + 1900, count_time).execute("https://testmatica.ru/fitboom_api/report_user.php");
        return v;
    }

    private class GetCountParam extends AsyncTask<String, String, String> {
        String email, password, report_type;
        int date_day, date_month, date_year;
        TextView to_insert;

        public GetCountParam(String email, String password, String report_type, int date_day, int date_month, int date_year, TextView to_insert) {
            this.email = email;
            this.password = password;
            this.report_type = report_type;
            this.date_day = date_day;
            this.date_month = date_month;
            this.date_year = date_year;
            this.to_insert = to_insert;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... url1) {
            String current = "";
            String JSON_URL = url1[0] + "?date_day=" + date_day + "&date_month=" + date_month + "&date_year=" + date_year + "&report_type=" + report_type;
            Log.d("TO_RETURN", JSON_URL);
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
                JSONArray jsonArray = jsonObject.getJSONArray("report_user");
                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                if (jsonObject1.getInt("code") == 100) {
                    to_insert.setText(Integer.toString(jsonObject1.getInt("return")));
                }
                if (jsonObject1.getInt("code") == 101) {
                    Intent auth_activity = new Intent(getContext(), SplashActivity.class);
                    startActivity(auth_activity);
                    getActivity().finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}