package com.example.yoga_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String appTitle = "Zen Flow Yoga";
    private FirebaseAuth auth;
    private FirebaseUser user;
    private androidx.appcompat.widget.Toolbar toolbar;
    private DrawerLayout drawer;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(appTitle);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new Home()).commit();
            navigationView.setCheckedItem(R.id.home);
            toolbar.setTitle(appTitle);
        }

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.navEmail);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if (user == null) {
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        } else {
            navUsername.setText(user.getEmail());
        }

        //Retrieve the data
        DataRetriever dataRetriever = new DataRetriever(this);

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // When the user clicks on the item in the menu,
    // it goes to the selected fragment and closes the menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new Home()).commit();
                drawer.closeDrawer(GravityCompat.START);
                toolbar.setTitle(appTitle);
                break;
            case R.id.explore:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new Explore()).commit();
                drawer.closeDrawer(GravityCompat.START);
                toolbar.setTitle("Explore");
                break;
            case R.id.progress:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new DailyProgress()).commit();
                drawer.closeDrawer(GravityCompat.START);
                toolbar.setTitle("Daily Progress");
                break;
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new Profile()).commit();
                drawer.closeDrawer(GravityCompat.START);
                toolbar.setTitle("Profile");
                break;
            case R.id.nav_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Join me on a journey to inner peace and wellness with the amazing Zen Flow Yoga! \n\n https://play.google.com/store/apps/" + getPackageName());
                startActivity(Intent.createChooser(sendIntent, "Choose one"));
                break;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, Login.class));
                finish();
                break;
        }
        return true;
    }
}