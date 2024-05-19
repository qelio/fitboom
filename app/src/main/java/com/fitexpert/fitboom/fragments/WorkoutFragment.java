package com.fitexpert.fitboom.fragments;

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

import com.fitexpert.fitboom.R;

import org.w3c.dom.Text;

public class WorkoutFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_workout, null);
        final Animation animAlpha = AnimationUtils.loadAnimation(v.getContext(), R.anim.alpha);

        // Кнопки выбора режима тренировки:
        LinearLayout whole_body = (LinearLayout) v.findViewById(R.id.whole_body);
        LinearLayout abs = (LinearLayout) v.findViewById(R.id.abs);
        LinearLayout legs = (LinearLayout) v.findViewById(R.id.legs);
        LinearLayout buttocks = (LinearLayout) v.findViewById(R.id.buttocks);
        // Кнопки выбора дней:
        TextView day_1 = (TextView) v.findViewById(R.id.day_1);
        TextView day_2 = (TextView) v.findViewById(R.id.day_2);
        TextView day_3 = (TextView) v.findViewById(R.id.day_3);
        TextView day_4 = (TextView) v.findViewById(R.id.day_4);
        TextView day_5 = (TextView) v.findViewById(R.id.day_5);
        TextView day_6 = (TextView) v.findViewById(R.id.day_6);
        TextView day_7 = (TextView) v.findViewById(R.id.day_7);
        TextView day_8 = (TextView) v.findViewById(R.id.day_8);
        TextView day_9 = (TextView) v.findViewById(R.id.day_9);
        TextView day_10 = (TextView) v.findViewById(R.id.day_10);
        TextView day_11 = (TextView) v.findViewById(R.id.day_11);
        TextView day_12 = (TextView) v.findViewById(R.id.day_12);
        TextView day_13 = (TextView) v.findViewById(R.id.day_13);
        TextView day_14 = (TextView) v.findViewById(R.id.day_14);
        TextView day_15 = (TextView) v.findViewById(R.id.day_15);
        TextView day_16 = (TextView) v.findViewById(R.id.day_16);
        TextView day_17 = (TextView) v.findViewById(R.id.day_17);
        TextView day_18 = (TextView) v.findViewById(R.id.day_18);
        TextView day_19 = (TextView) v.findViewById(R.id.day_19);
        TextView day_20 = (TextView) v.findViewById(R.id.day_20);
        TextView day_21 = (TextView) v.findViewById(R.id.day_21);
        TextView day_22 = (TextView) v.findViewById(R.id.day_22);
        TextView day_23 = (TextView) v.findViewById(R.id.day_23);
        TextView day_24 = (TextView) v.findViewById(R.id.day_24);
        TextView day_25 = (TextView) v.findViewById(R.id.day_25);
        TextView day_26 = (TextView) v.findViewById(R.id.day_26);
        TextView day_27 = (TextView) v.findViewById(R.id.day_27);
        TextView day_28 = (TextView) v.findViewById(R.id.day_28);
        TextView day_29 = (TextView) v.findViewById(R.id.day_29);
        TextView day_30 = (TextView) v.findViewById(R.id.day_30);
        TextView day_31 = (TextView) v.findViewById(R.id.day_31);
        whole_body.setBackground(v.getContext().getDrawable(R.drawable.type_trenning_active));
        abs.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
        legs.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
        buttocks.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
        whole_body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);
                whole_body.setBackground(v.getContext().getDrawable(R.drawable.type_trenning_active));
                abs.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
                legs.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
                buttocks.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
            }
        });

        abs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);
                whole_body.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
                abs.setBackground(v.getContext().getDrawable(R.drawable.type_trenning_active));
                legs.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
                buttocks.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
            }
        });

        legs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);
                whole_body.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
                abs.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
                legs.setBackground(v.getContext().getDrawable(R.drawable.type_trenning_active));
                buttocks.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
            }
        });

        buttocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);
                whole_body.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
                abs.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
                legs.setBackground(v.getContext().getDrawable(R.drawable.type_trenning));
                buttocks.setBackground(v.getContext().getDrawable(R.drawable.type_trenning_active));
            }
        });
        return v;
    }
}