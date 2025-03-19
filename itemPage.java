package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.fooddelievery.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class itemPage extends AppCompatActivity {

    BottomSheetBehavior bottomSheetBehavior;
    FrameLayout bottomSheetContainer;

    Button back, cart, floatingCart;
    private Button button;
    private boolean isChecked = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_page);
        Intent intent = getIntent();
        Integer image = intent.getIntExtra("image",1);
        String itemName = intent.getStringExtra("itemName");
        String price = intent.getStringExtra("price");
        String fat = intent.getStringExtra("fat");
        String saturatedFat = intent.getStringExtra("saturatedFat");
        String calories = intent.getStringExtra("calories");
        TextView itemNameTextView = findViewById(R.id.itemNameTextView);
        TextView priceTextView = findViewById(R.id.priceTextView);
        TextView fatTextView = findViewById(R.id.fatTextView);
        TextView saturatedFatTextView = findViewById(R.id.saturatedFatTextView);
        TextView caloriesTextView = findViewById(R.id.caloriesTextView);
        TextView pricecarpopup= findViewById(R.id.item_price_text);
        itemNameTextView.setText(itemName);
        assert price != null;
        priceTextView.setText(String.format(price));
        pricecarpopup.setText(String.format(price));
        assert fat != null;
        fatTextView.setText(String.format(fat));
        assert saturatedFat != null;
        saturatedFatTextView.setText(String.format(saturatedFat));
        assert calories != null;
        caloriesTextView.setText(String.format(calories));
        FrameLayout sheet = findViewById(R.id.sheet);

        BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from(sheet);
        bottomSheetBehavior.setPeekHeight(550);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        back = findViewById(R.id.back_button);
        back.setOnClickListener(view -> startActivity(new Intent(itemPage.this, MainActivity2.class)));

        button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            isChecked = !isChecked;
            if (isChecked) {
                button.setBackgroundResource(R.drawable.tick_button);
                findViewById(R.id.price_and_button_container).setVisibility(View.VISIBLE);
            } else {
                button.setBackgroundResource(R.drawable.add_button);
                findViewById(R.id.price_and_button_container).setVisibility(View.GONE);
            }
        });


        cart = findViewById(R.id.CartButton);
        cart.setOnClickListener(view -> startActivity(new Intent(itemPage.this, CartPage.class)));

        cart.setVisibility(View.GONE);

        floatingCart = findViewById(R.id.floating_cart_button);
        floatingCart.setOnClickListener(view -> startActivity(new Intent(itemPage.this, CartPage.class)));
    }

    private void updateCartButtonVisibility() {
        if (itemsAddedToCart()) {
            cart.setVisibility(View.VISIBLE);
        } else {
            cart.setVisibility(View.GONE);
        }
    }
    private boolean itemsAddedToCart() {
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCartButtonVisibility();
    }
}
