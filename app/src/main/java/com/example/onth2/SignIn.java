package com.example.onth2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignIn extends Authentication {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Button signUp = findViewById(R.id.submit);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);


        onSignIn(signUp, email, password);
        TextView signIn = findViewById(R.id.SignIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
            }
        });

    }

    @Override
    void updateUI(FirebaseUser currentUser, String messageError) {

        if(currentUser == null){
            Toast.makeText(SignIn.this, "Authentication failed."+messageError,
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(SignIn.this, "Authentication Success.",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(SignIn.this, MainActivity.class);
            startActivity(intent);
        }
    }
}