package com.example.cp670_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyProfileFragment extends Fragment {

    List<SlideModel> imageList;
    ImageSlider carousel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.my_profile_fragment, container, false);
        carousel = root.findViewById(R.id.imageView2);

        imageList = new ArrayList<SlideModel>(); // Create image list

// imageList.add(SlideModel("String Url" or R.drawable)
// imageList.add(SlideModel("String Url" or R.drawable, "title") You can add title

        imageList.add(new SlideModel("https://bit.ly/37Rn50u", "Baby Owl"));
        imageList.add(new SlideModel("https://bit.ly/3fLJf72", "The population of elephants is decreasing in the world."));
        carousel.setImageList(imageList,true);


        return root;
    }
}
