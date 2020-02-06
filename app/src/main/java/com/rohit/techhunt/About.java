package com.rohit.techhunt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class About extends AppCompatActivity {

    ImageView github_profile,equinox,Axios,Dsc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        github_profile = findViewById(R.id.rohit_github);
        github_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCrometab();
            }
        });
    }

    private void openCrometab() {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(Color.parseColor("#003366"));
        CustomTabsIntent intent = builder.build();
        intent.launchUrl(this, Uri.parse("https://github.com/refactor-droidyy"));
    }
}
