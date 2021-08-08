package com.example.cp670_project;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AgeDialog extends DialogFragment {

    private static final String TAG = "MyCustomDialog";

    public interface OnInputSelected{
        void sendInput(String input);
    }
    public OnInputSelected mOnInputSelected;

    //widgets
    private NumberPicker dInput;
    private NumberPicker mInput;
    private NumberPicker yInput;
    private TextView mActionCancel, mActionOk ;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.date_picker, container, false);
        mActionOk = view.findViewById(R.id.action_ok);
        mActionCancel = view.findViewById(R.id.action_cancel);
        mInput = view.findViewById(R.id.day_picker);

        mActionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: closing dialog");
                getDialog().dismiss();
            }
        });

        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: capturing input.");

                String input = String.valueOf(mInput.getValue());
                if(!input.equals("")){
//
//                    //Easiest way: just set the value.
//                    MainFragment fragment = (MainFragment) getActivity().getFragmentManager().findFragmentByTag("MainFragment");
//                    fragment.mInputDisplay.setText(input);

                    mOnInputSelected.sendInput(input);
                }


                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mOnInputSelected = (OnInputSelected) getTargetFragment();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage() );
        }
    }
}
