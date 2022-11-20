package com.why.clock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private int record_number = 0;
    private List<Record> recordList = new ArrayList<>();
    private TextView clock;
    private SharedPreferences preferences;
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
        Button button_update = findViewById(R.id.button_update);
        Button button_record = findViewById(R.id.button_record);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecordAdapter adapter = new RecordAdapter(recordList);
        recyclerView.setAdapter(adapter);
        preferences = getSharedPreferences("time_record", Context.MODE_PRIVATE);

        clock = findViewById(R.id.clock);
        clock.setText(getCurrentTime());
        handler.post(runnable);

        button_update.setOnClickListener(view -> {
            clock.setText(getCurrentTime());
        });

        button_record.setOnClickListener(view -> {
            String time = getCurrentTime();

            record_number++;
            recordList.add(new Record(time));

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("record" + record_number, time);
            editor.putInt("number", record_number);
            editor.commit();
        });

        reload();
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

    private void reload() {
        record_number = preferences.getInt("number", 0);
        int number = record_number;
        for (int i = 1; i <= number; i++) {
            recordList.add(new Record(preferences.getString("record"+i,null)));
        }
    }
}