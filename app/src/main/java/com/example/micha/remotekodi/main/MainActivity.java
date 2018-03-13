package com.example.micha.remotekodi.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.micha.remotekodi.R;
import com.example.micha.remotekodi.control.ControlActivity;

public class MainActivity extends AppCompatActivity implements MainContract.MView {

    EditText ipAddress, port;
    MainContract.MPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ipAddress = findViewById(R.id.ip);
        port = findViewById(R.id.port);
        presenter = new MainPresenter();
        presenter.attachView(this);
    }

    /**
     * The onclick method for the button. Makes sure the fields aren't empty. Starts the next activity if they aren't.
     * @param view the button.
     */
    public void connect(View view) {
        String ip = ipAddress.getText().toString();
        String portAccess = port.getText().toString();
        if(presenter.checkString(ip)){
            ipAddress.setError("Needs an ip address");
        }
        if(presenter.checkString(portAccess)){
            port.setError("Needs a port");
        }
        else{
            Intent intent = new Intent(getApplicationContext(), ControlActivity.class);
            intent.putExtra("ip address", ip);
            intent.putExtra("port", portAccess);
            startActivity(intent);
        }

    }

    @Override
    public void showError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
