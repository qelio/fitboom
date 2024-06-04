package com.fitexpert.fitboom.fragments;

import static android.content.Context.MODE_PRIVATE;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fitexpert.fitboom.CurrentTrenningActivivty;
import com.fitexpert.fitboom.DailyTrenning;
import com.fitexpert.fitboom.FitUser;
import com.fitexpert.fitboom.MainActivity;
import com.fitexpert.fitboom.PreferencesActivity;
import com.fitexpert.fitboom.R;
import com.fitexpert.fitboom.SignUpActivity;
import com.fitexpert.fitboom.SplashActivity;

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
import java.util.Date;

public class WorkoutFragment extends Fragment {
    SharedPreferences settings_profile;
    // Кнопки выбора дней:
    TextView day_1, day_2, day_3, day_4, day_5, day_6, day_7, day_8, day_9, day_10, day_11, day_12, day_13, day_14, day_15, day_16;
    TextView day_17, day_18, day_19, day_20, day_21, day_22, day_23, day_24, day_25, day_26, day_27, day_28, day_29, day_30, day_31;
    TextView days[] = new TextView[32];
    boolean days_flag[] = new boolean[35];
    LinearLayout linear_layout_loading, linear_layout_days;
    int current_trenning = 1;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_workout, null);
        final Animation animAlpha1 = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
        final Animation animAlpha2 = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
        final Animation animAlpha3 = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
        final Animation animAlpha4 = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
        settings_profile = getActivity().getSharedPreferences("settings_profile", MODE_PRIVATE);
        // Кнопки выбора режима тренировки:
        LinearLayout whole_body = (LinearLayout) v.findViewById(R.id.whole_body);
        LinearLayout abs = (LinearLayout) v.findViewById(R.id.abs);
        LinearLayout legs = (LinearLayout) v.findViewById(R.id.legs);
        LinearLayout buttocks = (LinearLayout) v.findViewById(R.id.buttocks);
        linear_layout_days = (LinearLayout) v.findViewById(R.id.linear_layout_days);
        linear_layout_loading = (LinearLayout) v.findViewById(R.id.linear_layout_loading);
        Button start_trenning = (Button) v.findViewById(R.id.start_trenning);
        // Кнопки выбора дней:
        days[1] = (TextView) v.findViewById(R.id.day_1);
        days[2] = (TextView) v.findViewById(R.id.day_2);
        days[3] = (TextView) v.findViewById(R.id.day_3);
        days[4] = (TextView) v.findViewById(R.id.day_4);
        days[5] = (TextView) v.findViewById(R.id.day_5);
        days[6] = (TextView) v.findViewById(R.id.day_6);
        days[7] = (TextView) v.findViewById(R.id.day_7);
        days[8] = (TextView) v.findViewById(R.id.day_8);
        days[9] = (TextView) v.findViewById(R.id.day_9);
        days[10] = (TextView) v.findViewById(R.id.day_10);
        days[11] = (TextView) v.findViewById(R.id.day_11);
        days[12] = (TextView) v.findViewById(R.id.day_12);
        days[13] = (TextView) v.findViewById(R.id.day_13);
        days[14] = (TextView) v.findViewById(R.id.day_14);
        days[15] = (TextView) v.findViewById(R.id.day_15);
        days[16] = (TextView) v.findViewById(R.id.day_16);
        days[17] = (TextView) v.findViewById(R.id.day_17);
        days[18] = (TextView) v.findViewById(R.id.day_18);
        days[19] = (TextView) v.findViewById(R.id.day_19);
        days[20] = (TextView) v.findViewById(R.id.day_20);
        days[21] = (TextView) v.findViewById(R.id.day_21);
        days[22] = (TextView) v.findViewById(R.id.day_22);
        days[23] = (TextView) v.findViewById(R.id.day_23);
        days[24] = (TextView) v.findViewById(R.id.day_24);
        days[25] = (TextView) v.findViewById(R.id.day_25);
        days[26] = (TextView) v.findViewById(R.id.day_26);
        days[27] = (TextView) v.findViewById(R.id.day_27);
        days[28] = (TextView) v.findViewById(R.id.day_28);
        days[29] = (TextView) v.findViewById(R.id.day_29);
        days[30] = (TextView) v.findViewById(R.id.day_30);
        days[31] = (TextView) v.findViewById(R.id.day_31);
        whole_body.setBackground(v.getContext().getDrawable(R.drawable.type_trenning_active));
        abs.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
        legs.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
        buttocks.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
        isConnectingToInternet(v.getContext());
        Date currunet_date = new Date();
        linear_layout_days.setVisibility(View.GONE);
        linear_layout_loading.setVisibility(View.VISIBLE);
        Log.d("DEBUG", currunet_date.getDay() + " " + currunet_date.getMonth() + " " +Integer.toString(currunet_date.getYear() + 1900));
        for (int i = 1; i <= currunet_date.getDay() + 2; i++) {
            days_flag[i] = false;
        }
        for (int i = 1; i <= currunet_date.getDay() + 2; i++) {
            new GetDayInfo(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), i, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, 1).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
        }

        whole_body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha1);
                whole_body.setBackground(v.getContext().getDrawable(R.drawable.type_trenning_active));
                abs.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
                legs.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
                buttocks.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
                current_trenning = 1;
                linear_layout_days.setVisibility(View.GONE);
                linear_layout_loading.setVisibility(View.VISIBLE);
                for (int i = 1; i <= currunet_date.getDay() + 2; i++) {
                    days_flag[i] = false;
                }
                for (int i = 1; i <= currunet_date.getDay() + 2; i++) {
                    new GetDayInfo(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), i, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                }
            }
        });

        start_trenning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), currunet_date.getDay() + 2, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), currunet_date.getDay() + 2, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        abs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha2);
                whole_body.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
                abs.setBackground(v.getContext().getDrawable(R.drawable.type_trenning_active));
                legs.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
                buttocks.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
                current_trenning = 2;
                linear_layout_days.setVisibility(View.GONE);
                linear_layout_loading.setVisibility(View.VISIBLE);
                for (int i = 1; i <= currunet_date.getDay() + 2; i++) {
                    days_flag[i] = false;
                }
                for (int i = 1; i <= currunet_date.getDay() + 2; i++) {
                    new GetDayInfo(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), i, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                }
            }
        });

        legs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha3);
                whole_body.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
                abs.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
                legs.setBackground(v.getContext().getDrawable(R.drawable.type_trenning_active));
                buttocks.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
                current_trenning = 3;
                linear_layout_days.setVisibility(View.GONE);
                linear_layout_loading.setVisibility(View.VISIBLE);
                for (int i = 1; i <= currunet_date.getDay() + 2; i++) {
                    days_flag[i] = false;
                }
                for (int i = 1; i <= currunet_date.getDay() + 2; i++) {
                    new GetDayInfo(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), i, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                }
            }
        });

        buttocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha4);
                whole_body.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
                abs.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
                legs.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
                buttocks.setBackground(v.getContext().getDrawable(R.drawable.type_trenning_active));
                current_trenning = 4;
                linear_layout_days.setVisibility(View.GONE);
                linear_layout_loading.setVisibility(View.VISIBLE);
                for (int i = 1; i <= currunet_date.getDay() + 2; i++) {
                    days_flag[i] = false;
                }
                for (int i = 1; i <= currunet_date.getDay() + 2; i++) {
                    new GetDayInfo(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), i, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                }
            }
        });

        days[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 1;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 2;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 3;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 4;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 5;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 6;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 7;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 8;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 9;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 10;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 11;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 12;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 13;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 14;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 15;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[16].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 16;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[17].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 17;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[18].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 18;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[19].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 19;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[20].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 20;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[21].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 21;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[22].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 22;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[23].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 23;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[24].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 24;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[25].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 25;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[26].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 26;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[27].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 27;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[28].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 28;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[29].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 29;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[30].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 30;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });

        days[31].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                int this_day = 31;
                new SetDayTrenning(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
                new GoToTrenningActivity(v.getContext(), settings_profile.getString("email", "-"), settings_profile.getString("password", "-"), this_day, currunet_date.getMonth() + 1, currunet_date.getYear() + 1900, current_trenning).execute("https://testmatica.ru/fitboom_api/daily_trenning.php");
            }
        });
        return v;
    }
    private boolean isConnectingToInternet(Context applicationContext){
        ConnectivityManager cm = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            Toast.makeText(applicationContext, "Нет подключения к Интернету!", Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            return true;
        }

    }
    private class GetDayInfo extends AsyncTask<String, String, String> {
        String email, password;
        int day, month, year, trenning_type;
        Context context;
        public GetDayInfo(Context context, String email, String password, int day, int month, int year, int trenning_type) {
            this.context = context;
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
                    days_flag[day] = true;
                    DailyTrenning dailyTrenning = new DailyTrenning();
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
                    int count_exercises = dailyTrenning.getCountExercises();
                    Log.d("DATE_CHECK", day + " " + Integer.toString(current_date.getDay() + 2));
                    if (count_exercises == 6) {
                        days[day].setBackground(context.getDrawable(R.drawable.daily_trenning_true));
                        days[day].setPadding(25, 25, 25, 25);
                    }
                    else if (count_exercises == 0) {
                        days[day].setBackground(context.getDrawable(R.drawable.daily_trenning_false));
                        days[day].setPadding(25, 25, 25, 25);
                    }
                    else {
                        days[day].setBackground(context.getDrawable(R.drawable.daily_trenning_middle));
                        days[day].setPadding(25, 25, 25, 25);
                    }
                    if (day == current_date.getDay() + 2) {
                        days[day].setBackground(context.getDrawable(R.drawable.daily_trenning_current));
                        days[day].setPadding(25, 25, 25, 25);
                    }
                }
                if (jsonObject1.getInt("code") == 101) {
                    Intent main_activity = new Intent(getActivity(), SplashActivity.class);
                    startActivity(main_activity);
                    getActivity().finish();
                }
                boolean wait_loadings_all = true;
                for (int i = 1; i <= current_date.getDay() + 2; i++) {
                    if (days_flag[i] == true) {
                        Log.d("Flag" + i, "true");
                    }
                    if (days_flag[i] == false) {
                        wait_loadings_all = false;
                        Log.d("Flag" + i, "flag");
                    }
                }
                if (wait_loadings_all == true) {
                    Log.d("FlagGlav", "true");
                }
                else {
                    Log.d("FlagGlav", "false");
                }
                if (wait_loadings_all) {
                    linear_layout_days.setVisibility(View.VISIBLE);
                    linear_layout_loading.setVisibility(View.GONE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

    private class GoToTrenningActivity extends AsyncTask<String, String, String> {
        String email, password;
        int day, month, year, trenning_type;
        Context context;
        public GoToTrenningActivity(Context context, String email, String password, int day, int month, int year, int trenning_type) {
            this.context = context;
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
                    DailyTrenning dailyTrenning = new DailyTrenning();
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
                    Intent current_trenning_activity = new Intent(getActivity(), CurrentTrenningActivivty.class);
                    current_trenning_activity.putExtra("daily_trenning", dailyTrenning);
                    startActivity(current_trenning_activity);
                }
                if (jsonObject1.getInt("code") == 101) {
                    Intent auth_activity = new Intent(getActivity(), SplashActivity.class);
                    startActivity(auth_activity);
                    getActivity().finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

    private class SetDayTrenning extends AsyncTask<String, String, String> {
        String email, password;
        int day, month, year, trenning_type;
        Context context;
        public SetDayTrenning(Context context, String email, String password, int day, int month, int year, int trenning_type) {
            this.context = context;
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
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }
}