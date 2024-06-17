package com.fitexpert.fitboom.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fitexpert.fitboom.CurrentTrenningActivivty;
import com.fitexpert.fitboom.DailyExercise;
import com.fitexpert.fitboom.MonthlyFoodDB;
import com.fitexpert.fitboom.R;
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

public class MealPlanFragment extends Fragment {
    SharedPreferences settings_profile;
    LinearLayout linear_layout_loading, linear_layout_days;
    TextView days[] = new TextView[32];
    TextView start_marathon, shopping_list;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_meal_plan, null);
        settings_profile = getActivity().getSharedPreferences("settings_profile", MODE_PRIVATE);
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
        Date current_date = new Date();
        for (int i = 1; i <= current_date.getDate(); i++) {
            days[i].setBackground(getContext().getDrawable(R.drawable.daily_trenning_true));
            days[i].setPadding(25, 25, 25, 25);
        }
        days[current_date.getDate()].setBackground(getContext().getDrawable(R.drawable.daily_trenning_current));
        linear_layout_days = (LinearLayout) v.findViewById(R.id.linear_layout_days);
        linear_layout_loading = (LinearLayout) v.findViewById(R.id.linear_layout_loading);
        start_marathon = (TextView) v.findViewById(R.id.start_marathon);
        shopping_list = (TextView) v.findViewById(R.id.shopping_list);
        start_marathon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                Toast toast = Toast.makeText(getContext(),
                        "Марафон пока не начался!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        shopping_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                Toast toast = Toast.makeText(getContext(),
                        "Данный раздел находится в разработке!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        days[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(1, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(2, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(3, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(4, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(5, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(6, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(7, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(1, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(8, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(9, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[21].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(21, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(10, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(11, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(12, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(13, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(14, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(15, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[16].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(16, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[17].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(17, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[18].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(18, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[19].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(19, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[20].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(21, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[22].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(22, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[23].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(23, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[24].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(24, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[25].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(25, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[26].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(26, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[27].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(27, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[28].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(28, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[29].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(29, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        days[30].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);
                v.startAnimation(anim);
                new GetMealPlan(30, settings_profile.getString("email", "-"), settings_profile.getString("password", "-")).execute("https://testmatica.ru/fitboom_api/get_monthlyfood.php");
            }
        });
        return v;
    }

    private class GetMealPlan extends AsyncTask<String, String, String> {
        int date_day;
        String email, password;

        public GetMealPlan(int date_day, String email, String password) {
            this.date_day = date_day;
            this.email = email;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... url1) {
            String current = "";
            String JSON_URL = url1[0] + "?date_day=" + date_day;
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
                JSONArray jsonArray = jsonObject.getJSONArray("monthlyfood");
                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                if (jsonObject1.getInt("code") == 100) {
                    MonthlyFoodDB monthlyFoodDB = new MonthlyFoodDB(jsonObject1.getInt("id"), jsonObject1.getInt("date_day"), jsonObject1.getString("breakfast_name"), jsonObject1.getString("lunch_name"), jsonObject1.getString("dinner_name"), jsonObject1.getString("breakfast_recept"), jsonObject1.getString("lunch_recept"), jsonObject1.getString("dinner_recept"), jsonObject1.getInt("breakfast_kkal"), jsonObject1.getInt("lunch_kkal"), jsonObject1.getInt("dinner_kkal"));
                    Intent current_meal_plan = new Intent(getContext(), CurrentMealPlanActivity.class);
                    current_meal_plan.putExtra("monthlyfood", monthlyFoodDB);
                    startActivity(current_meal_plan);
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