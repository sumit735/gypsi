package com.release.gypsi.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.release.gypsi.Models.GridItem;
import com.release.gypsi.R;
import com.release.gypsi.adapters.FriendsListAdapter;
import com.release.gypsi.helpers.ProgressBarHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static android.content.res.Configuration.SCREENLAYOUT_SIZE_LARGE;
import static android.content.res.Configuration.SCREENLAYOUT_SIZE_MASK;
import static android.content.res.Configuration.SCREENLAYOUT_SIZE_XLARGE;

public class FriendsListing extends AppCompatActivity {

    GridView gridView;
    ArrayList<String> mItems;
    Context context;
    ArrayList<GridItem> itemData = new ArrayList<GridItem>();
    private FriendsListAdapter customGridAdapter;
    /*The Data to be posted*/
    int offset = 1;
    int limit = 10;
    int listSize = 0;
    int itemsInServer = 0;
    private boolean mIsScrollingUp;
    private int mLastFirstVisibleItem;
    int scrolledPosition=0;
    boolean scrolling;
    boolean firstTime = false;
    GetUserDetails asyncReg;
    int corePoolSize = 60;
    int maximumPoolSize = 80;
    int keepAliveTime = 10;
    BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(maximumPoolSize);
    Executor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendlists);

        gridView = (GridView) findViewById(R.id.imagesGridView);

        Toolbar mActionBarToolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        mActionBarToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        mActionBarToolbar.setTitle("");
        mActionBarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Log.v("SUBHA","genre value == " + getIntent().getStringExtra("genreName"));

        asyncReg = new GetUserDetails();
        asyncReg.executeOnExecutor(threadPoolExecutor);


        ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
        layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT; //this is in pixels
        gridView.setLayoutParams(layoutParams);
        gridView.setNumColumns(2);

        resetData();

        customGridAdapter = new FriendsListAdapter(FriendsListing.this, R.layout.activity_friends_listing, itemData);

        gridView.setAdapter(customGridAdapter);


        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                if (gridView.getLastVisiblePosition() >= itemsInServer - 1) {

                    return;

                }

                if (view.getId() == gridView.getId()) {
                    final int currentFirstVisibleItem = gridView.getFirstVisiblePosition();

                    if (currentFirstVisibleItem > mLastFirstVisibleItem) {
                        mIsScrollingUp = false;

                    } else if (currentFirstVisibleItem < mLastFirstVisibleItem) {
                        mIsScrollingUp = true;

                    }

                    mLastFirstVisibleItem = currentFirstVisibleItem;
                }
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    scrolling = false;

                } else if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {

                    scrolling = true;

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


                if (scrolling == true && mIsScrollingUp == false) {

                    if (firstVisibleItem + visibleItemCount >= totalItemCount) {

                        listSize = itemData.size();
                        if (gridView.getLastVisiblePosition() >= itemsInServer - 1) {
                            return;

                        }
                        offset += 1;

                        asyncReg = new GetUserDetails();
                        asyncReg.executeOnExecutor(threadPoolExecutor);

                        scrolling = false;


                    }

                }

            }
        });


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

/*
                GridItem item = itemData.get(position);


                Intent intent = new Intent(FriendsListing.this, SinglePartActivity.class);
                intent.putExtra("MovieTitle",item.getTitle());
                intent.putExtra("MovieImage",item.getImageId());
                intent.putExtra("MovieDetails",item.getShort_desc());
                intent.putExtra("MovieUrl",item.getVideoUrl());
                intent.putExtra("MovieBanner",item.getBannerImage());
                intent.putExtra("MovieGenre",item.getMovieGenre());
                intent.putExtra("MovieDuration",item.getMovieDuration());
                intent.putExtra("MovieID",item.getId());
                startActivity(intent);*/

            }
        });



    }


    public void onResume() {
        super.onResume();
        //movieThirdPartyUrl = getResources().getString(R.string.no_data_str);


    }



    public void resetData() {
        if (itemData != null && itemData.size() > 0) {
            itemData.clear();
        }
        firstTime = true;

        offset = 1;
        //  isLoading = false;
        listSize = 0;
        if (((getResources().getConfiguration().screenLayout & SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_LARGE) || ((getResources().getConfiguration().screenLayout & SCREENLAYOUT_SIZE_MASK) == SCREENLAYOUT_SIZE_XLARGE)) {
            limit = 20;
        } else {
            limit = 15;
        }
        itemsInServer = 0;
        //isSearched = false;
    }


    public class GetUserDetails extends AsyncTask<Void, String, JSONArray> {
        JSONArray array;
        ProgressBarHandler pDialog;
        @Override
        protected JSONArray doInBackground(Void... params) {

            try {

                URL url = new URL("http://gvitechnology.com/gypsi/api/friendlist.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                httpURLConnection.setRequestProperty("Accept", "application/json");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                JSONObject postData = new JSONObject();
                try {
                    postData.put("userid", getIntent().getStringExtra("LOGIN_ID"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }



                DataOutputStream outputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                outputStream.write(postData.toString().getBytes("UTF-8"));

                int code = httpURLConnection.getResponseCode();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                String line = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                bufferedReader.close();

                String response = stringBuilder.toString();
                JSONArray jsonObject = new JSONArray(response);



                int lengthJsonArr = jsonObject.length();
                JSONObject jsonChildNode;
                Log.v("SUBHA","api res == " + lengthJsonArr);
                for (int i = 0; i < lengthJsonArr; i++) {
                    GridItem movie = new GridItem();
                    jsonChildNode = jsonObject.getJSONObject(i);

                    movie.setId(jsonChildNode.getString("id"));
                    movie.setTitle(jsonChildNode.getString("name"));
                    movie.setImageId(jsonChildNode.getString("image"));
                    movie.setShort_desc(jsonChildNode.getString("details"));
                    movie.setVideoUrl(jsonChildNode.getString("url"));
                    movie.setBannerImage(jsonChildNode.getString("banner_image"));
                    movie.setMovieDuration(jsonChildNode.getString("total_time"));
                    movie.setMovieGenre(jsonChildNode.getString("genre"));


                    itemData.add(movie);


                }

                customGridAdapter.notifyDataSetChanged();
                pDialog.hide();


            } catch (Exception e) {

                e.printStackTrace();
            }
            return array;


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressBarHandler(FriendsListing.this);
            pDialog.show();

        }

        @Override
        protected void onPostExecute(JSONArray array) {
            super.onPostExecute(array);


        }
    }

}