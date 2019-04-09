package com.antho.newsreader.view.fragments.adapter;
import com.antho.newsreader.model.popular.Popular;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;
/** Overrides diffUtil methods to improve software performance **/
class PopularDiffCallback extends DiffUtil.Callback
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

