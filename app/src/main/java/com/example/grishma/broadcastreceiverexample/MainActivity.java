package com.example.grishma.broadcastreceiverexample;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    BroadCastReceiver broadCastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        broadCastReceiver = new BroadCastReceiver();
    }

    public void send_broadcast(View view) {
        Log.e("MainActivity", "Send Broadcast");
        Intent intent = new Intent();
        intent.setAction("myAction");
        Log.e("MainActivity", "sent?");
        sendBroadcast(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //registerReceiver(broadCastReceiver, new IntentFilter("myAction"));
        registerReceiver(broadCastReceiver,new IntentFilter("abc"));
    }

    @Override
    protected void onPause() {
        //unregisterReceiver(broadCastReceiver);
        Log.e("MainActivity", "unregistered");
        super.onPause();
    }

    public void handleClick(View view)
    {
        Intent intent=new Intent();
        intent.setAction("abc");
        sendBroadcast(intent);

    }
}
