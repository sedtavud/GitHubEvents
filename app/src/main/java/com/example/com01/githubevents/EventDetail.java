package com.example.com01.githubevents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.com01.githubevents.MainActivity.EXTRA_ID;
import static com.example.com01.githubevents.MainActivity.EXTRA_IMAGE;
import static com.example.com01.githubevents.MainActivity.EXTRA_LOGIN;
import static com.example.com01.githubevents.MainActivity.EXTRA_URL;

public class EventDetail extends AppCompatActivity {
    TextView acc_id,acc_displayname,acc_url;
    ImageView acc_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        Intent intent = getIntent();
        String id = intent.getStringExtra(EXTRA_ID);
        String display_name = intent.getStringExtra(EXTRA_LOGIN);
        String url = intent.getStringExtra(EXTRA_URL);
        String image = intent.getStringExtra(EXTRA_IMAGE);

        acc_id = findViewById(R.id.acc_id);
        acc_displayname = findViewById(R.id.acc_displayname);
        acc_url = findViewById(R.id.acc_url);
        acc_image = findViewById(R.id.acc_image);


        acc_id.setText(id);
        acc_url.setText(url);
        acc_displayname.setText(display_name);
        Picasso.get().load(image).into(acc_image);
    }
}
