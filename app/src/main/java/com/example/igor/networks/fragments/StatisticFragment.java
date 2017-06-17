package com.example.igor.networks.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.igor.networks.R;

/**
 * @author Igor Hnes on 6/14/17.
 */
public class StatisticFragment extends Fragment {

    public StatisticFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.statistic_layout, null);

        return view;
    }
}
