package com.example.administrator.hanlderexecise26;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView)findViewById(R.id.textContent);
        final android.os.Handler handler =new Handler(){
            public void handleMessage(Message msg) {
                textView.setText(msg.arg1+"");
            }
        };
        final Runnable myWorker = new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while (progress<=100){
                    Message msg = new Message();
                    msg.arg1 = progress;
                    handler.sendMessage(msg);
                    progress+= 10;
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Message msg = handler.obtainMessage();//同 new Message();
                msg.arg1 = -1;
                handler.sendMessage(msg);

            }
        };
        Button btnStart = (Button)findViewById(R.id.buttonStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread workThread = new Thread(null, myWorker, "WorkThread");
                workThread.start();
            }
        });
    }
}
