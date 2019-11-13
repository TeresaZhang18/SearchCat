package com.example.searchcat.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchcat.R;
import com.example.searchcat.model.Cat;
import com.example.searchcat.model.FakeDatabase;

import java.util.ArrayList;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {

    public ArrayList<Cat> favouritecat;

    public void setData (ArrayList<Cat> mycat) {
        favouritecat = mycat;
    }

    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_list, null, false);
        FavouriteViewHolder favouriteViewHolder = new FavouriteViewHolder(view);
        return  favouriteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteViewHolder holder, int position) {
        final Cat mycatposition = favouritecat.get(position);
        holder.catName.setText(mycatposition.getName());

    }

    @Override
    public int getItemCount() {
        return favouritecat.size();
    }

    public class FavouriteViewHolder extends RecyclerView.ViewHolder {
        public TextView catName;
        public View view;
        public FavouriteViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            catName = itemView.findViewById(R.id.catName_list);

        }
    }
}
