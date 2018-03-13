package com.example.micha.remotekodi.control;

import com.example.micha.remotekodi.BasePresenter;
import com.example.micha.remotekodi.BaseView;

/**
 * Created by micha on 3/12/2018.
 */

public interface ControlContract {
    interface CPresenter extends BasePresenter<CView>{
        void createUrl(String ip, String port);
    }

    interface CView extends BaseView{

    }
}
