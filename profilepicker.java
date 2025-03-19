package com.example;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fooddelievery.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class profilepicker extends AppCompatActivity {
    ProgressBar progressBar;
    ImageView imageUploadpic;
    FirebaseAuth authprofile;
    StorageReference storageReference;
    FirebaseUser firebaseUser;
    static final int PICK_IMAGE_REQUEST=1;
    Uri uriImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilepicker);
        authprofile= FirebaseAuth.getInstance();
        Button buttonUpload= findViewById(R.id.uploadImageButton);
        Button buttonpic=findViewById(R.id.chooseImageButton);
        imageUploadpic=findViewById(R.id.selectedImageView);
        authprofile= FirebaseAuth.getInstance();
        firebaseUser= authprofile.getCurrentUser();
        storageReference= FirebaseStorage.getInstance().getReference("Displace pictures");
        if(firebaseUser != null){
            Uri uri= firebaseUser.getPhotoUrl();
            Picasso.get().load(uri).into(imageUploadpic);
        }
        else{
            Toast.makeText(profilepicker.this, "Upload the Profile Picture", Toast.LENGTH_LONG).show();
        }
        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uploadpic();

            }
        });
        buttonpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
    }

    private void Uploadpic() {
        if(uriImage!=null){
            StorageReference filereference= storageReference.child(authprofile.getCurrentUser().getUid()+ "." + getFileExtention(uriImage));
            filereference.putFile(uriImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    filereference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            if(firebaseUser!=null){
                            firebaseUser= authprofile.getCurrentUser();

                            UserProfileChangeRequest profileupdate= new UserProfileChangeRequest.Builder().setPhotoUri(uri).build();
                            firebaseUser.updateProfile(profileupdate);
                                Intent intent= new Intent(profilepicker.this, main.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(profilepicker.this, "Please register first", Toast.LENGTH_LONG).show();

                            }
                        }
                    });

                }
            });
        }
    }

    private String getFileExtention(Uri uriImage) {
        ContentResolver cr= getContentResolver();
        MimeTypeMap mime= MimeTypeMap.getSingleton();


        return mime.getExtensionFromMimeType(cr.getType(uriImage));
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode== RESULT_OK && data!=null && data.getData()!=null){
            uriImage= data.getData();
            imageUploadpic.setImageURI(uriImage);
        }
    }
}
