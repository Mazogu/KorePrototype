package com.example.micha.remotekodi.remote;

import com.example.micha.remotekodi.BasePresenter;
import com.example.micha.remotekodi.BaseView;

/**
 * Created by micha on 3/14/2018.
 */

public interface RemoteContract {
    interface RView extends BaseView{

        void setMessage(String s);
    }

    interface RPresenter extends BasePresenter<RView>{
        void send(String command, String navigate);
        void playCommand(String tag);
        void track(String next);
    }
}
