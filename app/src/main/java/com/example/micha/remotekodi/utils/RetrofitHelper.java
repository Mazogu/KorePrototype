package com.example.micha.remotekodi.utils;

import com.example.micha.remotekodi.model.music.Music;
import com.example.micha.remotekodi.model.players.Players;
import com.example.micha.remotekodi.model.playlist.Playlist;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by micha on 3/12/2018.
 */

public class RetrofitHelper {
    /**
     * Factory class for creating retrofit objects
     */
    public static class Factory{
        /**
         * Creates a retrofit object to cast into a retrofit service
         * @param url Url needed to connect to kodi
         * @return A retrofit object with the needed url
         */
        public static Retrofit getRetrofit(String url){
            return new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .baseUrl(url).build();
        }

        public static Retrofit getObjectRetrofit(String url){
            return new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(url).build();
        }
    }

    /**
     * Uses the factor method to create a retrofit service object and calls the get method
     * @param url Url needed to connect.
     * @param request Jsonrpc request method.
     * @return returns reponse string in json format.
     */
    public static Observable<String> getResponse(String url, String request){
        RetrofitService service = Factory.getRetrofit(url).create(RetrofitService.class);
        return service.getResponse(request);
    }

    public static Observable<Players> getPlayers(String url, String request){
        RetrofitService service = Factory.getObjectRetrofit(url).create(RetrofitService.class);
        return service.getPlayers(request);
    }

    public static Observable<Music> getMusic(String url, String request){
        RetrofitService service = Factory.getObjectRetrofit(url).create(RetrofitService.class);
        return service.getMusic(request);
    }

    public static Observable<Playlist> getPlaylist(String url, String request){
        RetrofitService service = Factory.getObjectRetrofit(url).create(RetrofitService.class);
        return service.getPlaylist(request);
    }


    /**
     * Interface for get request methods.
     */
    public interface RetrofitService{

        /**
         * Sends request to kodi application using the ip address and port as a url
         * @param request
         * @return
         */
        @GET("jsonrpc")
        Observable<String> getResponse(@Query("request") String request);

        @GET("jsonrpc")
        Observable<Players> getPlayers(@Query("request") String request);

        @GET("jsonrpc")
        Observable<Music> getMusic(@Query("request") String request);

        @GET("jsonrpc")
        Observable<Playlist> getPlaylist(@Query("request") String request);
    }
}
