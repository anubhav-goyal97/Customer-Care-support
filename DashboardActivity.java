package com.anubhav.lenovo.dell;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    WebView mWebview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawer);
        mToggle=new ActionBarDrawerToggle(this,mDrawerLayout, R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView=(NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.home)
        {
            Intent i = new Intent(DashboardActivity.this, searchhhh.class);
            startActivity(i);
        }

        else if(id==R.id.device)
        {
            Intent i = new Intent(DashboardActivity.this, registeractivity.class);
            startActivity(i);
        }
       else if(id==R.id.regsitered)
        {
            Intent i = new Intent(DashboardActivity.this, DashboardActivity.class);
            startActivity(i);
        }
        if(id==R.id.logout)
        {
           System.exit(0);
        }
        return false;
    }


    public void openfaq(View view){
        Intent i = new Intent(DashboardActivity.this, FaqActivity.class);
        startActivity(i);


    }
    public void opensearch(View view){
        Intent i = new Intent(DashboardActivity.this, SearchActivity.class);
        startActivity(i);


    }
    public void openchat(View view){
        Intent i = new Intent(DashboardActivity.this, MainActivity.class);
        startActivity(i);


    }
}
