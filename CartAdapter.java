package com.example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelievery.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<FoodItem> cartItems;

    public CartAdapter(Context context, List<FoodItem> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }


    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        FoodItem item = cartItems.get(position);
        holder.itemName.setText(item.getName());
        holder.itemQuantity.setText(String.valueOf(item.getQuantity()));
        holder.total.setText(String.valueOf(item.getQuantity()*item.getPrice()));
        holder.btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = item.getQuantity();
                item.setQuantity(currentQuantity + 1);

                notifyDataSetChanged();
            }
        });

        holder.btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = item.getQuantity();
                if (currentQuantity > 0) {
                    item.setQuantity(currentQuantity - 1);

                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemQuantity;
        TextView total;
        Button btnIncrease;
        Button btnDecrease;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.name);
            itemQuantity = itemView.findViewById(R.id.quantity);
            btnIncrease = itemView.findViewById(R.id.plus);
            btnDecrease = itemView.findViewById(R.id.minus);
            total = itemView.findViewById(R.id.price);
        }
    }
}