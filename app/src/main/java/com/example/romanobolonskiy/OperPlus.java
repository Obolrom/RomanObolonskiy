package com.example.romanobolonskiy;

import android.os.Handler;
import android.os.Message;
import android.text.Editable;

public class OperPlus implements Calculable, WhatCodes {

    Editable numberStr1;
    Editable numberStr2;
    Handler handler;

    public OperPlus(Handler handler, Editable numberStr1, Editable numberStr2) {
        this.numberStr1 = numberStr1;
        this.numberStr2 = numberStr2;;
        this.handler = handler;
    }

    @Override
    public Message getMessage() {
        Message message;
        String result = "";
        String number2 = numberStr2.toString().trim();
        String number1 = numberStr1.toString().trim();
        Double resNumber;

        resNumber = Double.parseDouble(number1) + Double.parseDouble(number2);
        result = number1 + " + " + number2 + " = " + resNumber.toString();
        message = handler.obtainMessage(WHAT_OK, result);

        return message;
    }
}
