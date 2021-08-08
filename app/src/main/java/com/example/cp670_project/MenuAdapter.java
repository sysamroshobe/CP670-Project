package com.example.cp670_project;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private List<MainMenuItem> items;
    private Map<Class<? extends MainMenuItem>, Integer> viewTypes;
    private SparseArray<MainMenuItem> holderFactories;

    private OnItemSelectedListener listener;

    public MenuAdapter(List<MainMenuItem> items) {
        this.items = items;
        this.viewTypes = new HashMap<>();
        this.holderFactories = new SparseArray<>();

        processViewTypes();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder = holderFactories.get(viewType).createViewHolder(parent);
        holder.adapter = this;
        return holder;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        items.get(position).bindViewHolder(holder);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return viewTypes.get(items.get(position).getClass());
    }

    private void processViewTypes() {
        int type = 0;
        for (MainMenuItem item : items) {
            if (!viewTypes.containsKey(item.getClass())) {
                viewTypes.put(item.getClass(), type);
                holderFactories.put(type, item);
                type++;
            }
        }
    }

    public void setSelected(int position) {
        MainMenuItem newChecked = items.get(position);
        if (!newChecked.Selectable()) {
            return;
        }

        for (int i = 0; i < items.size(); i++) {
            MainMenuItem item = items.get(i);
            if (item.isChecked()) {
                item.setChecked(false);
                notifyItemChanged(i);
                break;
            }
        }

        newChecked.setChecked(true);
        notifyItemChanged(position);

        if (listener != null) {
            listener.MenuItemSelected(position);
        }
    }

    public void setListener(OnItemSelectedListener listener) {
        this.listener = listener;
    }

    public interface OnItemSelectedListener {
        void MenuItemSelected(int position);
    }

    static abstract class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private MenuAdapter adapter;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            adapter.setSelected(getAdapterPosition());
        }
    }
}
