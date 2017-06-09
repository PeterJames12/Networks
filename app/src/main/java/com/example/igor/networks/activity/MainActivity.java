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
import com.example.igor.networks.fragments.MyEventFragment;
import com.example.igor.networks.fragments.ProfileFragments;
import com.example.igor.networks.fragments.ResentEventFragments;
import com.example.igor.networks.fragments.TakePart;
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
                .addDrawerItems(new PrimaryDrawerItem().withName(R.string.something).withIdentifier(1))
                .addDrawerItems(new PrimaryDrawerItem().withName(R.string.take_part).withIdentifier(2))
                .addDrawerItems(new PrimaryDrawerItem().withName(R.string.my_events).withIdentifier(3))
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
        Fragment fragment = null;
        switch ((int) drawerItem.getIdentifier()) {
            case 0:
                fragment = new ProfileFragments();
                break;
            case 1:
                fragment = new ResentEventFragments();
                break;
            case 2:
                fragment = new TakePart();
                break;
            case 3:
                fragment = new MyEventFragment();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_info:
                info();
                return true;
            case R.id.menu_settings:
                settings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void info() {
        Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
        startActivity(intent);
    }

    public void settings() {
        Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
        startActivity(intent);
    }
}
