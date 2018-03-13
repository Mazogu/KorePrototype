package com.example.micha.remotekodi.control;

/**
 * Created by micha on 3/12/2018.
 */

public class ControlPresenter implements ControlContract.CPresenter{
    ControlContract.CView view;
    String url;

    @Override
    public void attachView(ControlContract.CView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void createUrl(String ip, String port) {
        url = String.format("http://%1$s:%2$s/", ip, port);
    }
}
