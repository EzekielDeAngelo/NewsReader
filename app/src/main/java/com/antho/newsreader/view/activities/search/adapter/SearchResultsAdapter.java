package com.antho.newsreader.view.activities.search.adapter;
/** Search results adapter **/
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.antho.newsreader.R;
import com.antho.newsreader.model.search.Search;
import com.antho.newsreader.view.fragments.adapter.NewsAdapter;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/** Adapter for search results recyclerview **/
public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter
        .NewsViewHolder> {

private final NewsAdapter.OnStoryClickedListener listener;
private final List<Search> data = new ArrayList<>();

     public   SearchResultsAdapter(NewsAdapter.OnStoryClickedListener listener) {
        this.listener = listener;
        }


@NonNull
@Override
public SearchResultsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout,
        parent, false);
        return new SearchResultsAdapter.NewsViewHolder(itemView, listener);
        }

@Override
public void onBindViewHolder(@NonNull SearchResultsAdapter.NewsViewHolder holder, int position) {
        holder.bind(data.get(position));
        }

@Override
public int getItemCount() {
        return data.size();
        }

@Override
public long getItemId(int position) {
        return data.get(position).hashCode();
        }

public void setData(List<Search> stories) {
        if (stories != null) {       Log.d("azerty","a");
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new SearchDiffCallback

        (data, stories));
        data.clear();
        data.addAll(stories);
        diffResult.dispatchUpdatesTo(this);
        } else {
        data.clear();Log.d("ytrezaazerty","a");
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

    private Search searchDocument;

    NewsViewHolder(View itemView, NewsAdapter.OnStoryClickedListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(v -> listener.onItemClicked(searchDocument.webUrl()));


    }

    void bind(Search searchDocument) {
        this.searchDocument = searchDocument;
        titleText.setText(searchDocument.headline().mainHeadline());
        //dateText.setText(Utilities.getDateFromSearchDocument(searchDocument));
        sectionText.setText(searchDocument.sectionName());
    /*    RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_paper)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.NORMAL);
        Glide.with(newsImage.getContext())
                .load(Utilities.getImageFromUrlFromSearchDocument(searchDocument))
                .apply(options)
                .into(newsImage);*/
        Log.d("aze","aaaaaaaaaaaa");
        if (searchDocument.multimedia().size() > 0 )
        {Log.d("aze","eza");
            Picasso.Builder builder = new Picasso.Builder(newsImage.getContext());
            builder.downloader(new OkHttp3Downloader(newsImage.getContext()));
            builder.build().load(searchDocument.multimedia().get(0).imageUrl())
                    .into(newsImage);
        }
    }

}
}