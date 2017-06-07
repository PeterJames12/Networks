package com.example.igor.networks;

import android.app.Activity;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.igor.networks.fragments.ProfileFragments;
import com.example.igor.networks.fragments.SomethingFragments;
import com.example.igor.networks.model.Event;
import com.example.igor.networks.util.GravatarUtil;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.icons.MaterialDrawerFont;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.squareup.picasso.Picasso;

import org.joda.time.LocalDate;

import java.util.LinkedList;
import java.util.List;

import fr.tkeunebr.gravatar.Gravatar;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * @author Igor Hnes on 06.06.17.
 */
public class MainActivity extends AppCompatActivity implements Drawer.OnDrawerItemClickListener {

    private Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initializeNavigationDrawer(toolbar);
        Realm.init(this);
    }

    private void initializeNavigationDrawer(Toolbar toolbar) {
        AccountHeader headerResult = createAccountHeader();
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withRootView(R.id.drawer_layout)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withAccountHeader(headerResult)
                .addDrawerItems(new PrimaryDrawerItem().withName(R.string.profile).withIdentifier(0))
                .addDrawerItems(new PrimaryDrawerItem().withName(R.string.something).withIdentifier(1))
                .withSelectedItem(0)
                .withFireOnInitialOnClick(true)
                .withOnDrawerItemClickListener(this)
                .build();
    }

    private AccountHeader createAccountHeader() {
        IProfile profile = new ProfileDrawerItem()
                .withName("Tetyana Zakharchenko")
                .withEmail("Zakharchenko_t@gmail.com")
                .withIcon(getResources().getDrawable(R.drawable.material_drawer_badge));
        
        return new AccountHeaderBuilder()
                .withDividerBelowHeader(true)
                .withActivity(this)
                .addProfiles(profile)
//                .withHeaderBackground(R.drawable.profile)
                .build();
    }


    @Override
    public void onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
        Fragment fragment= null;
        switch ((int) drawerItem.getIdentifier()) {
            case 0:
                fragment = new ProfileFragments();
                break;
            case 1:
                fragment = new SomethingFragments();
                break;
            default:
                break;
        }

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_r, fragment)
                    .commitAllowingStateLoss();
        }
        return false;
    }
}
