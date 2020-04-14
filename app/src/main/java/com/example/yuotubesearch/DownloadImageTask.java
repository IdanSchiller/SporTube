package com.example.yuotubesearch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;

public class DownloadImageTask extends AsyncTask<Void, Void, Bitmap> {
      //  private ImageView imageViewTest;
        private OurListAdapter adapter;
        private String url;
        private  int position;

        public DownloadImageTask(OurListAdapter adapter,String url,int position) {
          //  this.imageViewTest = imageViewTest;
            this.adapter=adapter;
            this.url = url;
            this.position=position;
        }

        protected Bitmap doInBackground(Void... params) {
            String urlDisplay = url;
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urlDisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                System.out.println(e+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//                    Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
           // onPostExecute(mIcon11);
            System.out.println("finished foinbackground\n");
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            System.out.println("started onPost\n");
            adapter.setImageView(result,position);
           // imageViewTest.setImageBitmap(result);
        }
    }


