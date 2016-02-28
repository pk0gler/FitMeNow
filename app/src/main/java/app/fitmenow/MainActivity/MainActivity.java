package app.fitmenow.MainActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import app.fitmenow.R;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ActionBarDrawerToggle hamburger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set Toolbar attributes
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));

        //Setting up the Navigation Drawer
        final DrawerLayout navDraw = (DrawerLayout) findViewById(R.id.drawer_layout);
        hamburger = new ActionBarDrawerToggle(this, navDraw, toolbar, R.string.app_name, R.string.app_name);
        navDraw.setDrawerListener(hamburger);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                //Toast.makeText(getBaseContext(), "Hi",Toast.LENGTH_SHORT).show();
                navDraw.closeDrawer(GravityCompat.START);
                //displayView(item);
                return false;
            }
        });
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //To show the hamburger
        hamburger.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //TO show the hamburger
        hamburger.syncState();
    }
}
