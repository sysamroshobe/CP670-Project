package com.example.cp670_project;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class ExtraSpace extends MainMenuItem<ExtraSpace.ViewHolder> {

    private int space;

    public ExtraSpace(int space) {
        this.space = space;
    }

    @Override
    public ViewHolder createViewHolder(ViewGroup parent) {
        Context c = parent.getContext();
        View view = new View(c);
        int height = (int) (c.getResources().getDisplayMetrics().density * space);
        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                height));
        return new ViewHolder(view);
    }

    @Override
    public void bindViewHolder(ViewHolder holder) {

    }

    @Override
    public boolean Selectable() {
        return false;
    }

    static class ViewHolder extends MenuAdapter.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
