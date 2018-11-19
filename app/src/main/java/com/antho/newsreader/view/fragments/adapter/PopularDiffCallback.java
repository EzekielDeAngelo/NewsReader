package com.antho.newsreader.view.fragments.adapter;

import android.support.v7.util.DiffUtil;

import com.antho.newsreader.model.popular.Popular;

import java.util.List;

public class PopularDiffCallback extends DiffUtil.Callback {


    private final List<Popular> oldList;
    private final List<Popular> newList;

    public PopularDiffCallback(List<Popular> oldList, List<Popular> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).mediaThumbnail() == newList.get(newItemPosition).mediaThumbnail();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}

