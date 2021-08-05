package com.example.cp670_project;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.text.method.PasswordTransformationMethod;

import androidx.fragment.app.Fragment;

public class AddMealFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.add_meal_fragment, container, false);

        final EditText meal_name = root.findViewById(R.id.mealName);
        final EditText calories_in = root.findViewById(R.id.caloriesIn);
        final Button save_meal_button = root.findViewById(R.id.saveMealButton);
        save_meal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(meal_name.getText());
                System.out.println(calories_in.getText());
//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putString("DefaultEmail", email_field.getText().toString());
//                editor.commit();
//
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
            }
        });

        return root;
    }
}
