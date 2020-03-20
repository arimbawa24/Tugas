package com.example.tugas_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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



public class Login extends AppCompatActivity {
    private static final String TAG = Login.class.getSimpleName();
    Button button;
    EditText email;
    EditText pass;
    FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences preferences = getSharedPreferences("masuk",MODE_PRIVATE);
        String cek = preferences.getString("ingat","");

        if(cek.equals("true")){
            Intent intent = new Intent(Login.this, HalamanUtama.class);
            startActivity(intent);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        button = findViewById(R.id.btn_Login);
        email = findViewById(R.id.email);
        mAuth  = FirebaseAuth.getInstance();
        pass = findViewById(R.id.pass);
        TextView singUp = findViewById(R.id.linkSingUp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = email.getText().toString();
                String password1 = pass.getText().toString();

                if(TextUtils.isEmpty(email1)){
                    email.setError("Email belum di masukan");
                    return;
                }
                if(TextUtils.isEmpty(password1)){
                    pass.setError("Email belum di masukan");
                    return;
                }
                final User user = new User(email1,password1);
                mAuth.signInWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Log.d(TAG, "Loginberhasil");
                            Intent intent = new Intent(Login.this, HalamanUtama.class);
                            startActivity(intent);
                            SharedPreferences preferen = getSharedPreferences("masuk",MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferen.edit();
                            editor.putString("ingat","true");
                            editor.apply();
                        }
                        else {
                            Log.w(TAG, "Login gagal", task.getException());
                            Toast.makeText(Login.this, "gagal", Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }
        });

        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SingUp.class);
                startActivity(intent);
            }
        });
    }
}
