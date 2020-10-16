package com.example.romanobolonskiy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button   transitionButton;

    private Button   toThirdActivity;
    private TextView resultOfThirdActivity;

    private final int REQUEST_CODE = 55555;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        transitionButton = findViewById(R.id.activity_main_button);
        editText = findViewById(R.id.activity_main_edit_text1);

        toThirdActivity = findViewById(R.id.activity_main_button2);
        resultOfThirdActivity = findViewById(R.id.activity_third_result_text_view);

        transitionButton.setOnClickListener(buttonToSecondActivity);
        toThirdActivity.setOnClickListener(buttonToThirdActivity);
    }

    private View.OnClickListener buttonToSecondActivity = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), SecondActivity.class);
            String justString = editText.getText().toString();
            intent.putExtra("justString", justString);
            startActivity(intent);
        }
    };

    private View.OnClickListener buttonToThirdActivity = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent("to.third.activity");
            startActivityForResult(intent, REQUEST_CODE);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        String result = resultOfThirdActivity.getText() + data.getStringExtra("name");
        resultOfThirdActivity.setText(result);
    }
}