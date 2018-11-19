package com.antho.newsreader.view.mostpopular;

/****/
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.antho.newsreader.R;
import com.antho.newsreader.model.popular.MostPopularNews;
import com.antho.newsreader.model.popular.MostPopularNewsList;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;
/****/
public class MostPopularAdapter extends RecyclerView.Adapter<MostPopularAdapter.NewsViewHolder>
{
    Context context;
    List<MostPopularNews> newsList;
    //
    public MostPopularAdapter(Context context, MostPopularNewsList newsList)
    {
        this.context = context;
        this.newsList = newsList.results();
    }
    //
    @NonNull
    @Override
    public MostPopularAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_layout, parent, false);
        return new NewsViewHolder(view);
    }
    //
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position)
    {
        final MostPopularNews news = newsList.get(position);
        //Thumbnail
        if (news.mediaThumbnail() != null)
        {
            if (news.mediaThumbnail().size() != 0)
            {
                Picasso.Builder builder = new Picasso.Builder(context);
                builder.downloader(new OkHttp3Downloader(context));
                builder.build().load(news.mediaThumbnail().get(0).mediaThumbnailList().get(0).thumbnailUrl())
                        .into(holder.thumbnail);
            }
        }
        //Section
        holder.section.setText(news.section());
        //Date
        holder.date.setText("" + news.date().charAt(8) + news.date().charAt(9) + "/"+ news.date().charAt(5) + news.date().charAt(6) + "/" + news.date().charAt(2) + news.date().charAt(3));
        //Title
        holder.title.setText(news.title());
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Uri webpage = Uri.parse(news.url());
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent);
                }
            }
        });

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