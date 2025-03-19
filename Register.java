package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fooddelievery.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    TextInputEditText editTextEmail, editTextPassword;
    Button buttonReg;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;
    Intent intent;
    ImageView imageView;
    EditText editTextFullName, editTextAddress, editTextMobileNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        editTextEmail= findViewById(R.id.email);
        editTextPassword= findViewById(R.id.password);
        buttonReg= findViewById(R.id.btn_register);
        mAuth= FirebaseAuth.getInstance();
        progressBar= findViewById(R.id.progressBar);
        textView=findViewById(R.id.loginNow);
        editTextFullName= findViewById(R.id.textViewFullName);
        editTextAddress= findViewById(R.id.textViewAddress);
        editTextMobileNumber= findViewById(R.id.textViewMobileNumber);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent= new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });


        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email;
                String password;
                email= editTextEmail.getText().toString().trim();
                password= editTextPassword.getText().toString();
                String textfullname= editTextFullName.getText().toString();
                String textaddress= editTextAddress.getText().toString();
                String textmobile= editTextMobileNumber.getText().toString();
                if(TextUtils.isEmpty(textfullname)){
                    Toast.makeText(Register.this, "Please enter your name", Toast.LENGTH_LONG).show();
                    editTextFullName.setError("full name is missing");
                    editTextFullName.requestFocus();}
                else if(TextUtils.isEmpty(textaddress)){
                    Toast.makeText(Register.this, "Please enter your address", Toast.LENGTH_LONG).show();
                    editTextAddress.setError("full address is missing");
                    editTextAddress.requestFocus();
                }

                else if(TextUtils.isEmpty(textmobile)){
                    Toast.makeText(Register.this, "Please enter your Mobile number", Toast.LENGTH_LONG).show();
                    editTextMobileNumber.setError("full mobile number is missing");
                    editTextMobileNumber.requestFocus();
                }
                if (TextUtils.isEmpty(email)) {
                    editTextMobileNumber.setError("empty email address");
                    editTextMobileNumber.requestFocus();
                }
                if (TextUtils.isEmpty(password)) {
                    editTextMobileNumber.setError("password can't be empty");
                    editTextMobileNumber.requestFocus();
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                       @Override
                                                       public void onComplete(@NonNull Task<AuthResult> task) {
                                                           progressBar.setVisibility(View.GONE);

                                                               if(task.isSuccessful()){
                                                                   FirebaseUser user=mAuth.getCurrentUser();
                                                                   ReadWriteUserDetails writeUserDetails= new ReadWriteUserDetails(textfullname, textaddress, textmobile);
                                                                   String useremail= user.getEmail();
                                                                   DatabaseReference referenceProfile= FirebaseDatabase.getInstance().getReference("Registered Users");
                                                                   referenceProfile.child(user.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                       @Override
                                                                       public void onComplete(@NonNull Task<Void> task) {
                                                                           if(task.isSuccessful()){
                                                                               user.sendEmailVerification();
                                                                               Toast.makeText(Register.this, "Registered saved successful", Toast.LENGTH_LONG).show();
                                                                               Intent intent= new Intent(Register.this, profilepicker.class);
                                                                               startActivity(intent);
                                                                               finish();

                                                                           }else {
                                                                               Toast.makeText(Register.this, "Registration unsuccessful", Toast.LENGTH_LONG).show();


                                                                           }


                                                                       }
                                                                   });
                                                               }






                                                       }
                                                   }
                            );
                }

            }
        });
    }
}