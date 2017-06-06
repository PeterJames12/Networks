package com.example.igor.networks.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.igor.networks.R;

/**
 * @author Igor Hnes on 06.06.17.
 */

public class SomethingFragments extends Fragment {

    public static final String TAG = "SomethingFragments";

    public SomethingFragments() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.something, null);
    }
}
