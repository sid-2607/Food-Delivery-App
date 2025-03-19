package com.example;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.fooddelievery.R;

public class MainActivity2 extends AppCompatActivity {

    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        b = findViewById(R.id.b1);
        b.setOnClickListener(view -> startActivity(new Intent(MainActivity2.this, itemPage.class)));
    }
}
