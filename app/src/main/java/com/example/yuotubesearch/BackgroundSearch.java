package com.example.yuotubesearch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.youtube.YouTube;

import android.util.Pair;
import android.widget.ArrayAdapter;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BackgroundSearch extends AsyncTask<Void, Void, Pair<List<SearchResult>,ArrayList<Bitmap>>> {
    private static final long NUMBER_OF_VIDEOS_RETURNED  = 5;
    private MainActivity mainActivity;
    private String query;

    public BackgroundSearch(MainActivity mainActivity,String query) {
        this.mainActivity = mainActivity;
        this.query = query;
    }


    @Override
    public  Pair<List<SearchResult>,ArrayList<Bitmap>> doInBackground(Void... params) {

            try {
                YouTube youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
                    public void initialize(HttpRequest request) throws IOException {
                    }
                }).setApplicationName("youtubesearch").build();

                YouTube.Search.List search = youtube.search().list("id,snippet");
                search.setKey(youTubeConfig.API_KEY);
                search.setQ(query);
                search.setType("video");
               // search.setFields("items(id/kind,id/videoId,snippet/title,snippet/publishedAt,snippet/thumbnails/default/url),nextPageToken");
                search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

                // Call the API and print results.
                SearchListResponse searchResponse = search.execute();
                List<SearchResult> searchResultList = searchResponse.getItems();
                ArrayList<Bitmap> bitmapList = new ArrayList<>();
                for (SearchResult result : searchResultList) {
                    String url = result.getSnippet().getThumbnails().getDefault().getUrl();
                    Bitmap bitmap = null;
                    try {
                        InputStream in = new java.net.URL(url).openStream();
                        bitmap = BitmapFactory.decodeStream(in);
                    } catch (Exception e) {
                        System.out.println(e+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        e.printStackTrace();
                    }
                    bitmapList.add(bitmap);
                }
                Pair<List<SearchResult>,ArrayList<Bitmap>> result = new Pair<>(searchResultList,bitmapList);

                    if (searchResultList != null) {
                    System.out.println("FINISHED!!!!!!!!!!!");
                    prettyPrint(searchResultList.iterator(), query);
                    return result;
                }
            } catch (GoogleJsonResponseException e) {
                System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                        + e.getDetails().getMessage()+" stack: ");
                e.printStackTrace();

            } catch (IOException e) {
                System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
            } catch (Throwable t) {
                t.printStackTrace();
            }
            return null;
        }

    protected void onPostExecute(Pair<List<SearchResult>,ArrayList<Bitmap>> result) {
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<String> imageURLList = new ArrayList<>();
        for (SearchResult videoRes : result.first){
            nameList.add(videoRes.getSnippet().getTitle());
            imageURLList.add(videoRes.getSnippet().getThumbnails().getDefault().getUrl());
        }

       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(mainActivity, R.layout.activity_list,R.id.textViewForSearch, nameList);
        ArrayAdapter<String> adapter2 = new OurListAdapter(mainActivity,R.layout.activity_list,R.id.textViewForSearch,nameList,imageURLList, result.second);
        mainActivity.setSearchResultsList(adapter2);
        //mainActivity.changeTextView("Success!");
    }


    private static void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {

        System.out.println("\n=============================================================");
        System.out.println(
                "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
        System.out.println("=============================================================\n");

        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }

        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();
            ResourceId rId = singleVideo.getId();

            // Confirm that the result represents a video. Otherwise, the
            // item will not contain a video ID.
            if (rId.getKind().equals("youtube#video")) {
                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();
                System.out.println(" Video Id: " + rId.getVideoId());
                System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
                System.out.println(" Thumbnail: " + thumbnail.getUrl());
                System.out.println("\n-------------------------------------------------------------\n");
            }
        }
    }
    }

