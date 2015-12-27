package com.wareshopc.app.truechalk.sportselector;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.wareshopc.app.truechalk.R;
import com.wareshopc.app.truechalk.sportselector.basketball.BasketballChalkListActivity;

public class SportSelectorActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

//    private ImageView mSoccerImageView;
    private ImageButton mSoccerImageButton;
    private ImageButton mFootballImageButton;
    private ImageButton mBasketballImageButton;
    private ImageButton mBaseballImageButton;
    private ImageButton mVolleyballImageButton;


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

        RelativeLayout rLayout = (RelativeLayout) findViewById(R.id.sports_selector_llayout);
        rLayout.setBackgroundColor(getResources().getColor(R.color.amber_400));


//        mSoccerImageView = (ImageView) findViewById(R.id.soccerImageView);
//        mSoccerImageView.setImageResource(R.drawable.soccer);
//        mSoccerImageView.setClickable(true);
//        mSoccerImageView.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                System.out.println("soccer intent placeholder");
//            }
//        });

        mSoccerImageButton = (ImageButton) findViewById(R.id.soccerImageButton);
        mSoccerImageButton.setImageResource(R.drawable.soccer96);
        mSoccerImageButton.setClickable(true);
        mSoccerImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Call Soccer Intent from ImageButton");
            }
        });

        mFootballImageButton = (ImageButton) findViewById(R.id.footballImageButton);
        mFootballImageButton.setImageResource(R.drawable.football96);
        mFootballImageButton.setClickable(true);
        mFootballImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Call Football Intent from ImageButton");
            }
        });

        mBasketballImageButton = (ImageButton) findViewById(R.id.basketballImageButton);
        mBasketballImageButton.setImageResource(R.drawable.basketball96);
        mBasketballImageButton.setClickable(true);
        mBasketballImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //BasketballChalk trueChalk = new BasketballChalk();
                //TrueChalkLab.get(getApplicationContext()).addChalk(trueChalk);
                //Intent i = new Intent(getApplicationContext(), BasketballChalkPagerActivity.class);
                //i.putExtra(BasketballChalkFragment.EXTRA_TRUECHALK_ID, trueChalk.getId());
                //startActivityForResult(i, 0);

                Intent i = new Intent(getApplicationContext(), BasketballChalkListActivity.class);
                startActivity(i);
            }
        });

        mBaseballImageButton = (ImageButton) findViewById(R.id.baseballImageButton);
        mBaseballImageButton.setImageResource(R.drawable.baseball96);
        mBaseballImageButton.setClickable(true);
        mBaseballImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Call Baseball Intent from ImageButton");
            }
        });

        mVolleyballImageButton = (ImageButton) findViewById(R.id.volleyballImageButton);
        mVolleyballImageButton.setImageResource(R.drawable.volleyball96);
        mVolleyballImageButton.setClickable(true);
        mVolleyballImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Call Volleyball Intent from ImageButton");
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
