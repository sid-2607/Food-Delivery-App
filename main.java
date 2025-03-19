package com.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.fooddelievery.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class main extends AppCompatActivity {
    ArrayList<com.example.recyclerview_list> recyclerview_list;
    RecyclerView recyclerView;
    Uri uri;
    FirebaseAuth authprofile;
    FirebaseUser firebaseUser;
    public String name= "Cheese Pizza";
    public String  price= "Rs 199";
    public String fat= "21g";
    public String saturated_fat="8g";
    StorageReference storageReference;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        recyclerView = findViewById(R.id.FoodItemsList);
        recyclerView.setHasFixedSize(true);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        authprofile= FirebaseAuth.getInstance();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerview_list = new ArrayList<>();
        recyclerview_list.add(new recyclerview_list(R.drawable.pzza,  "Pizza","Price: Rs.199","Fats: 21g","Saturated Fats: 8g","Calories: 670cal"));
        recyclerview_list.add(new recyclerview_list(R.drawable.img,  "Burger","Price: Rs.99","Fats: 120g","Saturated Fats: 19g","Calories: 700cal"));
        recyclerview_list.add(new recyclerview_list(R.drawable.img_1, "1lb Wings","Price: Rs.79","Fats: 40g","Saturated Fats: 20g","Calories: 1000cal"));
        recyclerview_list.add(new recyclerview_list(R.drawable.img_2, "Espresso","Price: Rs.59","Fats: 80g","Saturated Fats: 15g","Calories: 500cal"));
        recyclerview_list.add(new recyclerview_list(R.drawable.img_3, "Cobb Salad","Price: Rs.69","Fats: 49g","Saturated Fats: 14g","Calories: 200cal"));
        recyclerview_list.add(new recyclerview_list(R.drawable.img_4, "Cookies","Price: Rs.129","Fats: 69g","Saturated Fats: 11g","Calories: 400cal"));
        recyclerview_adapter recyclerview_adapter = new recyclerview_adapter(recyclerview_list, this);
        recyclerView.setAdapter(recyclerview_adapter);
        FirebaseDatabase.getInstance().getReference().child("Items List").push().setValue(recyclerview_list);
        authprofile= FirebaseAuth.getInstance();
        firebaseUser= authprofile.getCurrentUser();
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        DatabaseReference myRef= database.getReference("Displace pictures");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String imageUrl = snapshot.getValue(String.class);
                if (imageUrl != null && !imageUrl.isEmpty()) {
                    ReadWriteUserDetails readUserDetails= snapshot.getValue(ReadWriteUserDetails.class);
                    String textfullname= firebaseUser.getDisplayName();
                    assert readUserDetails != null;
                    String mobile= readUserDetails.mobilenumber;
                    String address= readUserDetails.address;
                    TextView addressTextView = findViewById(R.id.userlocation);
                    addressTextView.setText(address);
                    ImageView profilePic = findViewById(R.id.profilepicturemain);
                    //Picasso.get().load(imageUrl).into(profilePic);
                    Glide.with(mContext).load(imageUrl).into(profilePic);
                } else {
                    Log.e("TAG", "Image URL is null or empty");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(main.this, "error fetching the details try again.",
                        Toast.LENGTH_SHORT).show();

            }
        });
        String calories="670cal";
        itemPageInfo itemInfo= new itemPageInfo(name, price, fat, saturated_fat, calories);
        /*DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Items List");
        reference.setValue(itemInfo);*/


    }



}