package com.example.romanobolonskiy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button   transitionButton;

    private final int REQUEST_CODE = 55555;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        transitionButton = findViewById(R.id.activity_main_button);
        editText = findViewById(R.id.activity_main_edit_text1);

        transitionButton.setOnClickListener(buttonListener);
    }

    private final View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), SecondActivity.class);
            String justString = editText.getText().toString();
            intent.putExtra("justString", justString);
            startActivity(intent);
        }
    };

}