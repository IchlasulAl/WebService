package com.example.webservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

public class AkademikAdapter extends RecyclerView.Adapter<AkademikAdapter.ViewHolder> {

    private ArrayList<HashMap<String,String>> postList;
    private MainActivity activity;

    public AkademikAdapter(ArrayList<HashMap<String,String>> postList,MainActivity mainActivity){
        this.postList=postList;
        this.activity=mainActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup,int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_akademik,viewGroup,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder,final int position){
        final HashMap<String,String> post = postList.get(position);
        Glide.with(activity).load(post.get("img_url")).into(viewHolder.imgLogo);
        viewHolder.textSingkatan.setText(post.get("singkatan"));
        viewHolder.textNama.setText(post.get("nama"));
        viewHolder.textUrl.setText(post.get("url"));
    }
    @Override
    public int getItemCount(){
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgLogo;
        TextView textSingkatan;
        TextView textNama;
        TextView textUrl;

        public ViewHolder(View view){
            super(view);
            imgLogo = (ImageView) view.findViewById(R.id.imgLogo);
            textSingkatan = (TextView) view.findViewById(R.id.textSingkatan);
            textNama = (TextView) view.findViewById(R.id.textNama);
            textUrl = (TextView) view.findViewById(R.id.textUrl);
        }

    }
}
