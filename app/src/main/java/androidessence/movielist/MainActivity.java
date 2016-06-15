/*
 * Copyright (c) 2016. Created by Pham Ngoc Thanh
 */

package androidessence.movielist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup RecyclerView
        RecyclerView movieRecyclerView = (RecyclerView) findViewById(R.id.movie_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        movieRecyclerView.setLayoutManager(linearLayoutManager);

        // Setup Adapter
        SwipeAdapter movieAdapter = new SwipeAdapter(this, getMovies());
        movieRecyclerView.setAdapter(movieAdapter);
//        movieRecyclerView.setOn

        // Setup ItemTouchHelper
        ItemTouchHelper.Callback callback = new MovieTouchHelper(movieAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(movieRecyclerView);
    }

    private List<ItemData> getMovies(){
        List<ItemData> movieList = new ArrayList<>();
        movieList.add(new ItemData("Harry Potter"));
        movieList.add(new ItemData("Twilight"));
        movieList.add(new ItemData("Star Wars"));
        movieList.add(new ItemData("Star Trek"));
        movieList.add(new ItemData("Galaxy Quest"));
        movieList.add(new ItemData("1"));
        movieList.add(new ItemData("2"));
        movieList.add(new ItemData("3"));
        movieList.add(new ItemData("4"));
        movieList.add(new ItemData("5"));
        return movieList;
    }
}
