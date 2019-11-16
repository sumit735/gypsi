package com.example.gypsi.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.gypsi.R;
import com.example.gypsi.SharedPreferenceClass;
import com.example.gypsi.helpers.ProgressBarHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class HomeFragment extends Fragment {

    ProgressBarHandler pDialog;
    Context context;

    int currentPage = 0;
    String section_name;
    String section_id;
    RecyclerView genreListView;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.

    RecyclerView my_recycler_view;
    RecyclerView adspager;
    RecyclerView bannerpager;
    LinearLayoutManager mLayoutManager;
    Button latest_songs, latest_shortfilms, latest_webseries, latest_movies;

    RelativeLayout loadingPanel;
    SharedPreferenceClass sharedPreferenceClass;
    int keepAliveTime = 10;
    int corePoolSize = 60;
    int maximumPoolSize = 80;
    String sectionName, sectionId;
    BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(maximumPoolSize);
    Executor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);


        sharedPreferenceClass = new SharedPreferenceClass(getActivity());

        // my_recycler_view.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);


        return v;


    }


    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        super.onStop();
    }


    @Override
    public void onResume() {

        super.onResume();


    }
}





