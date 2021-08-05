package com.example.cp670_project;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class AddExerciseFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.add_exercise_fragment, container, false);
        Account account = (Account) getArguments().getSerializable("Account");

        final EditText exercise_name_text_box = root.findViewById(R.id.exerciseName);
        final EditText type_text_box = root.findViewById(R.id.type);
        final EditText weight_text_box = root.findViewById(R.id.weight);
        final EditText distance_text_box = root.findViewById(R.id.distance);
        final EditText length_of_time_text_box = root.findViewById(R.id.lengthOfTime);
        final EditText calories_out_text_box = root.findViewById(R.id.caloriesOut);

        final Button save_meal_button = root.findViewById(R.id.saveMealButton);
        save_meal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Editable exerciseName = exercise_name_text_box.getText();
                final Editable type = type_text_box.getText();
                final Editable weight = weight_text_box.getText();
                final Editable distance = distance_text_box.getText();
                final Editable lengthOfTime = length_of_time_text_box.getText();
                final Editable caloriesOut = calories_out_text_box.getText();

                if (exerciseName.toString().length() > 0 && type.toString().length() > 0 && weight.toString().length() > 0
                        && distance.toString().length() > 0 && lengthOfTime.toString().length() > 0 && caloriesOut.toString().length() > 0) {
                    try {
                        double weightDouble = Double.parseDouble(weight.toString());
                        double distanceDouble = Double.parseDouble(distance.toString());
                        double lengthOfTimeDouble = Double.parseDouble(lengthOfTime.toString());
                        int caloriesInt = Integer.parseInt(caloriesOut.toString());

                        Exercise newExercise = new Exercise(account.getId(), exerciseName.toString(), type.toString(), weightDouble, distanceDouble, lengthOfTimeDouble, caloriesInt);
                        account.addExercise(newExercise);

                        exercise_name_text_box.setText("");
                        type_text_box.setText("");
                        weight_text_box.setText("");
                        distance_text_box.setText("");
                        length_of_time_text_box.setText("");
                        calories_out_text_box.setText("");

                        Toast toast = Toast.makeText(getActivity(), "Exercise Successfully Saved", Toast.LENGTH_LONG);
                        toast.show();
                    } catch (NumberFormatException e) {
                        Toast toast = Toast.makeText(getActivity(), "Error: Weight, Distance, Length of Time must be doubles. Calories Out must be Integer.", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });

        return root;
    }
}
