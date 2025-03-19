package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fooddelievery.R;

import java.util.ArrayList;

public class CartPage extends AppCompatActivity {
    private ArrayList<FoodItem> cartItems;
    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private TextView subtotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        cartItems = new ArrayList<>();
        createDummyData();
        setContentView(R.layout.activity_cart_page2);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(CartPage.this));
        adapter = new CartAdapter(this, cartItems);
        recyclerView.setAdapter(adapter);
        Button backbuttoncart= findViewById(R.id.back_buttonCart);
        Button trackorderpage= findViewById(R.id.button5);
        trackorderpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(CartPage.this, TrackOrderPage.class);
                startActivity(intent);
                finish();
            }
        });
        backbuttoncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(CartPage.this, itemPage.class);
                startActivity(intent);
                finish();
            }
        });
        subtotal = findViewById(R.id.subtotal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ConstraintLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public  void createDummyData() {
        cartItems.add(new FoodItem(1, "Pizza", 199, "Delicious pizza with cheese and toppings.", 300, 10, 15));
    }
}