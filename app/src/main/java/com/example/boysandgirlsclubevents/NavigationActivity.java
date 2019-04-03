package com.example.boysandgirlsclubevents;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.boysandgirlsclubevents.Announcements.AnnouncementsFragment;

import java.util.ArrayList;
import java.util.List;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

    private List<Fragment> mFragments = new ArrayList<>(3);
    private Toolbar mToolbar;
    private BottomNavigationView mBottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setUpActionBar();
        setUpLocationDrawer();
        buildFragmentsList();
        setUpBottomNavView();
    }

    private void setUpActionBar()
    {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    private void setUpLocationDrawer()
    {
        //Set up nav drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setUpBottomNavView()
    {
        mBottomNav = findViewById(R.id.bottom_navigation_main);
        mBottomNav.setOnNavigationItemSelectedListener(itemSelectedListener);

        //Show the first Fragment (calendar fragment) to be displayed by default.
        showFragment(0, CalendarFragment.TAG);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener itemSelectedListener =
        new BottomNavigationView.OnNavigationItemSelectedListener()
    {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.nav_calendar:
                    showFragment(0, CalendarFragment.TAG);
                    return true;
                case R.id.nav_announcements:
                    showFragment(1, AnnouncementsFragment.TAG);
                    return true;
                case R.id.nav_members_of_the_month:
                    showFragment(2, MemberMonthFragment.TAG);
                    return true;
            }

            return false;
        }
    };


    @Override
    public void onBackPressed()
    {
        //Close out of the drawer before doing whatever pressing back would do
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_log_in)
        {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        else if (id == R.id.nav_ann_street)
        {


        }

        else if (id == R.id.nav_water_street)
        {

        }

        else if (id == R.id.nav_lemon_street)
        {

        }
        else if (id == R.id.nav_columbia)
        {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void buildFragmentsList()
    {
        Fragment calendarFragment = new CalendarFragment();
        Fragment announcementsFragment = new AnnouncementsFragment();
        Fragment memberOfMonthFragment = new MemberMonthFragment();

        mFragments.add(calendarFragment);
        mFragments.add(announcementsFragment);
        mFragments.add(memberOfMonthFragment);
    }

    private void showFragment(int pos, String tag)
    {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_fragmentHolder_main, mFragments.get(pos), tag)
                .commit();
    }
}
