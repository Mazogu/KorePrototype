package com.example.micha.remotekodi.main;

/**
 * Created by micha on 3/12/2018.
 */

public class MainPresenter implements MainContract.MPresenter {

    MainContract.MView view;

    @Override
    public void attachView(MainContract.MView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }


    /**
     * Makes sure the string isn't empty
     * @param s A string to check
     * @return
     */
    @Override
    public boolean checkString(String s) {
        if(s.equals("")){
            view.showError("Neither field can be empty");
            return true;
        }
        return false;
    }
}
