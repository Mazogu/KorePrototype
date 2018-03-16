package com.example.micha.remotekodi.remote;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by micha on 3/16/2018.
 */

public class PlayerReciever extends BroadcastReceiver {

    private BroadCastInteractor interactor;

    public PlayerReciever(BroadCastInteractor interactor) {
        this.interactor = interactor;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("Get Player")&&interactor!=null){
            interactor.sendId(intent.getIntExtra("playerId",3));
        }
    }

    public interface BroadCastInteractor{
        void sendId(int id);
    }
}
