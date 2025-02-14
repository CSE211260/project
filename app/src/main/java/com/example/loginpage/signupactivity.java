package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signupactivity extends AppCompatActivity {
    private FirebaseAuth auth;
            private EditText signupEmail,signupPassword ;
    private Button signupButton;
    private TextView loginRedirectText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signupactivity);

            auth = FirebaseAuth.getInstance();
            signupEmail= findViewById(R.id.signup_email);
           signupPassword = findViewById(R.id.signup_password);
            signupButton =findViewById(R.id.signup_button);
            loginRedirectText = findViewById(R.id.loginRedirectText);

            signupButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String user = signupEmail.getText().toString().trim();
                    String pass = signupPassword.getText().toString().trim();

                    if (user.isEmpty()){
                        signupEmail.setError("Email cannot be empty");
                    }
                    if(pass.isEmpty()){
                        signupPassword.setError("password cannot be empty");
                    } else {
                        auth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(signupactivity.this, "signup successful", Toast.LENGTH_SHORT).show();

                                    startActivity(new Intent(signupactivity.this,loginactivity.class));
                                } else {
                                    Toast.makeText(signupactivity.this,"signup failed"+  task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                }
            });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity((new Intent(signupactivity.this, loginactivity.class)));
            }
        });
        }

}