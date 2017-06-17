package com.example.igor.networks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.igor.networks.R;
import com.example.igor.networks.fragments.CantripFragment;
import com.example.igor.networks.fragments.ProfileFragment;
import com.example.igor.networks.fragments.RecentRatesFragment;
import com.example.igor.networks.fragments.SettingsFragment;
import com.example.igor.networks.fragments.StatisticFragment;
import com.example.igor.networks.fragments.WinnersFragment;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import io.realm.Realm;

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
        setSupportActionBar(toolbar);
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
                .addDrawerItems(new PrimaryDrawerItem().withName(R.string.cantrip).withIdentifier(1))
                .addDrawerItems(new PrimaryDrawerItem().withName(R.string.recent_rates).withIdentifier(2))
                .addDrawerItems(new PrimaryDrawerItem().withName(R.string.winners).withIdentifier(3))
                .addDrawerItems(new PrimaryDrawerItem().withName(R.string.statistic).withIdentifier(4))
                .addDrawerItems(new PrimaryDrawerItem().withName(R.string.settings).withIdentifier(5))
                .withSelectedItem(0)
                .withFireOnInitialOnClick(true)
                .withOnDrawerItemClickListener(this)
                .build();
    }

    private AccountHeader createAccountHeader() {
        IProfile profile = new ProfileDrawerItem()
                .withName("Tetyana Zakharchenko")
                .withEmail("Zakharchenko_t@gmail.com")
                .withIcon(getResources().getDrawable(R.drawable.favicon));

        return new AccountHeaderBuilder()
                .withDividerBelowHeader(true)
                .withActivity(this)
                .addProfiles(profile)
//                .withHeaderBackground(R.drawable.btc)
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
        Fragment fragment = null;
        switch ((int) drawerItem.getIdentifier()) {
            case 0:
                fragment = new ProfileFragment();
                break;
            case 1:
                fragment = new CantripFragment();
                break;
            case 2:
                fragment = new RecentRatesFragment();
                break;
            case 3:
                fragment = new WinnersFragment();
                break;
            case 4:
                fragment = new StatisticFragment();
                break;
            case 5:
                fragment = new SettingsFragment();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void info(MenuItem menuItem) {
        Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
        startActivity(intent);
    }
}
