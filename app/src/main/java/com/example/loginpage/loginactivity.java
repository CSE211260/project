package com.example.loginpage;



import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

import java.util.regex.Pattern;

public class loginactivity extends AppCompatActivity {
     private FirebaseAuth auth;
     private EditText loginEmail,loginPssword;
     private TextView signupRedirectText;
     private Button loginButton;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        auth = FirebaseAuth.getInstance();
        loginEmail = findViewById(R.id.login_email);
        loginPssword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        signupRedirectText = findViewById(R.id.signupRedirectText);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginEmail.getText().toString();
                String pass = loginPssword.getText().toString();
                 if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                   if (!pass.isEmpty()){
                   auth.signInWithEmailAndPassword(email,pass)
                           .addOnSuccessListener((new OnSuccessListener<AuthResult>() {
                               @Override
                                       public void onSuccess(AuthResult authResult){
                           Toast.makeText(loginactivity.this,"login successful",Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(loginactivity.this, MainActivity.class));
                           finish();
                       }
                       })).addOnFailureListener(new OnFailureListener() {
                           @Override
                        public void onFailure(@NonNull Exception e){
                               Toast.makeText(loginactivity.this,"login failed",Toast.LENGTH_SHORT).show();
                           }
                   });
                   }else{

                     loginPssword.setError("password cannot be empty");
                   }
                   }else if(email.isEmpty()){
                loginEmail.setError("email cannot be empty");
            }else{
                loginEmail.setError("please enter valid email");

                }

            }
        });


    signupRedirectText.setOnClickListener(new View.OnClickListener() {
        @Override
                public  void onClick(View view){
           startActivity(new Intent(loginactivity.this,signupactivity.class));
        }
    });

}
}