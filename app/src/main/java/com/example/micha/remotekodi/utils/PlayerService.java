package com.example.micha.remotekodi.utils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

import com.example.micha.remotekodi.model.players.Players;

import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PlayerService extends Service {

    public static final String TAG = PlayerService.class.getSimpleName();
    private String url;
    Timer timer;
    TimerTask task;

    public PlayerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent.getAction().equals("Start")){
            String ip = intent.getStringExtra("ip address");
            String port = intent.getStringExtra("port");
            url = "http://"+ip+":"+port+"/";
            Request request = new Request.Builder("Player.GetActivePlayers").addID("Players").build();
            timer = new Timer();
            makeCalls(request);
            timer.schedule(task,0,5000);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void makeCalls(final Request request) {
        task = new TimerTask() {
            @Override
            public void run() {
                RetrofitHelper.getPlayers(url,request.toString()).observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Observer<Players>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Players players){
                                int playerId = 3;
                                if(players.getResult().size() > 0){
                                    playerId = players.getResult().get(0).getPlayerid();
                                }
                                LocalBroadcastManager local = LocalBroadcastManager.getInstance(getApplicationContext());
                                Intent intent = new Intent();
                                intent.setAction("Get Player");
                                intent.putExtra("playerId", playerId);
                                local.sendBroadcast(intent);
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        };
    }

    @Override
    public void onDestroy() {
        timer.cancel();
        timer = null;
        task = null;
        super.onDestroy();
    }
}
