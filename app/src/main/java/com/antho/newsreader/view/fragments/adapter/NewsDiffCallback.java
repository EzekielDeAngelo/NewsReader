package com.antho.newsreader.view.fragments.adapter;
/** News diff callback **/

import com.antho.newsreader.model.news.News;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;
/** Overrides diffUtil methods to improve software performance **/
public class NewsDiffCallback  extends DiffUtil.Callback
{
    private final List<News> oldList;
    private final List<News> newList;
    //
    public NewsDiffCallback(List<News> oldList, List<News> newList)
    {
        this.oldList = oldList;
        this.newList = newList;
    }
    //
    @Override
    public int getOldListSize()
    {
        return oldList.size();
    }
    //
    @Override
    public int getNewListSize()
    {
        return newList.size();
    }
    //
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition)
    {
        return oldList.get(oldItemPosition).url().equals(newList.get(newItemPosition).url());
    }
    //
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition)
    {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
