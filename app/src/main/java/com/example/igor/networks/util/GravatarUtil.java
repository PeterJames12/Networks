package com.example.igor.networks.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.igor.networks.R;
import com.squareup.picasso.Picasso;
import fr.tkeunebr.gravatar.Gravatar;

/**
 * @author Igor Hnes on 6/7/17.
 */

public class GravatarUtil extends BaseAdapter {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;

    public GravatarUtil(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.profile_layout, null);
        }

        String gravatarUrl = Gravatar.init().with("joyukr@ukr.net").force404().size(50).build();
        Picasso.with(mContext)
                .load(gravatarUrl)
                .placeholder(R.drawable.material_drawer_circle_mask)
                .error(R.drawable.material_drawer_badge)
                .into((ImageView) convertView.findViewById(R.id.userPhoto));

        ((TextView) convertView.findViewById(R.id.txtUserId)).setText("Igor Hnes");

        return convertView;
    }
}
