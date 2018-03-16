package com.example.micha.remotekodi.playlist;

import android.util.Log;

import com.example.micha.remotekodi.model.playlist.Playlist;
import com.example.micha.remotekodi.remote.PlayerReciever;
import com.example.micha.remotekodi.utils.Request;
import com.example.micha.remotekodi.utils.RetrofitHelper;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by micha on 3/15/2018.
 */

public class PlaylistPresenter implements PlaylistContract.PlayPresenter,PlayerReciever.BroadCastInteractor {

    public static final String TAG = PlaylistPresenter.class.getSimpleName();
    private PlaylistContract.PlayView view;
    private int previousId;
    private String ip,port;

    public PlaylistPresenter(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }


    @Override
    public void attachView(PlaylistContract.PlayView view) {
        this.view = view;
        previousId = 4;
    }


    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void sendId(int id) {
        Log.d(TAG, "sendId: "+id);
        if(id != previousId && id <= 1 && id >= 0){
            String url = "http://" + ip + ":" + port + "/";
            previousId = id;
            String param1 = "\"playlistid\":"+id;
            String param2 = getProperties(id);
            Request request = new Request.Builder("Playlist.GetItems").addArgs(param1,param2).addID("playlist").build();
            getMusicPlaylist(url,request);
        }
    }

    private String getProperties(int id) {
        switch (id){
            case 0:
                return "\"properties\":[\"title\",\"artist\"]";
            case 1:
                return "\"properties\":[\"title\"]";
            default:
                return "\"properties\":[\"title\"]";
        }
    }

    private void getMusicPlaylist(String url, Request request) {
        RetrofitHelper.getPlaylist(url,request.toString()).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Playlist>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Playlist playlist) {
                        view.getList(playlist.getResult().getItems(),previousId);
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

    private void getPlaylist(String url, Request request) {
        RetrofitHelper.getResponse(url,request.toString()).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: "+s);
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
