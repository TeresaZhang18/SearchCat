package com.example.searchcat.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.searchcat.R;
import com.example.searchcat.adapter.SearchAdapter;
import com.example.searchcat.model.Cat;
import com.example.searchcat.model.FakeDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    public RecyclerView recyclerView;
    public ImageButton search;
    public EditText input;
    public String q;

    public List<Cat> catToAdapt;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.searchrecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        search = view.findViewById(R.id.searchbutton);
        input = view.findViewById(R.id.inputfield);

        search.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                q = input.getText().toString();

                final SearchAdapter searchAdapter = new SearchAdapter();

                final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                String url = "https://api.thecatapi.com/v1/breeds/search?q="+q;

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Gson gson = new Gson();

                        Cat[] cat = gson.fromJson(response, Cat[].class);
                        List<Cat> mycat = Arrays.asList(cat);
                        FakeDatabase.saveCatsToFakedb(mycat);
                        searchAdapter.setData(mycat);
                        recyclerView.setAdapter(searchAdapter);
                        requestQueue.stop();
                    }
                };

                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        requestQueue.stop();
                    }
                };


                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
                requestQueue.add(stringRequest);


            }
        });

        return view;
    }
}
