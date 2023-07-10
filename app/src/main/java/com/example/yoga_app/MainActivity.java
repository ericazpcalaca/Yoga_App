package com.example.yoga_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth auth;
    private Button btnLogout;
    private TextView textView;
    private FirebaseUser user;
    private androidx.appcompat.widget.Toolbar toolbar;
    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new Home()).commit();
            navigationView.setCheckedItem(R.id.home);
            toolbar.setTitle("Yoga");
        }


//        auth = FirebaseAuth.getInstance();
//        btnLogout = findViewById(R.id.btnLogout);
//        textView = findViewById(R.id.textView);
//        user = auth.getCurrentUser();
//
//        if(user == null){
//            startActivity(new Intent(getApplicationContext(),Login.class));
//            finish();
//        }else{
//            textView.setText(user.getEmail());
//        }
//


    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // When the user clicks on the item in the menu, it goes to the selected fragment and closes the menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new Home()).commit();
                drawer.closeDrawer(GravityCompat.START);
                toolbar.setTitle("Yoga");
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
                Toast.makeText(this,"Share",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_send:
                Toast.makeText(this,"Send",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}