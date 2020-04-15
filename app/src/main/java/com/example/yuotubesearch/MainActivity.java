package com.example.yuotubesearch;

//import androidx.appcompat.app.AppCompatActivity;
//import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.view.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.api.services.youtube.YouTube;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private BackgroundSearch backgroundSearch;
    private ListView searchResultsList;
    private SearchView searchView;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    public void setSearchResultsList(ArrayAdapter resultsListAdapted) {
        this.searchResultsList.setAdapter(resultsListAdapted);
    }


    public void setTextView(String newText) {
        this.textView.setText(newText);
    }

//    public void searchYouTube(String query) {
//        backgroundSearch = new BackgroundSearch(this, query);
//        backgroundSearch.execute();
//    }


    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.activity);
        // SearchFragment searchFragment = new SearchFragment(this);
//        imageViewTest = findViewById(R.id.imageViewTest);
//        try {
//            URL url = new URL("https://www.freedigitalphotos.net/images/previews/duckling-10087357.jpg");
//            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)(url.getContent()));
//            imageViewTest.setImageBitmap(bitmap);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            System.out.println(e);
//            e.printStackTrace();
//        }


        // show The Image in a ImageView
        // new DownloadImageTask((ImageView) findViewById(R.id.imageViewTest))
        // .doInBackground("http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png");

//                .execute("https://www.freedigitalphotos.net/images/previews/duckling-10087357.jpg");

//     //   public void onClick(View v) {
//            startActivity(new Intent(this, IndexActivity.class));
//            finish();
//
//        }

//        searchView = findViewById(R.id.searchView);
//        searchResultsList = findViewById(R.id.searchResultListView);

        tabLayout =  findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Search"));
        tabLayout.addTab(tabLayout.newTab().setText("fuck"));
        tabLayout.addTab(tabLayout.newTab().setText("Gal"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final TabLayoutAdapter adapter = new TabLayoutAdapter(this, getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                searchYouTube(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });





    }
//    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
//        ImageView imageViewTest;
//
//        public DownloadImageTask(ImageView imageViewTest) {
//            this.imageViewTest = imageViewTest;
//        }
//
//        protected Bitmap doInBackground(String... urls) {
//            System.out.println("INSIDE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//            String urldisplay = urls[0];
//            Bitmap mIcon11 = null;
//            try {
//                InputStream in = new java.net.URL(urldisplay).openStream();
//                mIcon11 = BitmapFactory.decodeStream(in);
//            } catch (Exception e) {
////                    Log.e("Error", e.getMessage());
//                e.printStackTrace();
//            }
//           // onPostExecute(mIcon11);
//            return mIcon11;
//        }
//
//        protected void onPostExecute(Bitmap result) {
//            imageViewTest.setImageBitmap(result);
//        }
//    }

}
