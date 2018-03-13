package com.example.micha.remotekodi.control;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.micha.remotekodi.R;

public class ControlActivity extends Activity implements ControlContract.CView {

    ControlContract.CPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        presenter = new ControlPresenter();
        presenter.attachView(this);
        Intent intent = getIntent();
        String ip = intent.getStringExtra("ip address");
        String port = intent.getStringExtra("port");
        presenter.createUrl(ip, port);
    }

    @Override
    public void showError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
