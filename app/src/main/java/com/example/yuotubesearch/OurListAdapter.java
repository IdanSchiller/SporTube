package com.example.yuotubesearch;

import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;

import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class OurListAdapter extends ArrayAdapter<String> {
        private final Context context;
        private final ArrayList<String> videoNames;
        private final List<String> imagesURLs;

    public OurListAdapter(Context context, int resource,int textViewResourceId, ArrayList<String> videoNames, List<String> imagesURLs) {
            super(context, resource,textViewResourceId, videoNames);
            this.context = context;
            this.videoNames = videoNames;
            this.imagesURLs = imagesURLs;
    }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.activity_list, parent, false);
            TextView textView =  rowView.findViewById(R.id.textViewForSearch);
            ImageView imageView = rowView.findViewById(R.id.videoImageView);
            textView.setText(videoNames.get(position));
            try {
                URL videoImageURL = new URL(imagesURLs.get(position));
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream)(videoImageURL.getContent()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return rowView;
        }
}
