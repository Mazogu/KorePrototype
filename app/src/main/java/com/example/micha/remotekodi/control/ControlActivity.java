package com.example.micha.remotekodi.control;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.micha.remotekodi.BaseActivity;
import com.example.micha.remotekodi.R;
import com.example.micha.remotekodi.remote.RemoteFragment;
import com.example.micha.remotekodi.utils.PlayerService;

public class ControlActivity extends BaseActivity {
    private static final String TAG = ControlActivity.class.getSimpleName();
    private ViewPager pager;
    public static final int NUM_PAGES = 2;
    private MediaPager mediaPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup views = findViewById(R.id.content);
        getLayoutInflater().inflate(R.layout.activity_control, views);
        pager = findViewById(R.id.pages);
        mediaPager = new MediaPager(getSupportFragmentManager(),ipaddress, port);
        pager.setAdapter(mediaPager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(getApplicationContext(), PlayerService.class);
        intent.putExtra("ip address",ipaddress);
        intent.putExtra("port", port);
        intent.setAction("Start");
        startService(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Intent intent = new Intent(getApplicationContext(),PlayerService.class);
        stopService(intent);
    }

    public void navigate(View view) {
        Fragment fragment = mediaPager.getCurrentFragment();
        ((ControlButtons) fragment).navigate(view);

    }

    public void play(View view) {
        Fragment fragment = mediaPager.getCurrentFragment();
        ((ControlButtons) fragment).play(view);
    }

    public interface ControlButtons{
        void navigate(View view);
        void play(View view);
    }
}
