package com.example.appbar;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.annotation.Documented;
import java.util.ArrayList;

public class fragment_explore extends Fragment {
    private RecyclerView recyclerView;
    private ParseAdapter adapter;
    private ArrayList<ParseItem> parseItems = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        recyclerView = view.findViewById(R.id.snackbutton);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new ParseAdapter(parseItems, view.getContext());
        recyclerView.setAdapter(adapter);

        Content content = new Content();
        content.execute();

        return view;
    }


    private class Content extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = ("https://www.digitaltrends.com/movies/best-movies-on-netflix/");
                Document doc = Jsoup.connect(url).get();
                Elements data = doc.select("figure");
                Elements tdata = doc.select("h2");
                int size = data.size();
                Log.i("size", Integer.toString(size));
                for (int x = 0; x < size; x++) {
                    String imgurl = data.select("img").eq(x).attr("data-dt-lazy-src");
                    Log.i("imgurls", imgurl);
                    String title = tdata.select("a").eq(x).text();
                    Log.i("title", title);

                    parseItems.add(new ParseItem(imgurl, title));
                }

            } catch (IOException e) {
               Log.i("juejof","ieoub");
            }
            return null;
        }
    }


}



