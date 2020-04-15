package com.example.yuotubesearch;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

public class SearchFragment extends ListFragment {
    private SearchView searchView;
    private BackgroundSearch backgroundSearch;
    private MainActivity mainActivity;
    private ListView searchResultsList;
    private Context context;



    public SearchFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
          View view = inflater.inflate(R.layout.fragment_search, container, false);
          searchView = view.findViewById(R.id.searchView);
     //     searchResultsList = view.findViewById(R.id.searchResultListView);
        if(this.isAdded())
            context = this.getActivity();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchYouTube(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return view;
    }
    public void searchYouTube(String query) {
        backgroundSearch = new BackgroundSearch(context,query,searchResultsList,this);
        backgroundSearch.execute();
    }
    public void setSearchResultsList(ArrayAdapter resultsListAdapted) {
        this.searchResultsList.setAdapter(resultsListAdapted);
    }


}