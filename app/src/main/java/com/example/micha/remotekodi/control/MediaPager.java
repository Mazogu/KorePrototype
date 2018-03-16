package com.example.micha.remotekodi.control;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.micha.remotekodi.playlist.PlaylistFragment;
import com.example.micha.remotekodi.remote.RemoteFragment;

/**
 * Created by micha on 3/15/2018.
 */

public class MediaPager extends FragmentStatePagerAdapter {
    private String ip,port;
    private Fragment currentFragment;

    public MediaPager(FragmentManager fm, String ip, String port) {
        super(fm);
        this.ip = ip;
        this.port = port;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:{
                currentFragment = RemoteFragment.newInstance(ip, port);
                return currentFragment;
            }

            case 1:{
                return PlaylistFragment.newInstance(ip, port);
            }
            default:{
                currentFragment = RemoteFragment.newInstance(ip, port);
                return currentFragment;
            }
        }
    }

    public Fragment getCurrentFragment(){
        return currentFragment;
    }

    @Override
    public int getCount() {
        return ControlActivity.NUM_PAGES;
    }
}
