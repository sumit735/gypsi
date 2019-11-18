package com.example.gypsi.fragment;


import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.gypsi.R;
import com.example.gypsi.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlogFragment extends Fragment {
    String about;
    // TextView textView;
    Context context;

    boolean returnValue = false;
    TextView noInternetTextView;

    RelativeLayout noInternet;

    boolean isNetwork;

    public BlogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    

        final View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        context = getActivity();



        view.setFocusableInTouchMode(true);
        view.requestFocus();


        setHasOptionsMenu(true);
        return view;

    }


    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
//        menu.clear();
        MenuItem item, item1, item2, item3;

    }


}

