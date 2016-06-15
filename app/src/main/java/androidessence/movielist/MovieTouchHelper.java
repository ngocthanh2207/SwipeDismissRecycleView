/*
 * Copyright (c) 2016. Created by Pham Ngoc Thanh
 */

package androidessence.movielist;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class MovieTouchHelper extends ItemTouchHelper.SimpleCallback {
    private SwipeAdapter mMovieAdapter;

    public MovieTouchHelper(SwipeAdapter movieAdapter){
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.mMovieAdapter = movieAdapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mMovieAdapter.swap(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mMovieAdapter.remove(viewHolder.getAdapterPosition());
    }
}
