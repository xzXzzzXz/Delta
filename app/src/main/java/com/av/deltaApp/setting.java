package com.av.deltaApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class setting extends AppCompatActivity {
    EditText setname, setstatus;
    Button donebut;
    FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;

    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://deltaapp-ed69e-default-rtdb.europe-west1.firebasedatabase.app");

        setname = findViewById(R.id.settingname);
        setstatus = findViewById(R.id.settingstatus);
        donebut = findViewById(R.id.donebutt);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.setCancelable(false);

        DatabaseReference reference = database.getReference().child("user").child(Objects.requireNonNull(auth.getUid()));

        // Preuzimanje podataka korisnika iz baze
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                email = Objects.requireNonNull(snapshot.child("mail").getValue()).toString();
                password = Objects.requireNonNull(snapshot.child("password").getValue()).toString();
                String name = Objects.requireNonNull(snapshot.child("userName").getValue()).toString();
                String status = Objects.requireNonNull(snapshot.child("status").getValue()).toString();
                setname.setText(name);
                setstatus.setText(status);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(setting.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });

        // Spremanje ažuriranih podataka u bazu
        donebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();

                String name = setname.getText().toString();
                String status = setstatus.getText().toString();

                Users users = new Users(auth.getUid(), name, email, password, status);
                reference.setValue(users).addOnCompleteListener(task -> {
                    progressDialog.dismiss();
                    if (task.isSuccessful()) {
                        Toast.makeText(setting.this, "Data saved successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(setting.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(setting.this, "Failed to save data", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
