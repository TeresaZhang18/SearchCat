package com.example.searchcat.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.searchcat.R;
import com.example.searchcat.activities.CatDetailActivity;
import com.example.searchcat.fragments.SearchFragment;
import com.example.searchcat.model.Cat;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    public List<Cat> catToAdapt;

    public void setData(List<Cat> catToAdapt) {
        this.catToAdapt = catToAdapt;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list, null, false);
        SearchViewHolder searchViewHolder = new SearchViewHolder(view);
        return  searchViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        final Cat catPosition = catToAdapt.get(position);
        holder.name.setText(catPosition.getName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();
                Intent intent = new Intent(context, CatDetailActivity.class);
                intent.putExtra("CatID", catPosition.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return catToAdapt.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView name;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            name = itemView.findViewById(R.id.catName_searchlist);
        }
    }


}
