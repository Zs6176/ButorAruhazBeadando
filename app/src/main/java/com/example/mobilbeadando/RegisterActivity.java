package com.example.mobilbeadando;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class RegisterActivity extends AppCompatActivity {
    private static final String LOG_TAG = RegisterActivity.class.getName();
    private static final String PREF_KEY = RegisterActivity.class.getPackage().toString();


    EditText Email;
    EditText Passworld;
    EditText Lakcim;
    SharedPreferences preferences;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registry);

        int secret_key = getIntent().getIntExtra("SECRET_KEY", 0);

        if (secret_key != 99) {
            finish();
        }

        Email = findViewById(R.id.editTextTextEmailAddress);
        Passworld = findViewById(R.id.editTextTextPassword);
        Lakcim = findViewById(R.id.editTextTextPostalAddress);

        preferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        String email = preferences.getString("userName", "");
        String password = preferences.getString("password", "");


        Passworld.setText(password);
        Email.setText(email);

        mAuth = FirebaseAuth.getInstance();

        Log.i(LOG_TAG, "onCreate");
    }

    public void register(View view) {
        String email = Email.getText().toString();
        String password = Passworld.getText().toString();
        String lakcim = Lakcim.getText().toString();

        Log.i(LOG_TAG, "Regisztrált: " + email + ", e-mail: " + lakcim);
        // startShopping();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if(task.isSuccessful()){
                Log.d(LOG_TAG, "User created successfully");
                startShopping();
            } else {
                Log.d(LOG_TAG, "User wasn't created successfully");
                Toast.makeText(RegisterActivity.this, "User was't created successfully: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void startShopping() {
        Intent intent = new Intent(this, AruhazActivity.class);
        // intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }


    public void cansle(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        // intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }
}
