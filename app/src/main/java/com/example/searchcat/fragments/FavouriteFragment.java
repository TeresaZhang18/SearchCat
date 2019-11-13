package com.example.searchcat.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.searchcat.R;
import com.example.searchcat.adapter.FavouriteAdapter;
import com.example.searchcat.model.FakeDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteFragment extends Fragment {

    public RecyclerView recyclerView;

    public FavouriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        recyclerView = view.findViewById(R.id.favouriteview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        FavouriteAdapter favouriteAdapter = new FavouriteAdapter();
        favouriteAdapter.setData(FakeDatabase.getallfavourite());
        recyclerView.setAdapter(favouriteAdapter);
        return view;
    }

}
