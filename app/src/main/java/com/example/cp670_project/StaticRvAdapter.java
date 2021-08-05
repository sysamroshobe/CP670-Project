package com.example.cp670_project;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StaticRvAdapter extends RecyclerView.Adapter<StaticRvAdapter.StaticRVViewHolder>{

    private ArrayList<StaticRvModel> items;
    int row_index = -1;
    UpdateRecyclerView updateRecyclerView;
    DashBoardFragment activity;
    boolean check = true;
    boolean select = true;
    private Account account;

    public StaticRvAdapter(ArrayList<StaticRvModel> items, DashBoardFragment activity, UpdateRecyclerView updateRecyclerView, Account account) {
        this.items = items;
        this.activity = activity;
        this.updateRecyclerView = updateRecyclerView;
        this.account = account;
    }

    @NonNull
    @Override
    public StaticRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_item,parent,false);
        StaticRVViewHolder staticRVViewHolder = new StaticRVViewHolder(view);
        return staticRVViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull StaticRVViewHolder holder, int position) {
        StaticRvModel currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getText());

        if (check){

            ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
//            items.add(new DynamicRVModel("burger 1", R.drawable.burger_png,0));
//            items.add(new DynamicRVModel("burger 2", R.drawable.burger_png,0));
//            items.add(new DynamicRVModel("burger 3", R.drawable.burger_png,0));
//            items.add(new DynamicRVModel("burger 4", R.drawable.burger_png,0));
//            items.add(new DynamicRVModel("burger 5", R.drawable.burger_png,0));
//            items.add(new DynamicRVModel("burger 6", R.drawable.burger_png,0));
//            items.add(new DynamicRVModel("burger 7", R.drawable.burger_png,0));
//            items.add(new DynamicRVModel("burger 8", R.drawable.burger_png,0));
//            items.add(new DynamicRVModel("burger 9", R.drawable.burger_png,0));

            updateRecyclerView.callback(position, items);

            check = false;
        }

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();

                if (position == 0) {
                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    final Meal meals[] = account.getMeals();
                        for (int i = 0; i < meals.length; i++) {
                        items.add(new DynamicRVModel(meals[i].getName(), R.drawable.burger_png,0, meals[i].getCaloriesIn(), true));
                    }

                    updateRecyclerView.callback(position, items);
                }

                else if (position == 1) {
                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    final Exercise exercises[] = account.getExercises();
                    for (int i = 0; i < exercises.length; i++) {
                        items.add(new DynamicRVModel(exercises[i].getName(), R.drawable.burger_png,0, exercises[i].getCaloriesOut(), true));
                    }

                    updateRecyclerView.callback(position, items);
                }

                else if (position == 2){
                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
//                    items.add(new DynamicRVModel("fries 1", R.drawable.fries_png,2));
//                    items.add(new DynamicRVModel("fries 2", R.drawable.fries_png,2));
//                    items.add(new DynamicRVModel("fries 3", R.drawable.fries_png,2));
//                    items.add(new DynamicRVModel("fries 4", R.drawable.fries_png,2));
//                    items.add(new DynamicRVModel("fries 5", R.drawable.fries_png,2));
//                    items.add(new DynamicRVModel("fries 6", R.drawable.fries_png,2));
//                    items.add(new DynamicRVModel("fries 7", R.drawable.fries_png,2));
//                    items.add(new DynamicRVModel("fries 8", R.drawable.fries_png,2));
//                    items.add(new DynamicRVModel("fries 9", R.drawable.fries_png,2));

                    updateRecyclerView.callback(position, items);
                }
            }
        });

        if (select){
            if (position==0)
                holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected_bg);
            select=false;
        }
        else {
            if (row_index == position){
                holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected_bg);
            }
            else {
                holder.linearLayout.setBackgroundResource(R.drawable.static_rv_bg);
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class StaticRVViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;

        public StaticRVViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.text);
            linearLayout = itemView.findViewById(R.id.linearLayour);
        }
    }
}
