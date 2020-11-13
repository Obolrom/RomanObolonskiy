package com.example.romanobolonskiy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MusicTrackAdapter.ActivityConnector {

    private Button addButton;
    private FragmentManager fragmentManager;
    private Fragment musicAdderFragment;
    private Fragment listFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = this.getSupportFragmentManager();
        musicAdderFragment = (MusicAdder) fragmentManager.findFragmentById(R.id.input_fields_frag);
        listFrag = (ListFrag) fragmentManager.findFragmentById(R.id.list);

        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View fragmentView = musicAdderFragment.getView();
                String songName;
                String executorName;
                String album;

                if (fragmentView != null) {
                    songName = ((EditText) fragmentView.findViewById(R.id.et_song)).getText().toString().trim();
                    executorName = ((EditText) fragmentView.findViewById(R.id.et_executor)).getText().toString().trim();
                    album = ((EditText) fragmentView.findViewById(R.id.et_album)).getText().toString().trim();

                    if (songName.isEmpty() || executorName.isEmpty() || album.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please, fill add fields", Toast.LENGTH_SHORT).show();
                    } else {
                        ListFrag.list.add(new MusicTrack(songName, executorName, album, "4:20"));
                        ((ListFrag) listFrag).notifyDataChanges();
                    }
                }

            }
        });


    }


    @Override
    public void recyclerItemSelected(int index) {
        Toast.makeText(this,
                "Song: " + ListFrag.list.get(index).getSong() +
                "\nExecutor: " + ListFrag.list.get(index).getExecutor(), Toast.LENGTH_SHORT).show();
    }
}
