package com.example.micha.remotekodi.playlist;

import com.example.micha.remotekodi.BasePresenter;
import com.example.micha.remotekodi.BaseView;
import com.example.micha.remotekodi.model.playlist.Item;

import java.util.List;

/**
 * Created by micha on 3/15/2018.
 */

public interface PlaylistContract {
    interface PlayView extends BaseView{
        void getList(List<Item> items, int previousId);
    }

    interface PlayPresenter extends BasePresenter<PlayView>{

    }
}
