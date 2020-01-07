package com.jsonfeed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.partyplanner.jsonfeed.R;

import java.util.ArrayList;
import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedItemHolder> {


    private List<FeedItem> mDataset;
    private Context context;

    @Override
    public FeedItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View feedListView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_item, parent, false);
        FeedItemHolder feedItem = new FeedItemHolder(feedListView);
        return feedItem;
    }

    public FeedAdapter(List<FeedItem> myDataset, Context context) {

        mDataset = new ArrayList<FeedItem>();
        for (FeedItem item : myDataset) {
            if (item.getTitle() != null || item.getImageHref() != null || item.getDescription() != null)
                mDataset.add(item);
        }
    }

    @Override
    public void onBindViewHolder(FeedItemHolder feedItemHolder, int position) {
        feedItemHolder.tittle.setText(mDataset.get(position).getTitle());
        feedItemHolder.description.setText(mDataset.get(position).getDescription());
        ImageLoader imageLoader = FeedRequestQueue.getInstance(context).getImageLoader();

        String url = mDataset.get(position).getImageHref();

        feedItemHolder.feedImage.setDefaultImageResId(R.drawable.ic_default_image);
        if (url != null)
            feedItemHolder.feedImage.setImageUrl(url, imageLoader);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
