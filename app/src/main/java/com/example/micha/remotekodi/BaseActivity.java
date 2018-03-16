package com.example.micha.remotekodi;

import android.content.ComponentName;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.micha.remotekodi.control.ControlActivity;
import com.example.micha.remotekodi.media.MediaActivity;

public class BaseActivity extends AppCompatActivity {

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private ListView list;
    protected String ipaddress;
    protected String port;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        drawer = findViewById(R.id.drawer);
        list = findViewById(R.id.drawerList);
        ipaddress = getIntent().getStringExtra("ip address");
        port = getIntent().getStringExtra("port");
        
        addToDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selection = ((TextView) view).getText().toString();
                Intent intent = new Intent();
                intent.putExtra("ip address", ipaddress);
                intent.putExtra("port", port);

                if(selection.equals(getString(R.string.remote_mode))){
                    intent.setComponent(new ComponentName(getApplicationContext(), ControlActivity.class));
                    startActivity(intent);
                    return;
                }

                intent.setComponent(new ComponentName(getApplicationContext(), MediaActivity.class));

                if(selection.equals(getString(R.string.movie_mode))){
                    intent.setAction(MediaActivity.MOVIE);
                }
                else if(selection.equals(getString(R.string.tv_mode))){
                    intent.setAction(MediaActivity.TV);
                }

                else if(selection.equals(getString(R.string.music_mode))){
                    intent.setAction(MediaActivity.MUSIC);
                }

                else if(selection.equals(getString(R.string.favorites_mode))){
                    intent.setAction(MediaActivity.FAVORITES);
                }
                startActivity(intent);

            }
        });

        toggle = new ActionBarDrawerToggle(this, drawer, R.string.drawer_open, R.string.drawer_closed){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(R.string.nav);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(getTitle());
                invalidateOptionsMenu();
            }
        };

        toggle.setDrawerIndicatorEnabled(true);
        drawer.addDrawerListener(toggle);
    }

    private void addToDrawer() {
        String[] array = getResources().getStringArray(R.array.selection);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,array);
        list.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("ip address",ipaddress);
        outState.putString("port", port);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ipaddress = savedInstanceState.getString("ip address");
        port = savedInstanceState.getString("port");
    }
}
