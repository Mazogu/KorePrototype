package com.example.micha.remotekodi.main;

import com.example.micha.remotekodi.BasePresenter;
import com.example.micha.remotekodi.BaseView;

/**
 * Created by micha on 3/12/2018.
 */

public interface MainContract {
    interface MPresenter extends BasePresenter<MView>{
        boolean checkString(String ip);
    }

    interface MView extends BaseView{

    }
}
