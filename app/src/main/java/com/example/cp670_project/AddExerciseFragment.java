package com.example.cp670_project;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class AddExerciseFragment extends Fragment {
    private final String TAG = "AddExerciseFragment";
    private ExercisesDataSource datasource;
    private Context mContext;

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
        final EditText repetitons_text_box = root.findViewById(R.id.repetitions);
        final EditText sets_text_box = root.findViewById(R.id.sets);
        final EditText image_text_box = root.findViewById(R.id.image);

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
                final Editable repetitionsTextbox = repetitons_text_box.getText();
                final Editable setsTextbox = sets_text_box.getText();
                final Editable image = image_text_box.getText();

                if (exerciseName.toString().length() > 0 && type.toString().length() > 0 && weight.toString().length() > 0
                        && distance.toString().length() > 0 && lengthOfTime.toString().length() > 0 && caloriesOut.toString().length() > 0) {
                    try {
                        double weightDouble = 0;
                        double distanceDouble = 0;
                        double lengthOfTimeDouble = 0;
                        int caloriesInt = 0;
                        int repetitions = 0;
                        int sets = 0;
                        int imageResult = 0;

                        try {
                            weightDouble = Double.parseDouble(weight.toString());
                            distanceDouble = Double.parseDouble(distance.toString());
                            lengthOfTimeDouble = Double.parseDouble(lengthOfTime.toString());
                            caloriesInt = Integer.parseInt(caloriesOut.toString());
                            repetitions = Integer.parseInt(repetitionsTextbox.toString());
                            sets = Integer.parseInt(setsTextbox.toString());
                            imageResult = Integer.parseInt(image.toString());
                        }
                        catch(NumberFormatException ex){
                            Log.e(TAG, ex.toString());
                        }

                        Exercise newExercise = new Exercise(account.getId(), exerciseName.toString(), type.toString(), weightDouble, distanceDouble, lengthOfTimeDouble, caloriesInt, repetitions, sets, imageResult);
                        account.addExercise(newExercise);

                        // save to database
                        datasource.createExercise(exerciseName.toString(), account.getId(), type.toString(), weightDouble, distanceDouble, lengthOfTimeDouble, caloriesInt, repetitions, sets, imageResult);

                        exercise_name_text_box.setText("");
                        type_text_box.setText("");
                        weight_text_box.setText("");
                        distance_text_box.setText("");
                        length_of_time_text_box.setText("");
                        calories_out_text_box.setText("");
                        repetitons_text_box.setText("");
                        sets_text_box.setText("");
                        image_text_box.setText("");

                        String exerciseSuccessText = getResources().getString(R.string.exerciseSuccessText);
                        Toast toast = Toast.makeText(getActivity(), exerciseSuccessText, Toast.LENGTH_LONG);
                        toast.show();
                    } catch (NumberFormatException e) {
                        Log.e(TAG, e.toString());
                        String exerciseErrorText = getResources().getString(R.string.exerciseErrorText);
                        Toast toast = Toast.makeText(getActivity(), exerciseErrorText, Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });

        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

        // 1. call to create database
        datasource = new ExercisesDataSource(mContext);

        // 2. open Database for writing
        datasource.open();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        datasource.close();
        mContext = null;
    }
}
