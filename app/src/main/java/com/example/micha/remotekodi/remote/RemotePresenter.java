package com.example.micha.remotekodi.remote;

import android.util.Log;

import com.example.micha.remotekodi.model.music.Music;
import com.example.micha.remotekodi.utils.Request;
import com.example.micha.remotekodi.utils.RetrofitHelper;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by micha on 3/14/2018.
 */

public class RemotePresenter implements RemoteContract.RPresenter, PlayerReciever.BroadCastInteractor {

    private static final String TAG = RemotePresenter.class.getSimpleName();
    private RemoteContract.RView view;
    private String ip,port;
    private int player;

    public RemotePresenter(String ip,String port){
        this.ip = ip;
        this.port = port;
        player = 3;
    }

    @Override
    public void attachView(RemoteContract.RView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void send(String command, String id) {
        String url = "http://"+ip+":"+port+"/";
        Request request = new Request.Builder(command).addID(id).build();
        sendCommand(url, request);
    }

    @Override
    public void playCommand(String tag) {
        String url = "http://"+ip+":"+port+"/";
        String param = "\"playerid\":"+player;
        Request request = new Request.Builder(tag).addID("play").addArgs(param).build();
        sendCommand(url, request);
    }

    private void sendCommand(String url, Request request) {
        RetrofitHelper.getResponse(url,request.toString()).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

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

    @Override
    public void track(String next) {
        String url = "http://"+ip+":"+port+"/";
        String param1 = String.format("\"to\":\"%s\"",next);
        String param2 = "\"playerid\":"+player;
        Request request = new Request.Builder("Player.Goto").addArgs(param1,param2).addID("track").build();
        sendCommand(url,request);
    }

    @Override
    public void sendId(int id) {

        String method = "Player.GetItem";
        player = id;
        switch (id){
            case 0:{
                String param1 = "\"properties\": [\"title\",\"album\",\"artist\",\"thumbnail\"]";
                String param2 = "\"playerid\":"+id;
                Request request = new Request.Builder(method).addArgs(param1,param2).addID("music_player").build();
                getSong(request);
                break;
            }
            case 1:{

                break;
            }
            default:{
                view.setMessage("Nothing is playing");
                break;
            }
        }
    }


    private void getSong(Request request) {
        String url = "http://"+ip+":"+port+"/";
        RetrofitHelper.getMusic(url,request.toString()).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<Music>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Music song) {
                if(song!=null&&view!=null){
                    view.setMessage(song.getResult().getItem().getTitle());
                }

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
}
