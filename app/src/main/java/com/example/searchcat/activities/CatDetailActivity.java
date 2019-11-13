package com.example.searchcat.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.searchcat.R;
import com.example.searchcat.model.Cat;
import com.example.searchcat.model.CatBreed;
import com.example.searchcat.model.FakeDatabase;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;


import com.bumptech.glide.Glide;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CatDetailActivity extends AppCompatActivity {

    public TextView catName;
    public TextView catDesc;
    public ImageView catImage;
    public TextView temperament;
    public TextView origin;
    public TextView lifeSpan;
    public TextView friendLevel;
    public TextView link;
    public ImageButton favourite;
    public TextView imperial;
    public TextView metric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_detail);


        catName = findViewById(R.id.catName);
        catDesc = findViewById(R.id.catDesc);
        catImage = findViewById(R.id.catImage);
        temperament = findViewById(R.id.temperament);
        origin = findViewById(R.id.origin);
        lifeSpan = findViewById(R.id.lifeSpan);
        friendLevel = findViewById(R.id.level);
        link = findViewById(R.id.link);
        favourite = findViewById(R.id.favouriteButton);
        imperial = findViewById(R.id.imperial);
        metric = findViewById(R.id.metric);

        Intent intent = getIntent();
        String catID = intent.getStringExtra("CatID");
        Cat cat = FakeDatabase.getCatByID(catID);

        catName.setText(cat.getName());
        catDesc.setText(cat.getDescription());
        temperament.setText(cat.getTemperament());
        origin.setText(cat.getOrigin());
        lifeSpan.setText(cat.getLife_span());
        friendLevel.setText(cat.getDog_friendly());
        link.setText(cat.getWikipedia_url());
        imperial.setText(cat.getWeight().imperial);
        metric.setText(cat.getWeight().metric);

        final String breedid = cat.getId();

        String url = "https://api.thecatapi.com/v1/images/search?breed_ids=" + breedid;

        final RequestQueue requestQueue =  Volley.newRequestQueue(getApplicationContext());

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                CatBreed[] catBreed = gson.fromJson(response, CatBreed[].class);
                List<CatBreed> mycat = Arrays.asList(catBreed);
                System.out.println(mycat.get(0));
                FakeDatabase.saveImage(breedid, mycat);
                CatBreed mycatBreed = FakeDatabase.getimagebyid(breedid);

                String imageurl = mycatBreed.getImageurl();
                Glide.with(getApplicationContext()).load(imageurl).into(catImage);

            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestQueue.stop();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,responseListener,errorListener);
        requestQueue.add(stringRequest);

        CatBreed catBreed = FakeDatabase.getimagebyid(breedid);
        System.out.println(catBreed);


        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mycatname = catName.getText().toString();
                FakeDatabase.favouritecats.add(new Cat(mycatname));

            }
        });
    }


}
