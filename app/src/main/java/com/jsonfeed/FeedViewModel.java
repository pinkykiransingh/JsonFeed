package com.jsonfeed;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

public class FeedViewModel extends ViewModel {
    String url = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json";
    Gson gson = new Gson();

    private MutableLiveData<Feed> items;

    public LiveData<Feed> getFeeds(Context context) {
        if (items == null) {
            items = new MutableLiveData<Feed>();
            refreshFeed(context);
        }
        return items;
    }

    public void refreshFeed(Context context) {
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
                        Feed feed = gson.fromJson(response, Feed.class);
                        items.setValue(feed);
                }, error->{}
        );
        FeedRequestQueue.getInstance(context).addToRequestQueue(request);
    }
}
