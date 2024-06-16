package com.fitexpert.fitboom.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import com.fitexpert.fitboom.MonthlyFoodDB;
import com.fitexpert.fitboom.R;

import org.w3c.dom.Text;

public class CurrentMealPlanActivity extends AppCompatActivity {
    TextView breakfast_name, lunch_name, dinner_name;
    TextView breakfast_recept, lunch_recept, dinner_recept;
    TextView breakfast_kkal, lunch_kkal, dinner_kkal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_meal_plan);
        Window window = this.getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.primary_color));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.primary_color));
        Bundle arguments = getIntent().getExtras();
        breakfast_name = (TextView) findViewById(R.id.breakfast_name);
        breakfast_recept = (TextView) findViewById(R.id.breakfast_recept);
        breakfast_kkal = (TextView) findViewById(R.id.breakfast_kkal);
        lunch_name = (TextView) findViewById(R.id.lunch_name);
        lunch_recept = (TextView) findViewById(R.id.lunch_recept);
        lunch_kkal = (TextView) findViewById(R.id.lunch_kkal);
        dinner_name = (TextView) findViewById(R.id.dinner_name);
        dinner_recept = (TextView) findViewById(R.id.dinner_recept);
        dinner_kkal = (TextView) findViewById(R.id.dinner_kkal);
        if (arguments != null) {
            MonthlyFoodDB monthlyFoodDB = (MonthlyFoodDB) arguments.getSerializable("monthlyfood");
            assert monthlyFoodDB != null;
            breakfast_name.setText(monthlyFoodDB.getBreakfast_name());
            breakfast_kkal.setText(Integer.toString(monthlyFoodDB.getBreakfast_kkal()) + " ккал");
            lunch_name.setText(monthlyFoodDB.getLunch_name());
            lunch_kkal.setText(Integer.toString(monthlyFoodDB.getLunch_kkal()) + " ккал");
            dinner_name.setText(monthlyFoodDB.getDinner_name());
            dinner_kkal.setText(Integer.toString(monthlyFoodDB.getDinner_kkal()) + " ккал");
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