package com.example.romanobolonskiy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Path;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private Handler handler;

    private Button  plusBtn;
    private Button  minusBtn;
    private Button  multiplyBtn;
    private Button  divisionBtn;
    private EditText field1;
    private EditText field2;
    private TextView expression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plusBtn = findViewById(R.id.btn1);
        minusBtn = findViewById(R.id.btn2);
        multiplyBtn = findViewById(R.id.btn3);
        divisionBtn = findViewById(R.id.btn4);
        field1 = findViewById(R.id.first_et);
        field2 = findViewById(R.id.second_et);
        expression = findViewById(R.id.tv);

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fieldChecker()) {
                    Toast.makeText(MainActivity.this, "all fields should be filled", Toast.LENGTH_SHORT).show();
                } else {
                    CalculusThread thread = new CalculusThread(
                            new OperPlus(handler, field1.getText(), field2.getText()));
                    thread.start();
                }
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fieldChecker()) {
                    Toast.makeText(MainActivity.this, "all fields should be filled", Toast.LENGTH_SHORT).show();
                } else {
                    CalculusThread thread = new CalculusThread(
                            new OperMinus(handler, field1.getText(), field2.getText()));
                    thread.start();
                }
            }
        });

        multiplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fieldChecker()) {
                    Toast.makeText(MainActivity.this, "all fields should be filled", Toast.LENGTH_SHORT).show();
                } else {
                    CalculusThread thread = new CalculusThread(
                            new OperMultiply(handler, field1.getText(), field2.getText()));
                    thread.start();
                }
            }
        });

        divisionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fieldChecker()) {
                    Toast.makeText(MainActivity.this, "all fields should be filled", Toast.LENGTH_SHORT).show();
                } else {
                    CalculusThread thread = new CalculusThread(
                            new OperDevise(handler, field1.getText(), field2.getText()));
                    thread.start();
                }
            }
        });

        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                expression.setText(msg.obj.toString());
            }
        };

    }

    private class CalculusThread extends Thread {
        Calculable calculable;

        public CalculusThread(Calculable calculable) {
            this.calculable = calculable;
        }

        @Override
        public void run() {
            Message message = calculable.getMessage();
            handler.sendMessage(message);
        }
    }

    private boolean fieldChecker() {
        return field1.getText().toString().isEmpty() | field2.getText().toString().isEmpty();
    }

}









