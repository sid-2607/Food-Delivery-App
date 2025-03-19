package com.example;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelievery.R;

import java.util.ArrayList;

public class recyclerview_adapter extends RecyclerView.Adapter<recyclerview_adapter.ViewHolder> {
    private ArrayList<com.example.recyclerview_list> recyclerview_list;
    private Context context;
    private ViewHolder holder;
    private int position;

    public recyclerview_adapter(ArrayList<com.example.recyclerview_list> recyclerview_list, Context context) {
        this.recyclerview_list = recyclerview_list;
        this.context = context;
    }

    @NonNull
    @Override
    public recyclerview_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerview_adapter.ViewHolder holder, int position) {
        this.holder = holder;
        this.position = position;
        holder.imageView.setImageResource(recyclerview_list.get(position).getImage());
        holder.textView2.setText(recyclerview_list.get(position).getitemName());
        holder.textView3.setText(recyclerview_list.get(position).getPrice());
        holder.textView4.setText(recyclerview_list.get(position).getFat());
        holder.textView5.setText(recyclerview_list.get(position).getSaturated_fat());
        holder.textView6.setText(recyclerview_list.get(position).getCalories());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerview_list selectedItem = recyclerview_list.get(position);

                Intent intent = new Intent(context, itemPage.class);
                intent.putExtra("image", selectedItem.getImage());
                intent.putExtra("itemName", selectedItem.getitemName());
                intent.putExtra("price", selectedItem.getPrice());
                intent.putExtra("fat", selectedItem.getFat());
                intent.putExtra("saturatedFat", selectedItem.getSaturated_fat());
                intent.putExtra("calories", selectedItem.getCalories());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recyclerview_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            imageView=itemView.findViewById(R.id.imageView3);
            textView2=itemView.findViewById(R.id.textView4);
            textView3=itemView.findViewById(R.id.textView5);
            textView4=itemView.findViewById(R.id.textView6);
            textView5=itemView.findViewById(R.id.textView7);
            textView6=itemView.findViewById(R.id.textView8);


        }
    }
}