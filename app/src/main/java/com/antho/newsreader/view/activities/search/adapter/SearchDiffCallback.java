package com.antho.newsreader.view.activities.search.adapter;

import android.support.v7.util.DiffUtil;

import com.antho.newsreader.model.search.Search;

import java.util.List;

public class SearchDiffCallback  extends DiffUtil.Callback {

    private final List<Search> oldList;
    private final List<Search> newList;

    public SearchDiffCallback(List<Search> oldList, List<Search> newList) {
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
        return oldList.get(oldItemPosition).webUrl() == newList.get(newItemPosition).webUrl();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }


}
