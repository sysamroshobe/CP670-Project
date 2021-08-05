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
    private Account account;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.add_meal_fragment, container, false);
        account = (Account) getArguments().getSerializable("Account");

        final EditText meal_name_text_box = root.findViewById(R.id.exerciseName);
        final EditText calories_in_text_box = root.findViewById(R.id.type);

        final Button save_meal_button = root.findViewById(R.id.saveMealButton);
        save_meal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Editable mealName = meal_name_text_box.getText();
                final Editable caloriesIn = calories_in_text_box.getText();

                if (mealName.toString().length() > 0 && caloriesIn.toString().length() > 0) {
                    try {
                        int caloriesInt = Integer.parseInt(caloriesIn.toString());
                        Meal newMeal = new Meal(account.getId(), mealName.toString(), caloriesInt);

                        account.addMeal(newMeal);

                        meal_name_text_box.setText("");
                        calories_in_text_box.setText("");

                        Toast toast = Toast.makeText(getActivity(), "Meal Successfully Saved", Toast.LENGTH_LONG);
                        toast.show();
                    } catch (NumberFormatException e) {
                        Toast toast = Toast.makeText(getActivity(), "Error: Calories must be an integer", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });

        return root;
    }
}
