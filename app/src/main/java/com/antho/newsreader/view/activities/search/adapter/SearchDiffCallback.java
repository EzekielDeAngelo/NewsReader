package com.antho.newsreader.view.activities.search.adapter;
import com.antho.newsreader.model.search.Search;

import java.util.List;
import java.util.Objects;

import androidx.recyclerview.widget.DiffUtil;
/** Overrides diffUtil methods to improve software performance **/
class SearchDiffCallback  extends DiffUtil.Callback
{
    private final List<Search> oldList;
    private final List<Search> newList;
    // Constructor
    public SearchDiffCallback(List<Search> oldList, List<Search> newList)
    {
        this.oldList = oldList;
        this.newList = newList;
    }
    @Override
    public int getOldListSize()
    {
        return oldList.size();
    }
    @Override
    public int getNewListSize()
    {
        return newList.size();
    }
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition)
    {
        return Objects.equals(oldList.get(oldItemPosition).url(), newList.get(newItemPosition).url());
    }
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition)
    {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
