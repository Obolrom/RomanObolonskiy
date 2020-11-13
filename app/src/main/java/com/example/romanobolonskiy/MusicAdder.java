package com.example.romanobolonskiy;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class MusicAdder extends Fragment {

    private View view;
    private EditText songName;
    private EditText executor;
    private EditText album;

    public MusicAdder() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_music_adder, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        songName = view.findViewById(R.id.et_song);
        executor = view.findViewById(R.id.et_executor);
        album = view.findViewById(R.id.et_album);
    }
}