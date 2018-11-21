package com.antho.newsreader.view.fragments.adapter;
/** Popular diff callback **/
import android.support.v7.util.DiffUtil;

import com.antho.newsreader.model.popular.Popular;

import java.util.List;
/** Overrides diffUtil methods to improve software performance **/
public class PopularDiffCallback extends DiffUtil.Callback
{
    private final List<Popular> oldList;
    private final List<Popular> newList;
    //
    public PopularDiffCallback(List<Popular> oldList, List<Popular> newList)
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
        return oldList.get(oldItemPosition).multimedia() == newList.get(newItemPosition).multimedia();
    }
    //
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition)
    {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}

