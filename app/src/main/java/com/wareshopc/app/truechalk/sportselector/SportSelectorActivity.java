package com.wareshopc.app.truechalk.sportselector;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.wareshopc.app.truechalk.R;

public class SportSelectorActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView mSoccerImageView;
    private ImageView mFootballImageView;
    private ImageView mBasketballImageView;
    private ImageView mBaseballImageView;
    private ImageView mVolleyballImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_selector);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);      //This is not necessary since I already created an actionbar in XML.

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        RelativeLayout rLayout = (RelativeLayout) findViewById(R.id.sports_selector_rlayout);
        rLayout.setBackgroundColor(getResources().getColor(R.color.amber_400));


        mSoccerImageView = (ImageView) findViewById(R.id.soccerImageView);
        mSoccerImageView.setImageResource(R.drawable.soccer);
        mSoccerImageView.setClickable(true);
        mSoccerImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("soccer intent placeholder");
            }
        });

        mFootballImageView = (ImageView) findViewById(R.id.footballImageView);
        mFootballImageView.setImageResource(R.drawable.football);
        mFootballImageView.setClickable(true);
        mFootballImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("placeholder");
            }
        });

        mBasketballImageView = (ImageView) findViewById(R.id.basketballImageView);
        mBasketballImageView.setImageResource(R.drawable.basketball);
        mBasketballImageView.setClickable(true);
        mBasketballImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("placeholder");
            }
        });

        mBaseballImageView = (ImageView) findViewById(R.id.baseballImageView);
        mBaseballImageView.setImageResource(R.drawable.baseball);
        mBaseballImageView.setClickable(true);
        mBaseballImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("placeholder");
            }
        });

        mVolleyballImageView = (ImageView) findViewById(R.id.volleyballImageView);
        mVolleyballImageView.setImageResource(R.drawable.volleyball);
        mVolleyballImageView.setClickable(true);
        mVolleyballImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("placeholder");
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sport_selector, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
