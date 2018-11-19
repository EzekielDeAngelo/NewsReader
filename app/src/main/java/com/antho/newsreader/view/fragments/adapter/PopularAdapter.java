package com.antho.newsreader.view.fragments.adapter;

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

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {

    private final List<Popular> data = new ArrayList<>();
    private final OnStoryClickedListener listener;
    /*PopularAdapter(PopularViewModel viewModel, LifecycleOwner owner) {
        viewModel.getPopularStories().observe(owner, this::setData);
    }*/

    /*public PopularAdapter(OnStoryClickedListener listener) {
        this.listener = listener;
    }*/
    public PopularAdapter(PopularViewModel viewModel, LifecycleOwner owner, OnStoryClickedListener listener) {
        viewModel.getPopularStories().observe(owner, this::setData);
        this.listener = listener;

    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout,
                parent, false);
        return new PopularViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /*@Override
    public long getItemId(int position) {
        return data.get(position).hashCode();
    }*/

    public void setData(List<Popular> stories) {

        if (stories != null) {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new PopularDiffCallback(data,
                            stories));
            data.clear();
            data.addAll(stories);
            diffResult.dispatchUpdatesTo(this);
        } else {
            data.clear();
            notifyDataSetChanged();
        }

    }

    static final class PopularViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.thumbnail)
        ImageView newsImage;
        @BindView(R.id.section)
        TextView sectionText;
        @BindView(R.id.date) TextView dateText;
        @BindView(R.id.title) TextView titleText;

        private Popular popularStory;

        PopularViewHolder(View itemView, OnStoryClickedListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> listener.onItemClicked(popularStory.url()));
            //itemView.setOnClickListener(view -> listener.onItemClicked(popularStory.url()));


        }

        void bind(Popular popularStory) {
            this.popularStory = popularStory;
            titleText.setText(popularStory.title());
//            dateText.setText(Utilities.getDateFromPopularStory(popularStory));
            sectionText.setText(popularStory.section());
      /*      RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.ic_paper)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.NORMAL);
            Glide.with(newsImage.getContext())
                    .load(Utilities.getImageUrlFromPopularStory(popularStory))
                    .apply(options)
                    .into(newsImage);*/


        // Use Glide to load image
            if (popularStory.mediaThumbnail().size() > 0 )
        {
            Picasso.Builder builder = new Picasso.Builder(newsImage.getContext());
            builder.downloader(new OkHttp3Downloader(newsImage.getContext()));
            builder.build().load(popularStory.mediaThumbnail().get(0).mediaThumbnailList().get(0).thumbnailUrl())
                    .into(newsImage);
        }

    }
}
    public interface OnStoryClickedListener
    {
        void onItemClicked(String url);
    }
}