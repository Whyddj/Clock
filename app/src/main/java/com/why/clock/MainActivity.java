package com.why.clock;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView clock;
    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, 1000);
            clock.setText(getCurrentTime());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        clock = findViewById(R.id.clock);

        clock.setText(getCurrentTime());

        handler.post(runnable);

        button.setOnClickListener(view -> {
            clock.setText(getCurrentTime());
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }


    private String getCurrentTime() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        return time.format(new Date());
    }

}