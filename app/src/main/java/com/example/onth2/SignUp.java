package com.example.onth2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends Authentication {
    DatabaseReference ref;
      FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button signUp = findViewById(R.id.submit);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        onSignUp(signUp, email, password);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("users");
    }
    @Override
    void updateUI(FirebaseUser currentUser, String messageError) {
        if(currentUser == null){
            Toast.makeText(SignUp.this, "Authentication failed." +messageError,
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(SignUp.this, "Authentication Success.",
                    Toast.LENGTH_SHORT).show();

            ref.child(currentUser.getUid()).setValue(currentUser.getUid());

            Intent intent = new Intent(SignUp.this,  SignIn.class);
            startActivity(intent);
        }
    }
}