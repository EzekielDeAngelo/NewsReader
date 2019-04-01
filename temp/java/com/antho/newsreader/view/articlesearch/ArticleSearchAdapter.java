package com.antho.newsreader.view.articlesearch;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.antho.newsreader.R;
import com.antho.newsreader.model.search.ArticleSearchNews;
import com.antho.newsreader.model.search.ArticleSearchNewsList;

import java.util.List;

public class ArticleSearchAdapter extends RecyclerView.Adapter<ArticleSearchAdapter.NewsViewHolder>
{
    Context context;
    List<ArticleSearchNews> newsList;

    //
    public ArticleSearchAdapter(Context context, ArticleSearchNewsList newsList)
    {

        this.context = context;
        this.newsList = newsList.results().get(0).results();
//        this.newsList.add(newsList.


    }
    //
    @NonNull
    @Override
    public ArticleSearchAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_layout, parent, false);
        return new NewsViewHolder(view);
    }
    //
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position)
    {
        final ArticleSearchNews news = newsList.get(position);

        //Thumbnail
        /*if (news.mediaThumbnail() != null)
        {
            if (news.mediaThumbnail().size() != 0)
            {
                Picasso.Builder builder = new Picasso.Builder(context);
                builder.downloader(new OkHttp3Downloader(context));
                builder.build().load(news.mediaThumbnail().get(0).mediaThumbnailList().get(0).thumbnailUrl())
                        .into(holder.thumbnail);
            }
        }*/
        //Section
        holder.title.setText(news.web_url());
        holder.section.setText(news.section());
        //holder.date.setText(news.source());
        //Date
       // holder.date.setText("" + news.date().charAt(8) + news.date().charAt(9) + "/"+ news.date().charAt(5) + news.date().charAt(6) + "/" + news.date().charAt(2) + news.date().charAt(3));
        //Title
        //holder.title.setText(news.title());
        /*holder.itemView.setOnClickListener(new View.OnClickListener()
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
*/
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