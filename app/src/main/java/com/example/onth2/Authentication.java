package com.example.onth2;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

abstract  class Authentication extends AppCompatActivity {
    public  FirebaseAuth mAuth;
    private static final String TAG = "Authentication: ";

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }
    public void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user,null);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            updateUI(null,task.getException().getMessage().toString());
                        }
                    }
                });
    }
    public void signUp(String email, String password){
        System.out.println(email + " - "+password);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user,null);
                        } else {
                            // If sign in fails, display a message to the user.
                            System.err.println( );
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            updateUI(null,task.getException().getMessage().toString());
                        }
                    }
                });
    }
    public void onSignUp(Button signUp, EditText email,  EditText password ){
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                if(emailText.equals("") || passwordText.equals("")){
                    updateUI(null, "Email and password is not null");
                    return;
                }
                signUp(emailText, passwordText);
            }
        });
    }
    public void onSignIn(Button signUp, EditText email,  EditText password ){
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();

                if(emailText.equals("") || passwordText.equals("")){
                    updateUI(null, "Email and password is not null");
                    return ;
                }
                signIn(emailText, passwordText);
            }
        });
    }
    abstract void updateUI(FirebaseUser currentUser,String messageError);
}
