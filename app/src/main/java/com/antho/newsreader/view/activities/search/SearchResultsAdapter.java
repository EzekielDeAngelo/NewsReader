package com.antho.newsreader.view.activities.search;
/** Search results adapter **/
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.antho.newsreader.R;
import com.antho.newsreader.model.articlesearch.Doc;
import com.antho.newsreader.model.articlesearch.Response;

import java.util.List;
/** Adapter for search results recyclerview **/
public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.NewsViewHolder>
{
    Context context;
    List<Doc> newsList;
    //
    public SearchResultsAdapter(Context context, Response newsList)
    {
        this.context = context;
        this.newsList = newsList.docs();
    }
    //
    @NonNull
    @Override
    public SearchResultsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_layout, parent, false);
        return new NewsViewHolder(view);
    }
    //
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position)
    {
        final Doc news = newsList.get(position);
        holder.section.setText(news.headline().mainHeadline());
        holder.title.setText(news.headline().printHeadline());
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