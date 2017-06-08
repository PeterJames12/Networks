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
import com.example.igor.networks.model.Player;
import com.squareup.picasso.Picasso;

import fr.tkeunebr.gravatar.Gravatar;
import io.realm.Realm;

/**
 * @author Igor Hnes on 06.06.17.
 */

public class ProfileFragments extends Fragment {

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

        Realm realm = Realm.getDefaultInstance();
        Player user = realm.where(Player.class).equalTo("id", 1).findFirst();

        String gravatarUrl = Gravatar.init().with(user.getEmail())
                .force404()
                .rating(Gravatar.Rating.g)
                .size(122)
                .build();

        Picasso.with(view.getContext())
                .load(gravatarUrl)
                .into((ImageView) imageView.findViewById(R.id.userPhoto));

        ((TextView) view.findViewById(R.id.txtUserId)).setText("Your id: " + user.getId() + "");

        ((TextView) view.findViewById(R.id.txtUserMoney)).setText("Total money : " + user.getMoney() + " $");
    }
}
