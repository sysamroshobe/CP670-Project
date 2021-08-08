package com.example.cp670_project;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class MyProfileFragment extends Fragment {
    private DatePickerDialog datePickerDialog;
    List<SlideModel> imageList;
    ImageSlider carousel;
    Button Gender;
    Button Age;
    Button Height;
    Button Weight;
    private static final String TAG = "ProfileFragment";


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.my_profile_fragment, container, false);
        carousel = root.findViewById(R.id.imageView2);
        Age = root.findViewById(R.id.age_btn);
        Height = root.findViewById(R.id.height_buttn);
        Weight = root.findViewById(R.id.weight_btn);
        Gender = root.findViewById(R.id.Gender_buttn);


        imageList = new ArrayList<SlideModel>(); // Create image list


        imageList.add(new SlideModel("https://bit.ly/37Rn50u", "Baby Owl"));
        imageList.add(new SlideModel("https://bit.ly/3fLJf72", "The population of elephants is decreasing in the world."));
        carousel.setImageList(imageList,true);


        Age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: opening dialog");

                AgeDialog dialog = new AgeDialog();
                dialog.setTargetFragment(MyProfileFragment.this, 1);
                dialog.show(getFragmentManager(), "MyCustomDialog");
            }
        });

        Height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: opening dialog");

                AgeDialog dialog = new AgeDialog();
                dialog.setTargetFragment(MyProfileFragment.this, 1);
                dialog.show(getFragmentManager(), "MyCustomDialog");
            }
        });

        Weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: opening dialog");

                AgeDialog dialog = new AgeDialog();
                dialog.setTargetFragment(MyProfileFragment.this, 1);
                dialog.show(getFragmentManager(), "MyCustomDialog");
            }
        });
        Gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: opening dialog");

                AgeDialog dialog = new AgeDialog();
                dialog.setTargetFragment(MyProfileFragment.this, 1);
                dialog.show(getFragmentManager(), "MyCustomDialog");
            }
        });

        return root;
    }







}
