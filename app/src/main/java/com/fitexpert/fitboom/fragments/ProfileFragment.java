package com.fitexpert.fitboom.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fitexpert.fitboom.FitUser;
import com.fitexpert.fitboom.MainActivity;
import com.fitexpert.fitboom.PreferencesActivity;
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

public class ProfileFragment extends Fragment {

    SharedPreferences settings_profile;
    TextView growth, current_weight, desired_weight, gender;
    Button edit_user;
    FitUser fitUser = new FitUser();
    LinearLayout log_out;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, null);
        settings_profile = getActivity().getSharedPreferences("settings_profile", MODE_PRIVATE);
        growth = (TextView) v.findViewById(R.id.growth);
        current_weight = (TextView) v.findViewById(R.id.current_weight);
        desired_weight = (TextView) v.findViewById(R.id.desired_weight);
        gender = (TextView) v.findViewById(R.id.gender);
        edit_user = (Button) v.findViewById(R.id.edit_user);
        log_out = (LinearLayout) v.findViewById(R.id.log_out);
        new GetFitUser(settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/authorization.php");
        edit_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fitUser.getId() != 0) {
                    Intent edit_profile = new Intent(getContext(), PreferencesActivity.class);
                    edit_profile.putExtra("fitUser", fitUser);
                    startActivity(edit_profile);
                }
            }
        });
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                SharedPreferences.Editor ed = settings_profile.edit();
                ed.remove("email");
                ed.remove("password");
                ed.apply();
                Intent splash_activity = new Intent(getContext(), SplashActivity.class);
                startActivity(splash_activity);
                getActivity().finish();
            }
        });

        return v;
    }

    private class GetFitUser extends AsyncTask<String, String, String> {
        String email, password;

        public GetFitUser(String email, String password) {
            this.email = email;
            this.password = password;
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
                    Intent auth_activity = new Intent(getContext(), SplashActivity.class);
                    startActivity(auth_activity);
                    getActivity().finish();
                }
                else if (jsonObject1.getInt("code") == 100) {
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
                    growth.setText(Double.toString(fitUser.getGrowth()) + " см");
                    current_weight.setText(Double.toString(fitUser.getCurrent_weight()) + " кг");
                    desired_weight.setText(Double.toString(fitUser.getDesired_weight()) + " кг");
                    gender.setText(fitUser.getGender());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        new GetFitUser(settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/authorization.php");
    }
}