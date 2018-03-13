package com.example.micha.remotekodi;

/**
 * Created by micha on 3/12/2018.
 */

public interface BasePresenter<V extends BaseView> {

    /**
     * Attaches a view to communicate with.
     * @param view View to attach
     */
    void attachView(V view);

    /**
     * Removes attached view.
     */
    void detachView();
}
