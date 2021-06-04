package com.example.myapplication.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;

public class Field  extends LinearLayout {

    String title;
    String answer;

    public Field(Context context , String title, String answer) {
        super(context);
        this.answer= answer;
        this.title = title;
        init();

    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater)getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate( R.layout.layout_field, this);


        TextView titleAsperField = view.findViewById(R.id.titleAsperField);
        TextView answerAsPerField  = view.findViewById(R.id.answerAsPerField);

        titleAsperField.setText(title);
        answerAsPerField.setText(answer);

    }
}
