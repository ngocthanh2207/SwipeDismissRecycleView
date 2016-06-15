/*
 * Copyright (c) 2016. Created by Pham Ngoc Thanh
 */

package androidessence.movielist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class SwipeAdapter extends RecyclerView.Adapter<SwipeAdapter.ViewHolder> {
    public interface SwipeOptionListener{
        void onClose(int position);
        void onSelect(int position);
    }

    private SwipeOptionListener swipeOptionListener;
    public void setSwipeOptionListener(SwipeOptionListener mSwipeOptionListener){
        swipeOptionListener = mSwipeOptionListener;
    }

    private Context mContext;
    private List<ItemData> mMovies;

    public SwipeAdapter(Context context, List<ItemData> movies){
        this.mContext = context;
        this.mMovies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindMovie(mMovies.get(position));
        holder.setPosition(position);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public void remove(int position) {
        mMovies.remove(position);
        notifyItemRemoved(position);
    }

    public void swap(int firstPosition, int secondPosition){
        Collections.swap(mMovies, firstPosition, secondPosition);
        notifyItemMoved(firstPosition, secondPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView movieNameTextView;
        public final Button buttonClose;
        public int position;

        public ViewHolder(View view){
            super(view);
            movieNameTextView = (TextView) view.findViewById(R.id.movie_name);
            buttonClose = (Button) view.findViewById(R.id.buttonClose);
            buttonClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(swipeOptionListener != null){
                        swipeOptionListener.onClose(position);
                    }
                    Toast.makeText(mContext, "on close " + position, Toast.LENGTH_SHORT).show();
                }
            });

            movieNameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(swipeOptionListener != null){
                        swipeOptionListener.onSelect(position);
                    }
                    Toast.makeText(mContext, "on Select + " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void bindMovie(ItemData movie){
            this.movieNameTextView.setText(movie.getName());
        }

        public void setPosition(int mPosition){
            position = mPosition;
        }
    }
}
