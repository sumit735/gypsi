package com.example.gypsi.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.gypsi.R;
import com.example.gypsi.activities.MainActivity;
import com.example.gypsi.helpers.ProgressBarHandler;




public class ContactUsFragment extends Fragment {
    ProgressBarHandler pDialog;
    Context context;
    String regEmailStr, regNameStr,regMessageStr;
    EditText editEmailStr, editNameStr,editMessageStr;
    TextView contactFormTitle;
    Button submit;
    String sucessMsg,statusmsg;
    String contEmail;
    boolean validate = true;


    public ContactUsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       /* getActionBar().setTitle(getArguments().getString(""));
        setHasOptionsMenu(true);*/
        getActivity().getWindow().setBackgroundDrawableResource(R.color.appBackgroundColor);
        View v = inflater.inflate(R.layout.fragment_contact_us, container, false);
        context = getActivity();


     //   TextView categoryTitle = (TextView) v.findViewById(R.id.categoryTitle);
        Typeface castDescriptionTypeface = Typeface.createFromAsset(context.getAssets(),context.getResources().getString(R.string.fonts));
        /*categoryTitle.setTypeface(castDescriptionTypeface);
        categoryTitle.setText(getArguments().getString("title"));*/
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(getArguments().getString("title"));
        // Kushal - set Id to back button and text in Toolabr
        Toolbar toolbar = ((MainActivity) getActivity()).toolbar;
        //setIdToActionBarBackButton(toolbar);

        contactFormTitle = (TextView) v.findViewById(R.id.contactFormTitle);
        Typeface contactFormTitleTypeface = Typeface.createFromAsset(context.getAssets(),context.getResources().getString(R.string.fonts));
        contactFormTitle.setTypeface(contactFormTitleTypeface);
        contactFormTitle.setText("Fill the form below.");

        //Log.v("SUBHA","ontact = "+ languagePreference.getTextofLanguage(FILL_FORM_BELOW, DEFAULT_FILL_FORM_BELOW));

        editEmailStr=(EditText) v.findViewById(R.id.contact_email) ;
        Typeface editEmailStrTypeface = Typeface.createFromAsset(context.getAssets(),context.getResources().getString(R.string.fonts));
        editEmailStr.setTypeface(editEmailStrTypeface);
        editEmailStr.setHint("Enter your email");

        editNameStr=(EditText) v.findViewById(R.id.contact_name) ;
        Typeface editNameStrTypeface = Typeface.createFromAsset(context.getAssets(),context.getResources().getString(R.string.fonts));
        editNameStr.setTypeface(editNameStrTypeface);
        editNameStr.setHint("Enter your Name");
        editNameStr.requestFocus();
      /*  InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editNameStr, InputMethodManager.SHOW_FORCED);*/
        //showKeyboard();

        editMessageStr=(EditText) v.findViewById(R.id.contact_msg) ;
        Typeface editMessageStrTypeface = Typeface.createFromAsset(context.getAssets(),context.getResources().getString(R.string.fonts));
        editMessageStr.setTypeface(editMessageStrTypeface);
        editMessageStr.setHint("Type Message");

        submit = (Button) v.findViewById(R.id.submit_cont);
        Typeface submitTypeface = Typeface.createFromAsset(context.getAssets(),context.getResources().getString(R.string.fonts));
        submit.setTypeface(submitTypeface);
        submit.setText("Submit");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "Submitted successfully", Toast.LENGTH_SHORT).show();

                SubmmitClicked();

            }
        });

     /*   editNameStr.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });
        editMessageStr.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });
        editEmailStr.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });
*/
        /*editNameStr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                    editNameStr.setError(null);
                }
                else{
                    editNameStr.setError("Required Field . ");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/
        /*editEmailStr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                   if (Util.isValidMail((editEmailStr.getText().toString().trim()))){
                       editEmailStr.setError(null);
                   }else
                   {
                       editEmailStr.setError("Invalid Email.");
                   }
                }
                else{
                    editEmailStr.setError("Required Field.");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/
        /*editMessageStr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                    editMessageStr.setError(null);
                }
                else{
                    editMessageStr.setError("Required Field.");
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
*/

        //  requestFocus(editNameStr);


      /*  v.setFocusableInTouchMode(true);
        v.requestFocus();
        v.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
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
                return false;
            }
        });*/

        editMessageStr.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        leaveCurrentPage();


                    }
                }
                return false;
            }
        });

        editNameStr.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        leaveCurrentPage();


                    }
                }
                return false;
            }
        });

        editEmailStr.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        leaveCurrentPage();


                    }
                }
                return false;
            }
        });

        setHasOptionsMenu(true);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
       /* MenuItem item,item1,item2,item3;
        item = menu.findItem(R.id.action_filter);
        item1 = menu.findItem(R.id.option);
        item2 = menu.findItem(R.id.search);
        item3 = menu.findItem(R.id.media_route_menu_item);
        item.setVisible(false);
        item1.setVisible(false);
        item2.setVisible(false);
        item3.setVisible(false);*/
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    public void SubmmitClicked() {

        regEmailStr = editEmailStr.getText().toString().trim();
        regNameStr = editNameStr.getText().toString().trim();
        regMessageStr = editMessageStr.getText().toString().trim();
        regMessageStr = regMessageStr.replaceAll("(\r\n|\n\r|\r|\n|<br />)", " ");



    }



    public void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        View v = getActivity().getCurrentFocus();
        if (v != null)
            imm.showSoftInput(v, 0);
    }



    public void leaveCurrentPage(){

        final Intent startIntent = new Intent(getActivity(), MainActivity.class);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        getActivity().startActivity(startIntent);
        getActivity().finish();
    }
}
