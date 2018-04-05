package com.example.mahmoudamin.gradpro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TextView loginemail,loginpassword, newAccount;
    Button   loginbuttom;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        loginemail= (TextView) findViewById(R.id.loginEmailField);
        loginpassword= (TextView) findViewById(R.id.loginPasswordField);
        newAccount = (TextView) findViewById(R.id.newAccountBtn);
        loginbuttom = (Button) findViewById(R.id.loginBtn);

        loginbuttom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Login();

            }
        });

        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, Signup.class);
                startActivity(in);
            }
        });
    }

    private void Login() {

        String emil = loginemail.getText().toString().trim();
        String pass = loginpassword.getText().toString().trim();


        if (TextUtils.isEmpty(emil)) {
            loginemail.setError("user is requird");
        }
        else if (TextUtils.isEmpty(pass)) {
            loginpassword.setError("password is requird");
        }
        else {
            mAuth.signInWithEmailAndPassword(emil, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Logged Success", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(MainActivity.this, App.class);
                        startActivity(in);
                    } else {
                        Toast.makeText(getApplicationContext(), "Logged Failed", Toast.LENGTH_SHORT).show();

                    }
                }
            });

        }
    }




    public boolean onCreateOptionsMenu(Menu menu) {


        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.about_project:
                Toast.makeText(this,"Aboud pro",Toast.LENGTH_LONG).show();
                Intent in =new Intent(this,Aboutproject.class);
                startActivity(in);
                break;
            case R.id.app:
                Toast.makeText(this,"Help pressed",Toast.LENGTH_LONG).show();
                Intent in1 =new Intent(this,App.class);
                startActivity(in1);

                break;



        }
        return super.onOptionsItemSelected(item);
    }


}
