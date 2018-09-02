package com.antho.newsreader.view;
/****/
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.antho.newsreader.model.News;
import com.antho.newsreader.model.NewsListJsonModel;
import com.antho.newsreader.R;
import com.bumptech.glide.Glide;

import java.util.List;
/****/
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>
{
    Context mCtx;
    List<News> newsList;
    //
    public NewsAdapter(Context mCtx, NewsListJsonModel newsList)
    {
        this.mCtx = mCtx;
        this.newsList = newsList.results();
    }
    //
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_layout, parent, false);
        return new NewsViewHolder(view);
    }
    //
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position)
    {
        News news = newsList.get(position);
        /*Glide.with(mCtx)
                .load(news.getThumbnailUrl())
                .into(holder.imageView);*/
        holder.textView.setText(news.title());
    }
    //
    @Override
    public int getItemCount()
    {
        return newsList.size();
    }
    //
    class NewsViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView textView;

        public NewsViewHolder(View itemView)
        {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}