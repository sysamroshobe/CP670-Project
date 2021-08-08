package com.example.cp670_project;

import android.view.ViewGroup;

public abstract class MainMenuItem<T extends MenuAdapter.ViewHolder> {

    protected boolean isChecked;

    public abstract T createViewHolder(ViewGroup parent);

    public abstract void bindViewHolder(T holder);

    public MainMenuItem<T> setChecked(boolean isChecked) {
        this.isChecked = isChecked;
        return this;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public boolean Selectable() {
        return true;
    }

}