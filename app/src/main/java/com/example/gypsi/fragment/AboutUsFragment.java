package com.example.gypsi.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.gypsi.R;
import com.example.gypsi.activities.MainActivity;
import com.example.gypsi.helpers.ProgressBarHandler;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment {
    String about;
    // TextView textView;
    Context context;
    ProgressBar progresBar;
    WebView webView;
    ProgressBarHandler pDialog;

    boolean returnValue = false ;
    TextView noInternetTextView;

    RelativeLayout noInternet;

    boolean isNetwork;

    public AboutUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       /* getActionBar().setTitle(getArguments().getString(""));
        setHasOptionsMenu(true);*/

        final View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        context = getActivity();


        progresBar = (ProgressBar) view.findViewById(R.id.progress_bar);


        ((MainActivity) getActivity()).getSupportActionBar().setTitle(Html.fromHtml(getArguments().getString("title")));
        // Kushal - set Id to back button and text in Toolabr
        Toolbar toolbar = ((MainActivity) getActivity()).toolbar;
        ((MainActivity) getActivity()).toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));
        webView = (WebView) view.findViewById(R.id.aboutUsWebView);


        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);

//        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int progress) {
                progresBar.setVisibility(View.VISIBLE);
              /*  view.setFocusableInTouchMode(true);
                view.requestFocus();*/
                if (progress == 100){
                    progresBar.setVisibility(View.GONE);
                  /*  view.setFocusableInTouchMode(true);
                    view.requestFocus();*/
                }
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                // Handle the error
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                webView.loadUrl(url);
                return true;
            }
        });



        view.setFocusableInTouchMode(true);
        view.requestFocus();

        webView.setFocusableInTouchMode(true);
        webView.requestFocus();



        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {

                        if (webView.canGoBack()) {
                            webView.goBack();
                            returnValue = true;
                        } else {

                            final Intent startIntent = new Intent(getActivity(), MainActivity.class);


                            getActivity().runOnUiThread(new Runnable() {
                                public void run() {
                                    startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    getActivity().startActivity(startIntent);
                                    getActivity().finish();

                                }
                            });
                        }
                    }
                }
                return returnValue;
            }
        });

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
        MenuItem item,item1,item2,item3;

    }


    /*@Override
    public void onAboutUsPostExecuteCompleted(int status, String about) {

        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.hide();
                pDialog = null;
            }
        } catch (IllegalArgumentException ex) {

        }

        progresBar.setVisibility(View.GONE);
        String bodyData = about;
        if (status == 200) {
          *//*textView.setMovementMethod(LinkMovementMethod.getInstance());
            textView.setText(getStyledTextFromHtml(bodyData));*//*
          try{
              int color = getActivity().getResources().getColor(R.color.aboutustextcolor);
              String aboutUSTextColor = "#" + Integer.toHexString(color & 0x00FFFFFF);
              String text = "<html><head>"
                      + "<style type=\"text/css\" >body{color:" + aboutUSTextColor + ";}"
                      + "</style></head>"
                      + "<body style >"
                      + about
                      + "</body></html>";

              String base64 = android.util.Base64.encodeToString(text.getBytes("UTF-8"), android.util.Base64.DEFAULT);
              webView.loadData(base64, "text/html; charset=utf-8", "base64");

//            webView.loadData(text, "text/html", "utf-8");
              webView.setBackgroundColor(getResources().getColor(R.color.aboutustestcolor));
              webView.getSettings().setJavaScriptEnabled(true);

          } catch (Exception e){
              e.printStackTrace();
          }

        }else {

            noInternetTextView.setText(languagePreference.getTextofLanguage(NO_DATA, DEFAULT_NO_DATA));
            noInternet.setVisibility(View.VISIBLE);
        }
    }
*/

}

