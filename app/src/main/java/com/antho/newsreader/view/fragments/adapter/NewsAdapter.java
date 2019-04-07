package com.antho.newsreader.view.fragments.adapter;
/** News adapter**/

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.antho.newsreader.R;
import com.antho.newsreader.model.news.News;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
/** Adapter to create and populate recycler view for news from top stories API **/
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>
{
    private final OnStoryClickedListener listener;
    private final List<News> data = new ArrayList<>();
    // Constructor
    public NewsAdapter (OnStoryClickedListener listener) {
        this.listener = listener;
    }
    // Creates view for recycler view with on click listener parameter
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout, parent, false);
        return new NewsViewHolder(itemView, listener);
    }
    // Bind data to the view items
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position)
    {
        holder.bind(data.get(position));
    }
    // Return the list size
    @Override
    public int getItemCount()
    {
        return data.size();
    }
    // Add news list data if available
    public void setData(List<News> newsList)
    {
        if (newsList != null)
        {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new NewsDiffCallback(data, newsList));
            data.clear();
            data.addAll(newsList);
            diffResult.dispatchUpdatesTo(this);
        } else
        {
            data.clear();
            notifyDataSetChanged();
        }
    }
    // Provides a reference to the views for each data item
    static final class NewsViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.thumbnail) ImageView newsImage;
        @BindView(R.id.section) TextView sectionText;
        @BindView(R.id.date) TextView dateText;
        @BindView(R.id.title) TextView titleText;
        private News news;
        // Bind view and set on click listener
        NewsViewHolder(View itemView, OnStoryClickedListener listener)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> listener.onItemClicked(news.url(), news.section()));
        }
        // Bind data entries to appropriate view items
        void bind(News news)
        {
            this.news = news;

            titleText.setText(news.title());
            dateText.setText(DateTimeFormatter.ofPattern("dd-MM").format(news.date()));
            sectionText.setText(news.section());
            if (news.multimedia().size() > 0 )
            {
                Picasso.Builder builder = new Picasso.Builder(newsImage.getContext());
                builder.downloader(new OkHttp3Downloader(newsImage.getContext()));
                builder.build().load(news.multimedia().get(0).thumbnailUrl())
                        .into(newsImage);
            }
        }
    }
    // On click listener with url and section as parameters to display news in webview
    public interface OnStoryClickedListener
    {
        void onItemClicked(String url, String section);
    }
}
