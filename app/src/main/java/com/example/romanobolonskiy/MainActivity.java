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

    final int WHAT_OK = 100;
    final int WHAT_DIVISION_BY_ZERO = -100;

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
                    CalculusThread thread = new CalculusThread(field1.getText(), field2.getText(), Operations.Plus);
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
                    CalculusThread thread = new CalculusThread(field1.getText(), field2.getText(), Operations.Minus);
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
                    CalculusThread thread = new CalculusThread(field1.getText(), field2.getText(), Operations.Multiply);
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
                    CalculusThread thread = new CalculusThread(field1.getText(), field2.getText(), Operations.Devise);
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
        Editable numberStr1;
        Editable numberStr2;
        Operations operation;

        public CalculusThread(Editable numberStr1, Editable numberStr2, Operations operation) {
            this.numberStr1 = numberStr1;
            this.numberStr2 = numberStr2;
            this.operation = operation;
        }

        @Override
        public void run() {
            Message message = handler.obtainMessage();
            String result = "";
            String number2 = numberStr2.toString().trim();
            String number1 = numberStr1.toString().trim();
            Double resNumber;

            switch (operation) {
                case Devise:
                    if (number2.equals("0")) {
                        result = "division by 0";
                        message = handler.obtainMessage(WHAT_DIVISION_BY_ZERO, result);
                    } else {
                        resNumber = Double.parseDouble(number1) / Double.parseDouble(number2);
                        result = numberStr1 + " + " + numberStr2 + " = " + resNumber.toString();
                        message = handler.obtainMessage(WHAT_OK, result);
                    }
                    break;
                case Multiply:
                    resNumber = Double.parseDouble(number1) * Double.parseDouble(number2);
                    result = number1 + " + " + number2 + " = " + resNumber.toString();
                    message = handler.obtainMessage(WHAT_OK, result);
                    break;
                case Plus:
                    resNumber = Double.parseDouble(number1) + Double.parseDouble(number2);
                    result = number1 + " + " + number2 + " = " + resNumber.toString();
                    message = handler.obtainMessage(WHAT_OK, result);
                    break;
                case Minus:
                    resNumber = Double.parseDouble(number1) - Double.parseDouble(number2);
                    result = number1 + " + " + number2 + " = " + resNumber.toString();
                    message = handler.obtainMessage(WHAT_OK, result);
                    break;
            }
            handler.sendMessage(message);
        }
    }

    private boolean fieldChecker() {
        return field1.getText().toString().isEmpty() | field2.getText().toString().isEmpty();
    }

}









