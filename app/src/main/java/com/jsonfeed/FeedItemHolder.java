package com.jsonfeed;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.partyplanner.jsonfeed.R;

public class FeedItemHolder extends RecyclerView.ViewHolder {
    TextView tittle;
    TextView description;
    NetworkImageView feedImage;

    public FeedItemHolder(View view) {
        super(view);
        tittle = (TextView) view.findViewById(R.id.feed_title);
        description = (TextView) view.findViewById(R.id.feedDescription);
        feedImage = (NetworkImageView) view.findViewById(R.id.icon);
    }

}
