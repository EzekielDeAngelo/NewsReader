package com.antho.newsreader.view.fragments.adapter;
/** Popular adapter**/
import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.antho.newsreader.R;
import com.antho.newsreader.model.popular.Popular;
import com.antho.newsreader.viewmodel.PopularViewModel;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/** Adapter to create and populate recycler view for news from most popular API **/
public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder>
{
    private final List<Popular> data = new ArrayList<>();
    private final OnStoryClickedListener listener;
    // Constructor
    public PopularAdapter(PopularViewModel viewModel, LifecycleOwner owner, OnStoryClickedListener listener)
    {
        viewModel.getPopularNews().observe(owner, this::setData);
        this.listener = listener;
    }
    // Creates view for recycler view with on click listener parameter
    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout, parent, false);
        return new PopularViewHolder(itemView, listener);
    }
    // Bind data to the view items
    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position)
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
    public void setData(List<Popular> stories)
    {
        if (stories != null)
        {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new PopularDiffCallback(data, stories));
            data.clear();
            data.addAll(stories);
            diffResult.dispatchUpdatesTo(this);
        }
        else
        {
            data.clear();
            notifyDataSetChanged();
        }
    }
    // Provides a reference to the views for each data item
    static final class PopularViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.thumbnail) ImageView newsImage;
        @BindView(R.id.section) TextView sectionText;
        @BindView(R.id.date) TextView dateText;
        @BindView(R.id.title) TextView titleText;
        private Popular popular;
        // Bind view and set on click listener
        PopularViewHolder(View itemView, OnStoryClickedListener listener)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> listener.onItemClicked(popular.url(), popular.section()));
        }
        // Bind data entries to appropriate view items
        void bind(Popular popular)
        {
            this.popular = popular;
            titleText.setText(popular.title());
            //dateText.setText(Utilities.getDateFromPopularStory(popularStory));
            sectionText.setText(popular.section());
            if (popular.multimedia().size() > 0 )
            {
                Picasso.Builder builder = new Picasso.Builder(newsImage.getContext());
                builder.downloader(new OkHttp3Downloader(newsImage.getContext()));
                builder.build().load(popular.multimedia().get(0).media().get(0).thumbnailUrl())
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