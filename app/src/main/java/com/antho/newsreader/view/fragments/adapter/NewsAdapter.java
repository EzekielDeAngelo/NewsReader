package com.antho.newsreader.view.fragments.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.antho.newsreader.R;
import com.antho.newsreader.model.news.News;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private final OnStoryClickedListener listener;
    private final List<News> data = new ArrayList<>();

    public NewsAdapter(OnStoryClickedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout,
                parent, false);
        return new NewsViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<News> stories) {
        if (stories != null) {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new NewsDiffCallback(data,
                    stories));
            data.clear();
            data.addAll(stories);
            diffResult.dispatchUpdatesTo(this);
        } else {
            data.clear();
            notifyDataSetChanged();
        }

    }

    static final class NewsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.thumbnail)
        ImageView newsImage;
        @BindView(R.id.section)
        TextView sectionText;
        @BindView(R.id.date) TextView dateText;
        @BindView(R.id.title) TextView titleText;

        private News story;

        NewsViewHolder(View itemView, OnStoryClickedListener listener)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> listener.onItemClicked(story.url()));
        }

        void bind(News story)
        {
            this.story = story;
            titleText.setText(story.title());
            //dateText.setText(Utilities.getDateFromStory(story));
            sectionText.setText(story.section());
            // Use Glide to load image
            if (story.thumbnail().size() > 0 )
            {
                Picasso.Builder builder = new Picasso.Builder(newsImage.getContext());
                builder.downloader(new OkHttp3Downloader(newsImage.getContext()));
                builder.build().load(story.thumbnail().get(0).thumbnailUrl())
                        .into(newsImage);
            }
  /*          RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.ic_paper)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.NORMAL);
            Glide.with(newsImage.getContext())
                    .load(Utilities.getImageUrl(story))
                    .apply(options)
                    .into(newsImage);*/
        }

    }

    public interface OnStoryClickedListener
    {
        void onItemClicked(String url);
    }
}
