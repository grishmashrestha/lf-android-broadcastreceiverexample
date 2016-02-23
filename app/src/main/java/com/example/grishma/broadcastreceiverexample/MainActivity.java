package com.example.grishma.broadcastreceiverexample;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    BroadCastReceiver broadCastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
//        registerReceiver(broadCastReceiver, new IntentFilter("abc"));
        registerReceiver(broadCastReceiver, new IntentFilter("progressBar"));
    }

    @Override
    protected void onPause() {
        unregisterReceiver(broadCastReceiver);
        Log.e("MainActivity", "unregistered");
        super.onPause();
    }

    public void handleClick(View view)
    {
        Intent intent=new Intent();
        intent.setAction("abc");
        sendBroadcast(intent);

    }

    public class MyAsyncTask extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                URL url = new URL("http://www.clipartbest.com/cliparts/9i4/o8L/9i4o8LL6T.png");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();

                File SDCardRoot = getFilesDir();

                File file = new File(SDCardRoot, "test.png");
                FileOutputStream fileOutput = new FileOutputStream(file);
                InputStream inputStream = urlConnection.getInputStream();
                int totalSize = urlConnection.getContentLength();
                int downloadedSize = 0;
                byte[] buffer = new byte[1024];
                int bufferLength = 0;
                while((bufferLength = inputStream.read(buffer)) > 0) {
                    fileOutput.write(buffer, 0, bufferLength);
                    downloadedSize += bufferLength;
                    updateProgress(downloadedSize, totalSize);
                }
                fileOutput.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    public void downloadImage(View view) {
        new MyAsyncTask().execute();
    }

    public void updateProgress(int downloadedSize, int totalSize) {
        HashMap<String , Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("downloadedSize", downloadedSize);
        hashMap.put("totalSize", totalSize);

        Intent intent = new Intent();
        intent.putExtra("size", hashMap);
        intent.setAction("progressBar");
        sendBroadcast(intent);
    }
}
