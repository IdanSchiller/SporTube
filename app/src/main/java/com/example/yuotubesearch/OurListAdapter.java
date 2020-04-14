package com.example.yuotubesearch;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;


public class OurListAdapter extends ArrayAdapter<String> {
        private final Context context;
        private final ArrayList<String> videoNames;
        private final List<String> imagesURLs;
        private ImageView imageView;
        private TextView textView;
        private boolean imageDownloadFinished = false;
        private final ArrayList<Bitmap> bitmapList;

    public OurListAdapter(Context context, int resource, int textViewResourceId, ArrayList<String> videoNames, List<String> imagesURLs, ArrayList<Bitmap> bitmapList) {
            super(context, resource,textViewResourceId, videoNames);
            this.context = context;
            this.videoNames = videoNames;
            this.imagesURLs = imagesURLs;
            this.bitmapList = bitmapList;
    }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.activity_list, parent, false);
            textView =  rowView.findViewById(R.id.textViewForSearch);
            imageView = rowView.findViewById(R.id.videoImageView);
            textView.setText(videoNames.get(position));
            imageView.setImageBitmap(bitmapList.get(position));
            //String url = imagesURLs.get(position);

//            DownloadImageTask imageTask = (DownloadImageTask) new DownloadImageTask(this,url,position).execute();
//            //imageTask.onPostExecute();
//            while (!imageDownloadFinished){}


//            try {
////              //  URL videoImageURL = new URL(imagesURLs.get(position));
////
////                InputStream in = new java.net.URL(imagesURLs.get(position)).openStream();
////                Bitmap bitmap = BitmapFactory.decodeStream(in);
////                imageView.setImageBitmap(bitmap);
////
//               // Bitmap bitmap = BitmapFactory.decodeStream((InputStream)(videoImageURL.getContent()));
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            return rowView;
        }


        void setImageView(Bitmap bitmap,int position){
            System.out.println("IMAGE SETTED "+bitmap.toString()+"\n");
            imageView.setImageBitmap(bitmap);
            textView.setText(videoNames.get(position));
            imageDownloadFinished=true;
        }
}
