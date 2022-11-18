package com.why.clock;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView clock = findViewById(R.id.clock);
        Button button = findViewById(R.id.button);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        String current = time.format(new Date());
        clock.setText(current);

        button.setOnClickListener(view -> {
            String current1 = time.format(new Date());
            clock.setText(current1);
        });
    }
}