package com.example.micha.remotekodi.utils;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.micha.remotekodi.R;
import com.example.micha.remotekodi.model.playlist.Item;
import com.example.micha.remotekodi.model.playlist.Playlist;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by micha on 3/16/2018.
 */

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistHolder> {

    public static final String TAG = PlaylistAdapter.class.getSimpleName();
    private List<Item> list;
    private int type;
    private String url;

    public PlaylistAdapter(List<Item> list, int previousId, String ip, String port){
        this.list = list;
        this.type = previousId;
        url = "http://" + ip + ":" + port + "/";
    }

    @Override
    public PlaylistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_list, parent, false);
        return new PlaylistHolder(view);
    }

    @Override
    public void onBindViewHolder(PlaylistHolder holder, final int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToTrack(position);
            }
        });
        holder.artist.setText(list.get(position).getArtist().get(0));
    }

    private void goToTrack(int position) {
        String param1 = "\"playerid\":"+type;
        String param2 = "\"to\":"+position;
        Request request = new Request.Builder("Player.Goto").addArgs(param1,param2).addID("move").build();
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

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class PlaylistHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView artist;
        public PlaylistHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.songTitle);
            artist = itemView.findViewById(R.id.artist);
        }
    }

}
