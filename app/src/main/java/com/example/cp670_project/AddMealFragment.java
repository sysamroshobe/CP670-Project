package com.example.cp670_project;

import android.os.Bundle;
import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.util.Log;

import androidx.fragment.app.Fragment;

public class AddMealFragment extends Fragment {
    private Account account;
    private final String TAG = "AddMealFragment";
    private ExercisesDataSource datasource;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.add_meal_fragment, container, false);
        account = (Account) getArguments().getSerializable("Account");

        final EditText meal_name_text_box = root.findViewById(R.id.mealName);
        final EditText calories_in_text_box = root.findViewById(R.id.caloriesIn);

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

                        // save to database
                        datasource.createMeal(newMeal.getName(), account.getId(), newMeal.getCaloriesIn());

                        meal_name_text_box.setText("");
                        calories_in_text_box.setText("");

                        String mealSuccessText = getResources().getString(R.string.mealSuccessText);
                        Toast toast = Toast.makeText(getActivity(), mealSuccessText, Toast.LENGTH_LONG);
                        toast.show();
                    } catch (NumberFormatException e) {
                        Log.e(TAG, e.toString());
                        String mealErrorText = getResources().getString(R.string.mealErrorText);
                        Toast toast = Toast.makeText(getActivity(), mealErrorText, Toast.LENGTH_LONG);
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
