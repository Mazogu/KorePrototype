package com.example.micha.remotekodi.remote;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.micha.remotekodi.R;
import com.example.micha.remotekodi.control.ControlActivity;


public class RemoteFragment extends Fragment implements RemoteContract.RView,ControlActivity.ControlButtons{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG = RemoteFragment.class.getSimpleName();
    RemoteContract.RPresenter presenter;

    // TODO: Rename and change types of parameters
    private String ipAddress;
    private String port;
    private TextView title;
    private PlayerReciever reciever;


    public RemoteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RemoteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RemoteFragment newInstance(String param1, String param2) {
        RemoteFragment fragment = new RemoteFragment();
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
            ipAddress = getArguments().getString(ARG_PARAM1);
            port = getArguments().getString(ARG_PARAM2);
        }
        presenter = new RemotePresenter(ipAddress,port);
        presenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_remote, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        if(view != null){
            title = view.findViewById(R.id.title);
        }
        reciever = new PlayerReciever((PlayerReciever.BroadCastInteractor) presenter);
        IntentFilter filter = new IntentFilter("Get Player");
        LocalBroadcastManager.getInstance(getActivity().getApplicationContext()).registerReceiver(reciever, filter);
    }

    @Override
    public void onDetach() {
        reciever = null;
        super.onDetach();
    }

    public void navigate(View view) {
        Log.d(TAG, "navigate: Button pressed");
        String command = view.getTag().toString();
        presenter.send(command,"navigate");
    }

    @Override
    public void play(View view) {
        switch (view.getId()){
            case R.id.playbutton:
                presenter.playCommand(view.getTag().toString());
                break;

            case R.id.stopbutton:
                presenter.playCommand(view.getTag().toString());
                break;

            case R.id.nextbutton:
                presenter.track("next");
                break;

            case R.id.backbutton:
                presenter.track("previous");
                break;
        }
    }

    @Override
    public void onDestroyView() {
        title = null;
        super.onDestroyView();
    }

    @Override
    public void showError(String s) {

    }


    @Override
    public void setMessage(String s) {
        if(title!=null){
            title.setText(s);
        }
    }
}
