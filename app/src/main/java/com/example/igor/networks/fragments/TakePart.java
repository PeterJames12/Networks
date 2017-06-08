package com.example.igor.networks.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.igor.networks.R;

/**
 * @author Igor Hnes on 6/8/17.
 */
public class TakePart extends Fragment {

    private TextView howMuch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.take_part_layout, container, false);
        howMuch = (TextView) view.findViewById(R.id.txtHowMuch);
        Button takePart = (Button) view.findViewById(R.id.btnTakePart);
        takePart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePart(v);
            }
        });
        return view;
    }

    public void takePart(View view) {

        // todo howMuch.getText().toString() in db firebase with key "1"


        Snackbar.make(view, "take part with " + howMuch.getText().toString(), Snackbar.LENGTH_SHORT).show();
    }



}
