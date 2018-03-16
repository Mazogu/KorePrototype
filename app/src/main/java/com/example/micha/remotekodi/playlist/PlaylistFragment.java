package com.example.micha.remotekodi.playlist;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.micha.remotekodi.R;
import com.example.micha.remotekodi.model.playlist.Item;
import com.example.micha.remotekodi.remote.PlayerReciever;
import com.example.micha.remotekodi.utils.PlaylistAdapter;

import java.util.List;

public class PlaylistFragment extends Fragment implements PlaylistContract.PlayView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG = PlaylistFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private PlayerReciever reciever;
    private PlaylistContract.PlayPresenter presenter;

    // TODO: Rename and change types of parameters
    private String ip;
    private String port;


    public PlaylistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlaylistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlaylistFragment newInstance(String param1, String param2) {
        PlaylistFragment fragment = new PlaylistFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ip = getArguments().getString(ARG_PARAM1);
            port = getArguments().getString(ARG_PARAM2);
        }
        presenter = new PlaylistPresenter(ip,port);
        presenter.attachView(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playlist, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        if(view != null){
            recyclerView = view.findViewById(R.id.playList);
            RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(manager);

        }

        reciever = new PlayerReciever((PlayerReciever.BroadCastInteractor) presenter);
        IntentFilter filter = new IntentFilter("Get Player");
        LocalBroadcastManager.getInstance(getActivity().getApplicationContext()).registerReceiver(reciever, filter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        reciever = null;
        super.onDetach();
    }

    @Override
    public void showError(String s) {
        Log.d(TAG, "showError:  "+s);
    }

    @Override
    public void getList(List<Item> items, int previousId) {
        PlaylistAdapter adapter = new PlaylistAdapter(items,previousId,ip,port);
        if(recyclerView != null){
            recyclerView.setAdapter(adapter);
        }
    }
}
