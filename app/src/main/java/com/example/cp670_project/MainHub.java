package com.example.cp670_project;

import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;


public class MainHub extends AppCompatActivity implements MenuAdapter.OnItemSelectedListener {
    BarChart mChart1;
    private PieChart pieChart;

    private static final int POS_CLOSE = 0;
    private static final int POS_DASHBOARD = 1;
    private static final int POS_MY_PROFILE = 2;
    private static final int POS_ADD_MEAL = 3;
    private static final int POS_ADD_EXERCISE = 4;
    private static final int POS_ABOUT_US = 5;
    private static final int POS_LOGOUT = 6;
    private String[] menuTitles;
    private Drawable[] smenuIcons;
    private Account account; // THIS IS GLOBAL

    private SlidingRootNav NavDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hub);
        account = (Account) getIntent().getExtras().getSerializable("Account");

        ConstraintLayout constraintLayout = findViewById(R.id.backgroundMain);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavDrawer = new SlidingRootNavBuilder(this)
                .withDragDistance(180)
                .withRootViewScale(0.75f)
                .withRootViewElevation(25)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.drawer_menu)
                .inject();

        smenuIcons = createIcons();
        menuTitles = createTitles();

        MenuAdapter menuAdapter = new MenuAdapter(Arrays.asList(
                MenuItemCreate(POS_CLOSE),
                MenuItemCreate(POS_DASHBOARD).setChecked(true),
                MenuItemCreate(POS_MY_PROFILE),
                MenuItemCreate(POS_ADD_MEAL),
                MenuItemCreate(POS_ADD_EXERCISE),
                MenuItemCreate(POS_ABOUT_US),
                new ExtraSpace(60),
                MenuItemCreate(POS_LOGOUT)
        ));
        menuAdapter.setListener(this);

        RecyclerView list = findViewById(R.id.drawer_list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(menuAdapter);

        menuAdapter.setSelected(POS_DASHBOARD);
    }

    private MainMenuItem MenuItemCreate(int position) {
        return new SingleItem(smenuIcons[position], menuTitles[position])
                .withIconTint(color(android.R.color.holo_blue_light))
                .withTextTint(color(R.color.black))
                .withSelectedIconTint(color(android.R.color.holo_blue_dark))
                .withSelectedTextTint(color(android.R.color.holo_green_light));
    }

    @ColorInt
    private int color(@ColorRes int res){
        return ContextCompat.getColor(this, res);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private String[] createTitles() {
        return getResources().getStringArray(R.array.id_activityScreenTitles);
    }

    private Drawable[] createIcons() {
        TypedArray iconArray = getResources().obtainTypedArray(R.array.id_activityScreenIcons);
        Drawable[] icons = new Drawable[iconArray.length()];
        for (int i = 0; i < iconArray.length(); i++){
            int id = iconArray.getResourceId(i,0);
            if (id!=0){
                icons[i] = ContextCompat.getDrawable(this,id);
            }
        }
        iconArray.recycle();
        return icons;
    }

    @Override
    public void MenuItemSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Account", account);

        if (position == POS_DASHBOARD){
            DashBoardFragment dashBoardFragment = new DashBoardFragment();
            dashBoardFragment.setArguments(bundle);
            transaction.replace(R.id.container, dashBoardFragment);
        }

        else if (position == POS_MY_PROFILE){
            MyProfileFragment myProfileFragment = new MyProfileFragment();
            transaction.replace(R.id.container, myProfileFragment);
        }

        else if (position == POS_ADD_MEAL) {
            AddMealFragment addMealFragment = new AddMealFragment();
            addMealFragment.setArguments(bundle);
            transaction.replace(R.id.container, addMealFragment);
        }

        else if (position == POS_ADD_EXERCISE) {
            AddExerciseFragment addExerciseFragment = new AddExerciseFragment();
            addExerciseFragment.setArguments(bundle);
            transaction.replace(R.id.container, addExerciseFragment);
        }

        else if (position == POS_ABOUT_US){
            AboutUsFragment aboutUsFragment = new AboutUsFragment();
            transaction.replace(R.id.container, aboutUsFragment);
        }

        else if (position == POS_LOGOUT){
            finish();
        }

        NavDrawer.closeMenu();
        transaction.addToBackStack(null);
        transaction.commit();
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}