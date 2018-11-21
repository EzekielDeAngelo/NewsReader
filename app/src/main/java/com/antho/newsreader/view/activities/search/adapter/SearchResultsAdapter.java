package com.antho.newsreader.view.activities.search.adapter;
/** Search results adapter **/
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.antho.newsreader.R;
import com.antho.newsreader.model.search.Search;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/** Adapter to create and populate recycler view for news from article search API  **/
public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.SearchViewHolder>
{
    private final OnStoryClickedListener listener;
    private final List<Search> data = new ArrayList<>();
    // Constructor
    public SearchResultsAdapter(OnStoryClickedListener listener)
    {
        this.listener = listener;
    }
    //  Creates view for recycler view with on click listener parameter
    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout, parent, false);
        return new SearchViewHolder(itemView, listener);
    }
    // Bind data to the view items
    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position)
    {
        holder.bind(data.get(position));
    }
    // Return the list size
    @Override
    public int getItemCount() { return data.size(); }
    // Return item id based on the position passed as parameter
    @Override
    public long getItemId(int position)
    {
        return data.get(position).hashCode();
    }
    // Add news list data if available
    public void setData(List<Search> stories)
    {
        if (stories != null)
        {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new SearchDiffCallback(data, stories));
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
    static final class SearchViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.thumbnail) ImageView newsImage;
        @BindView(R.id.section) TextView sectionText;
        @BindView(R.id.date) TextView dateText;
        @BindView(R.id.title) TextView titleText;
        private Search search;
        // Bind view and set on click listener
        SearchViewHolder(View itemView, OnStoryClickedListener listener)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> listener.onItemClicked(search.url(), search.section()));
        }
        // Bind data entries to appropriate view items
        void bind(Search search)
        {
            this.search = search;
            titleText.setText(search.headline().title());
            //dateText.setText(Utilities.getDateFromSearchDocument(searchDocument));
            sectionText.setText(search.section());
            if (search.multimedia().size() > 0)
            {
                Picasso.Builder builder = new Picasso.Builder(newsImage.getContext());
                builder.downloader(new OkHttp3Downloader(newsImage.getContext()));
                builder.build().load(search.multimedia().get(0).thumbnailUrl())
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