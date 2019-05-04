package com.google.firebase.codelab.mlkit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity
    {

        private static final String TAG = "EmailPassword";

        private TextView mStatusTextView;
        private TextView mDetailTextView;
        private EditText mEmailField;
        private EditText mPasswordField;

        // [START declare_auth]
        private FirebaseAuth mAuth;
        // [END declare_auth]


        private EditText editEmail;
        private EditText editPassword;
        private Button loginButt;
        private Button NewButt;
        private TextView textShow;
        private ProgressDialog progressDIALOG;
        private FirebaseAuth firebaseAUTH;
        @Override
        protected void onCreate(Bundle savedInstanceState) {


            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);


            firebaseAUTH = FirebaseAuth.getInstance();

            if(firebaseAUTH.getCurrentUser() != null)
                {
                    //go to pull activity here
                    finish();
                    startActivity( new Intent(getApplicationContext(), MainActivity.class));
                }

            progressDIALOG = new ProgressDialog(this);
            loginButt = (Button) findViewById(R.id.buttonLogin);
            NewButt = (Button) findViewById(R.id.buttonCreateAccount);
            textShow = (TextView) findViewById(R.id.textView);
            editEmail = (EditText) findViewById(R.id.editTextEmail);
            editPassword = (EditText) findViewById(R.id.editTextPassword);

            NewButt.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                        {
                            createAccount();
                        }
                });

            loginButt.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                        {
                            SignIn();
                        }
                });
        }


        public void createAccount()
            {
                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                    {
                        // email is empty
                        Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
                        return;
                    }

                if(TextUtils.isEmpty(password))
                    {
                        Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
                        return;
                    }

                progressDIALOG.setMessage(("Registering User..."));
                progressDIALOG.show();

                firebaseAUTH.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                    {
                                        //user is sccelfully registered and logged in
                                        // we will start the other activity here
                                        Toast.makeText(getApplicationContext(), "retistered succesfully", Toast.LENGTH_SHORT).show();
                                        finish();
                                        startActivity( new Intent(getApplicationContext(), MainActivity.class));
                                    }
                                else
                                    {
                                        Toast.makeText(getApplicationContext(), "could not register", Toast.LENGTH_SHORT).show();
                                    }
                            }
                        });
            }

        public void SignIn()
            {
                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                    {
                        // email is empty
                        Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
                        return;
                    }

                if(TextUtils.isEmpty(password))
                    {
                        Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
                        return;
                    }

                firebaseAUTH.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDIALOG.dismiss();

                                if(task.isSuccessful())
                                    {
                                        //start the pull activity
                                        finish();
                                        startActivity( new Intent(getApplicationContext(), MainActivity.class));
                                    }
                                else
                                    {
                                        Toast.makeText(getApplicationContext(), "could not register", Toast.LENGTH_SHORT).show();
                                    }
                            }
                        });
            }
    }
