package com.example.yuotubesearch;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    private Button push_me;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
          View view =      inflater.inflate(R.layout.fragment_home, container, false);
        push_me = view.findViewById(R.id.push_me);
        push_me.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

            }

        });

        return view;
    }

}