package com.example.micha.remotekodi.main.utils;

import java.lang.ref.SoftReference;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
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
    }

    /**
     * Uses the factor method to create a retrofit service object and calls the get method
     * @param url Url needed to connect.
     * @param request Jsonrpc request method.
     * @return returns reponse string in json format.
     */
    public Observable<String> getResponse(String url, String request){
        RetrofitService service = Factory.getRetrofit(url).create(RetrofitService.class);
        return service.getResponse(request);
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
    }
}
