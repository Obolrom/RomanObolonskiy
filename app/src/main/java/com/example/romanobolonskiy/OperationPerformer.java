package com.example.romanobolonskiy;

import android.os.Message;
import android.text.Editable;

public class OperationPerformer implements Calculable, WhatCodes {

    interface CalculableCall {
        Message callbackMessage(String number1, String number2);
    }

    Editable numberStr1;
    Editable numberStr2;
    CalculableCall calculable;

    public OperationPerformer(CalculableCall calculable, Editable numberStr1, Editable numberStr2) {
        this.numberStr1 = numberStr1;
        this.numberStr2 = numberStr2;;
        this.calculable = calculable;
    }

    @Override
    public Message getMessage() {
        String number2 = numberStr2.toString().trim();
        String number1 = numberStr1.toString().trim();

        return calculable.callbackMessage(number1, number2);
    }
}
