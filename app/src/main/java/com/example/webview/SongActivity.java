package com.example.webview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SongActivity extends MainActivity {

    private static final String URL= "http://starlord.hackerearth.com/studio";
    RecyclerView songListfull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        songListfull=findViewById(R.id.songListfull);
        songListfull.setLayoutManager(new LinearLayoutManager(this));

        StringRequest request=new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("CODE",response);
                GsonBuilder gsonBuilder=new GsonBuilder();
                Gson gson=gsonBuilder.create();
                Song[] songs=gson.fromJson(response,Song[].class);
                songListfull.setAdapter(new SongAdapter(SongActivity.this,songs));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SongActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);
    }
}
