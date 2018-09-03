package com.antho.newsreader.view;
/****/
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.antho.newsreader.model.News;
import com.antho.newsreader.model.NewsListJsonModel;
import com.antho.newsreader.R;
import com.bumptech.glide.Glide;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/****/
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>
{
    Context context;
    List<News> newsList;

    //
    public NewsAdapter(Context context, NewsListJsonModel newsList)
    {
        this.context = context;
        this.newsList = newsList.results();
    }
    //
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_layout, parent, false);
        return new NewsViewHolder(view);
    }
    //
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position)
    {
        News news = newsList.get(position);
        //Thumbnail
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(news.thumbnail().get(0).thumbnailUrl())
            .placeholder((R.drawable.ic_launcher_background))
            .error(R.drawable.ic_launcher_background)
            .into(holder.thumbnail);

      /*  Glide.with(context)
                .load(news.thumbnail().get(position).thumbnailUrl())
                .into(holder.thumbnail);*/
        //Section
        if (news.subsection() == "")
        {
            holder.section.setText(news.section());
        }
        else
        {
            holder.section.setText(news.section() + " > " + news.subsection());
        }
        //Date
        holder.date.setText(news.date().getDayOfMonth() + "/" + news.date().getMonthValue() + "/" + news.date().getYear() % 100);
        //Title
        holder.title.setText(news.title());
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
        public final View mView;
        ImageView thumbnail;
        TextView section;
        TextView date;
        TextView title;


        public NewsViewHolder(View itemView)
        {
            super(itemView);
            mView = itemView;
            thumbnail = itemView.findViewById(R.id.thumbnail);
            section = itemView.findViewById(R.id.section);
            date = itemView.findViewById(R.id.date);
            title = itemView.findViewById(R.id.title);
        }
    }
}