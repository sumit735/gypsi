package com.release.gypsi.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.release.gypsi.Models.User;
import com.release.gypsi.R;
import com.release.gypsi.Services.DataContext;
import com.release.gypsi.Services.LocalUserService;
import com.release.gypsi.Services.Tools;

public class ActivityProfile extends AppCompatActivity {

    DataContext db = new DataContext(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        User user = LocalUserService.getLocalUserFromPreferences(this);
        TextView tv_UserFullName = (TextView) findViewById(R.id.tv_UserFullName);
        tv_UserFullName.setText(Tools.toProperName(user.FirstName) + " " + Tools.toProperName(user.LastName));
    }
}
