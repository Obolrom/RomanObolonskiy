package com.example.romanobolonskiy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements WhatCodes {

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
                    Calculable performer = new OperationPerformer(new OperationPerformer.CalculableCall() {
                        @Override
                        public Message callbackMessage(String number1, String number2) {
                            Message message;
                            String result = "";
                            Double resNumber;

                            resNumber = Double.parseDouble(number1) + Double.parseDouble(number2);
                            result = number1 + " + " + number2 + " = " + resNumber.toString();
                            message = handler.obtainMessage(WHAT_OK, result);

                            return message;
                        }
                    }, field1.getText(), field2.getText());
                    new CalculusThread(performer).start();
                }
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fieldChecker()) {
                    Toast.makeText(MainActivity.this, "all fields should be filled", Toast.LENGTH_SHORT).show();
                } else {
                    Calculable performer = new OperationPerformer(new OperationPerformer.CalculableCall() {
                        @Override
                        public Message callbackMessage(String number1, String number2) {
                            Message message;
                            String result = "";
                            Double resNumber;

                            resNumber = Double.parseDouble(number1) - Double.parseDouble(number2);
                            result = number1 + " - " + number2 + " = " + resNumber.toString();
                            message = handler.obtainMessage(WHAT_OK, result);

                            return message;
                        }
                    }, field1.getText(), field2.getText());
                    new CalculusThread(performer).start();
                }
            }
        });

        multiplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fieldChecker()) {
                    Toast.makeText(MainActivity.this, "all fields should be filled", Toast.LENGTH_SHORT).show();
                } else {
                    Calculable calculable = new OperationPerformer(new OperationPerformer.CalculableCall() {
                        @Override
                        public Message callbackMessage(String number1, String number2) {
                            Message message;
                            String result = "";
                            Double resNumber;

                            resNumber = Double.parseDouble(number1) * Double.parseDouble(number2);
                            result = number1 + " * " + number2 + " = " + resNumber.toString();
                            message = handler.obtainMessage(WHAT_OK, result);

                            return message;
                        }
                    }, field1.getText(), field2.getText());
                    new CalculusThread(calculable).start();
                }
            }
        });

        divisionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fieldChecker()) {
                    Toast.makeText(MainActivity.this, "all fields should be filled", Toast.LENGTH_SHORT).show();
                } else {
                    Calculable performer = new OperationPerformer(new OperationPerformer.CalculableCall() {
                        @Override
                        public Message callbackMessage(String number1, String number2) {
                            Message message;
                            String result = "";
                            Double resNumber;

                            if (number2.equals("0")) {
                                result = "division by 0";
                                message = handler.obtainMessage(WHAT_DIVISION_BY_ZERO, result);
                            } else {
                                resNumber = Double.parseDouble(number1) / Double.parseDouble(number2);
                                result = number1 + " / " + number2 + " = " + resNumber.toString();
                                message = handler.obtainMessage(WHAT_OK, result);
                            }
                            return message;
                        }
                    }, field1.getText(), field2.getText());
                    new CalculusThread(performer).start();
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
        private Calculable calculable;

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









