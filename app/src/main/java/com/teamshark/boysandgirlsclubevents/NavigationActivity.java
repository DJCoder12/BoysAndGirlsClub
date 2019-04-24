package com.teamshark.boysandgirlsclubevents;

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
import android.widget.Toast;

import com.teamshark.boysandgirlsclubevents.MemberOfMonth.AddMemberActivity;
import com.teamshark.boysandgirlsclubevents.R;
import com.teamshark.boysandgirlsclubevents.Calendar.CalendarFragment;
import com.teamshark.boysandgirlsclubevents.Calendar.CalendarSettings;
import com.teamshark.boysandgirlsclubevents.Calendar.ClubCalendar;
import com.teamshark.boysandgirlsclubevents.Calendar.Event;
import com.teamshark.boysandgirlsclubevents.MemberOfMonth.MemberMonthFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.teamshark.boysandgirlsclubevents.Announcements.AnnouncementsFragment;

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

        AndroidThreeTen.init(this);
        setUpActionBar();
        setUpLocationDrawer();
        buildFragmentsList();
        setUpBottomNavView();

        // Refresh calendar on open.
        ClubCalendar.refreshData(this);
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
                case R.id.nav_join_club:
                    showFragment(3, JoinClubFragment.TAG);
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
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        super.onPrepareOptionsMenu(menu);
        MenuItem logInMenu = menu.findItem(R.id.action_log_in);
        if (FirebaseAuth.getInstance().getCurrentUser() == null)
        {
            logInMenu.setTitle(getResources().getString(R.string.button_login));
        }
        else
        {
            logInMenu.setTitle(getResources().getString(R.string.button_logout));
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here
        int id = item.getItemId();

        Intent i;
        switch (id)
        {
            case R.id.action_refresh:
                ClubCalendar.refreshData(this);
                Toast.makeText(this, "Refreshing event data...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_log_in:
                i = new Intent(this, LoginActivity.class);
                startActivity(i);
                break;
            case R.id.action_new_event:
                if (FirebaseAuth.getInstance().getCurrentUser() != null)
                {
                    i = new Intent(this, AddEventsActivity.class);
                    startActivity(i);
                }
            case R.id.action_add_member:
                if (FirebaseAuth.getInstance().getCurrentUser() != null)
                {
                    i = new Intent(this, AddMemberActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(this, "You must be logged in to create an event or add a new MotM.", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        CalendarFragment calendarFragment = (CalendarFragment) mFragments.get(0);

        if (id == R.id.nav_hill_clubhouse)
        {
            CalendarSettings.switchLocationFilter(Event.ClubLocation.Hill);
        }
        else if (id == R.id.nav_jackWalker_clubhouse)
        {
            CalendarSettings.switchLocationFilter(Event.ClubLocation.JackWalker);
        }
        else if (id == R.id.nav_columbia_clubhouse)
        {
            CalendarSettings.switchLocationFilter(Event.ClubLocation.Columbia);
        }
        else if (id == R.id.nav_southeast_clubhouse)
        {
            CalendarSettings.switchLocationFilter(Event.ClubLocation.Southeast);
        }
        else if (id == R.id.nav_view_daily)
        {
            CalendarSettings.switchDisplayType(CalendarSettings.CalendarType.Daily);
        }
        else if (id == R.id.nav_view_weekly)
        {
            CalendarSettings.switchDisplayType(CalendarSettings.CalendarType.Weekly);
        }
        else if (id == R.id.nav_view_monthly)
        {
            CalendarSettings.switchDisplayType(CalendarSettings.CalendarType.Monthly);
        }

        showFragment(0, CalendarFragment.TAG);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void buildFragmentsList()
    {
        Fragment calendarFragment = new CalendarFragment();
        Fragment announcementsFragment = new AnnouncementsFragment();
        Fragment memberOfMonthFragment = new MemberMonthFragment();
        Fragment joinClubFragment = new JoinClubFragment();

        mFragments.add(calendarFragment);
        mFragments.add(announcementsFragment);
        mFragments.add(memberOfMonthFragment);
        mFragments.add(joinClubFragment);
    }

    private Fragment getFragment(int index)
    {
        //CalendarFragment must be reloaded every time to avoid blank view pager
        if (index == 0)
        {
            return new CalendarFragment();
        }

        else
        {
            return mFragments.get(index);
        }
    }

    public void showFragment(int pos, String tag)
    {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_fragmentHolder_main, getFragment(pos), tag)
                .commitAllowingStateLoss();
    }
}
