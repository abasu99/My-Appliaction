package com.example.webview;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GithubUsers extends MainActivity {

    private static final String URL= "https://api.github.com/users";
    RecyclerView userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_users);
        userList=findViewById(R.id.userList);
        userList.setLayoutManager(new LinearLayoutManager(this));

        StringRequest request=new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("CODE",response);
                GsonBuilder gsonBuilder=new GsonBuilder();
                Gson gson=gsonBuilder.create();
                User[] users=gson.fromJson(response,User[].class);
                userList.setAdapter(new GithubAdapter(GithubUsers.this,users));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(GithubUsers.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);

    }
}
