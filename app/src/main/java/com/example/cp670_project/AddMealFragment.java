package com.example.cp670_project;

import android.os.Bundle;

import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class AddMealFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.add_meal_fragment, container, false);
        Account account = (Account) getArguments().getSerializable("Account");

        final EditText meal_name_textbox = root.findViewById(R.id.mealName);
        final EditText calories_in_textbox = root.findViewById(R.id.caloriesIn);

        final Editable mealName = meal_name_textbox.getText();
        final Editable caloriesIn = calories_in_textbox.getText();

        final Button save_meal_button = root.findViewById(R.id.saveMealButton);
        save_meal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int caloriesInt = Integer.parseInt(caloriesIn.toString());
                    // is an integer!
                } catch (NumberFormatException e) {
                    System.out.println("Hello :(");
                }
//                Toast toast = Toast.makeText(getApplicationContext(),text, Toast.LENGTH_LONG);
//                toast.show();
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
