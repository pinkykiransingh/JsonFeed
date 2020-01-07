package com.jsonfeed;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.partyplanner.jsonfeed.R;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ;

    FeedViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        final ListView list = findViewById(R.id.my_recycler_view);
        model = ViewModelProviders.of(this).get(FeedViewModel.class);
        model.getFeeds(this.getApplicationContext()).observe(this, feed -> {


            RecyclerView mRecyclerView;
            RecyclerView.Adapter mAdapter;

            RecyclerView.LayoutManager mLayoutManager;

            getSupportActionBar().setTitle(feed.title);

            mRecyclerView = findViewById(R.id.my_recycler_view);
            mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new FeedAdapter(Arrays.asList(feed.rows), getApplicationContext());
            mRecyclerView.setAdapter(mAdapter);
            RecyclerView.ItemDecoration itemDecoration =
                    new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
            if (mRecyclerView.getItemDecorationCount() == 0)
                mRecyclerView.addItemDecoration(itemDecoration);

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            model.refreshFeed(this.getApplicationContext());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
