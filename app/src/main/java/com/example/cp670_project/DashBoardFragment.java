package com.example.cp670_project;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

public class DashBoardFragment extends Fragment implements UpdateRecyclerView {
    private static final String TAG = "DashBoardFragment";
    private Context mContext;
    private ExercisesDataSource datasource;
    private RecyclerView recyclerView, recyclerView2;
    private StaticRvAdapter staticRvAdapter;
    BarChart mChart1;
    private PieChart pieChart;

    ArrayList<DynamicRVModel> items = new ArrayList();
    DynamicRVAdapter dynamicRVAdapter;
    int pos;
    ViewGroup root;

    private Account account;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        account = (Account) getArguments().getSerializable("Account");

        root = (ViewGroup) inflater.inflate(R.layout.dashboard_fragment, container, false);

        super.onViewCreated(root, savedInstanceState);

        String mealsText = getResources().getString(R.string.mealsText);
        String exercisesText = getResources().getString(R.string.exercisesText);
        final ArrayList<StaticRvModel> item = new ArrayList<>();
        item.add(new StaticRvModel(R.drawable.burger,mealsText));
        item.add(new StaticRvModel(R.drawable.pizza,exercisesText));
//        item.add(new StaticRvModel(R.drawable.fries,"SomethingElse?"));


        recyclerView = root.findViewById(R.id.rv_1);
        staticRvAdapter = new StaticRvAdapter(item, this, this, this.account);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(staticRvAdapter);

        List<Exercise> testList = datasource.getAllExercises();

        if (testList.size() == 0) {
            datasource.createExercise("Dumbbell Bench Press", "", "Dumbbell",
                    50, 0, 0, 0, 8, 5, 0);
            datasource.createExercise("Incline Dumbbell Bench Press", "", "Dumbbell",
                    50, 0, 0, 0, 8, 4, 0);
            datasource.createExercise("Dumbbell Floor Press", "", "Dumbbell",
                    50, 0, 0, 0, 8, 3, 0);
            datasource.createExercise("Standing Dumbbell Press", "", "Dumbbell",
                    50, 0, 0, 0, 8, 4, 0);
            datasource.createExercise("Dumbbell Lateral Raise", "", "Dumbbell",
                    50, 0, 0, 0, 8, 3, 0);
            datasource.createExercise("Dumbbell Tricep Kickback", "", "Dumbbell",
                    50, 0, 0, 0, 8, 3, 0);
        }
        List<Exercise> exerciseList = datasource.getAllExercises();

        for (int i = 0; i < testList.size(); i++) {
             Exercise exercise = exerciseList.get(i);
             // TODO: add calories and flag
             Boolean caloriesInFlag = Boolean.FALSE;
             items.add(new DynamicRVModel(exercise.getName(), i, 0, 0 , caloriesInFlag));
             Log.d(TAG, "Added exercise " + exercise.getName());
         }
        testList.clear();

        recyclerView2 = root.findViewById(R.id.rv_2);
        dynamicRVAdapter = new DynamicRVAdapter(items);
        recyclerView2.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView2.setAdapter(dynamicRVAdapter);

        pieChart = root.findViewById(R.id.activity_main_piechart);
        setupPieChart();
        loadPieChartData();


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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void callback(int position, ArrayList<DynamicRVModel> items) {
        dynamicRVAdapter = new DynamicRVAdapter(items);
        dynamicRVAdapter.notifyDataSetChanged();
        recyclerView2.setAdapter(dynamicRVAdapter);
    }

    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Spending by Category");
        pieChart.setCenterTextSize(15);
        pieChart.setDrawRoundedSlices(true);
        pieChart.setHoleRadius(82);
        pieChart.setHoleColor(1);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }

    private void loadPieChartData() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(0.2f, "Food & Dining"));
        entries.add(new PieEntry(0.15f, "Medical"));
        entries.add(new PieEntry(0.10f, "Entertainment"));
        entries.add(new PieEntry(0.25f, "Electricity and Gas"));
        entries.add(new PieEntry(0.3f, "Housing"));

        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Expense Category");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }

}
