package com.example.webview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.GithubViewHolder> {

    private Context context;
    private User[] data;

    public GithubAdapter(Context context, User[] data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public GithubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_user_layout, parent, false);
        return new GithubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GithubViewHolder holder, int position) {
        final User user = data[position];
        holder.textUser.setText(user.getLogin());
        Glide.with(holder.imgUser.getContext()).load(user.getAvatarUrl()).into(holder.imgUser);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, user.getId()+". "+user.getLogin() + " was clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.length;
    }

    public class GithubViewHolder extends RecyclerView.ViewHolder {

        ImageView imgUser;
        TextView textUser;

        public GithubViewHolder(View itemView) {
            super(itemView);

            imgUser = itemView.findViewById(R.id.imgUser);
            textUser = itemView.findViewById(R.id.textUser);
        }
    }

}

