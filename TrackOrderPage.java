package com.example;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import com.example.fooddelievery.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TrackOrderPage extends AppCompatActivity {
    static int PERMISSION_CODE= 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_order_page);
        Button Call= findViewById(R.id.callIcon);
        if (ContextCompat.checkSelfPermission(TrackOrderPage.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(TrackOrderPage.this,new String[]{Manifest.permission.CALL_PHONE},PERMISSION_CODE);}

        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String phoneNo = "9971385281";
                    Intent i = new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:"+phoneNo));
                    startActivity(i);


            }
        });
        ImageView timelineIndicator = findViewById(R.id.timelineIndicator);

        int translationDistance = getResources().getDimensionPixelSize(R.dimen.timeline_translation_distance);

        Animation animation = new TranslateAnimation(0, translationDistance, 0, 100);
        animation.setDuration(100);
        animation.setFillAfter(true);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {}

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        timelineIndicator.startAnimation(animation);
    }
}
