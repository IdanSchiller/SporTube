package com.example.yuotubesearch;

import android.os.AsyncTask;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.youtube.YouTube;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BackgroundSearch extends AsyncTask<Void, Void, List<SearchResult>> {
    private static final long NUMBER_OF_VIDEOS_RETURNED  = 25;
    private MainActivity mainActivity;
    private String query;

    public BackgroundSearch(MainActivity mainActivity,String query) {
        this.mainActivity = mainActivity;
        this.query = query;
    }


    @Override
    public List<SearchResult> doInBackground(Void... params) {

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
                if (searchResultList != null) {
                    System.out.println("FINISHED!!!!!!!!!!!");
                    prettyPrint(searchResultList.iterator(), query);
                    return searchResultList;
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

    protected void onPostExecute(List<SearchResult> resultList) {
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<String> imageURLList = new ArrayList<>();
        for (SearchResult result : resultList){
            nameList.add(result.getSnippet().getTitle());
            imageURLList.add(result.getSnippet().getThumbnails().getDefault().getUrl());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mainActivity, R.layout.activity_list,R.id.textViewForSearch, nameList);
        ArrayAdapter<String> adapter2 = new OurListAdapter(mainActivity,R.layout.activity_list,R.id.textViewForSearch,nameList,imageURLList);
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

