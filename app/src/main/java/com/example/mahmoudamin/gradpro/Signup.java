package com.example.mahmoudamin.gradpro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.ProviderQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    EditText  signupemail, signupusername, signuppassword, signupphone;
    Button signupbutton;
    DatabaseReference databaseuser;
    TextView alreadyHaveAcc;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        databaseuser = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();


        signupemail = (EditText) findViewById(R.id.emailField);
        signupusername = (EditText) findViewById(R.id.nameField);
        signuppassword = (EditText) findViewById(R.id.passwordField);
        signupphone = (EditText) findViewById(R.id.mobileField);
        signupbutton = (Button) findViewById(R.id.registerBtn);
        alreadyHaveAcc = (TextView) findViewById(R.id.alreadyHaveAccount);


        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adduser();

            }
        });

        alreadyHaveAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Signup.this, MainActivity.class);
                startActivity(in);
            }
        });
    }

//    public boolean checkemail() {
//
//        mAuth.fetchProvidersForEmail(signupemail.getText().toString())
//                .addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<ProviderQueryResult> task) {
//                        boolean check = !task.getResult().getProviders().isEmpty();
//
//                        if (check) {
//
//
//                        }
//                    }
//                });
//        return true;
//
//    }


    private void adduser() {

        final String fullname = signupusername.getText().toString().trim();
        final String email = signupemail.getText().toString().trim();
        final String password = signuppassword.getText().toString().trim();
        final String phone = signupphone.getText().toString().trim();


        if (TextUtils.isEmpty(fullname)) {
            signupusername.setError("Name is Requird");
        }
        else if (TextUtils.isEmpty(email)) {
            signupemail.setError("email is Requird ");
        }
//        else if(checkemail()==true){
//
//            signupemail.setError("Email is used ");
//            Toast.makeText(getApplicationContext(), "Email is used", Toast.LENGTH_SHORT).show();
//        }

        else if (TextUtils.isEmpty(password)) {
            signuppassword.setError("Password is Requird");
        }
        else if (TextUtils.isEmpty(phone)) {
            signupphone.setError("Phone is Requird");
        }
        else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                // If sign in fails, display a message to the user.
                                Log.e("eeeeeeeeeeee", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Signup.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();


                            } else {
                                Log.e("eeeeeeeeeeeeeeee", "createUserWithEmail:success");
                                FirebaseUser userr = mAuth.getCurrentUser();
                                String id = databaseuser.push().getKey();

                                Users user = new Users(id, fullname, email, password, phone);
                                databaseuser.child(id).setValue(user);

                                Toast.makeText(Signup.this, "Signup Success", Toast.LENGTH_SHORT).show();

                                Intent in = new Intent(Signup.this, MainActivity.class);
                                startActivity(in);

                            }


                        }


                    });




        }
    }
}
