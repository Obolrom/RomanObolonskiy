package com.example.romanobolonskiy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;
    private String   gotMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        gotMessage = intent.getStringExtra("justString");

        textView = findViewById(R.id.activity_second_text_view1);
        if (gotMessage.isEmpty()) {
            textView.setText("THE STRING WAS EMPTY");
        } else {
            textView.setText(gotMessage);
        }

    }

}
