package com.example.romanobolonskiy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListFrag extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static ArrayList<MusicTrack> list;

    public ListFrag() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.list_frag, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        list = new ArrayList<MusicTrack>();
        list.add(new MusicTrack("People are strange", "The Doors", "Have no idea", "2:35"));
        list.add(new MusicTrack("Master of Puppets", "Metallica", "Master of Puppets", "8:36"));
        list.add(new MusicTrack("Sad but true", "Metallica", "The black album", "5:36"));
        list.add(new MusicTrack("One", "Metallica", "And Justice for all", "8:36"));
        list.add(new MusicTrack("Orion", "Metallica", "Master of Puppets", "8:36"));
        list.add(new MusicTrack("Toxicity", "Metallica", "SOAD", "8:36"));
        list.add(new MusicTrack("The Islander", "Nightwish", "Doesn't matter", "5:17"));

        this.recyclerView = view.findViewById(R.id.list_view);
        recyclerView.setHasFixedSize(true);
        this.adapter = new MusicTrackAdapter(this.getContext(), list);
        this.layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void notifyDataChanges() {
        adapter.notifyDataSetChanged();
    }
}
