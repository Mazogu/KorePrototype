package com.example.micha.remotekodi;

/**
 * Created by micha on 3/12/2018.
 */

public interface BasePresenter<V extends BaseView> {
    void attachView(V view);
    void detachView();
}
