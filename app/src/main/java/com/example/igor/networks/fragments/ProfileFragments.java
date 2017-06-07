package com.example.igor.networks.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.igor.networks.R;
import com.example.igor.networks.model.Event;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;

import fr.tkeunebr.gravatar.Gravatar;
import io.realm.Realm;
import io.realm.RealmResults;


/**
 * @author Igor Hnes on 06.06.17.
 */

public class ProfileFragments extends Fragment {

    public static final String TAG = "ProfileFragments";

    public ProfileFragments() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_layout, null);

        loadUserPhoto(view);

        return view;
    }

    private void loadUserPhoto(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.userPhoto);

        String gravatarUrl = Gravatar.init().with("joyukr@ukr.net")
                .force404()
                .rating(Gravatar.Rating.g)
                .size(122)
                .build();

        Picasso.with(view.getContext())
                .load(gravatarUrl)
                .into((ImageView) imageView.findViewById(R.id.userPhoto));

        ((TextView) view.findViewById(R.id.txtUserName)).setText("Igor Hnes");
    }
}
