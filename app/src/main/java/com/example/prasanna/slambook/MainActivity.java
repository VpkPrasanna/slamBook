package com.example.prasanna.slambook;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText Name;
    EditText Age;
    EditText Dob;
    EditText Phone;
    EditText Clg;
    EditText Year;
    Button Bt;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name = findViewById(R.id.name);
        Age = findViewById(R.id.age);
        Dob = findViewById(R.id.dob);
        Phone = findViewById(R.id.phone);
        Clg = findViewById(R.id.clg);
        Year = findViewById(R.id.year);
        Bt = findViewById(R.id.btn);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {slamBook();}
        });
    }
    private void slamBook(){
        String name = Name.getText().toString().trim();
        String age = Age.getText().toString().trim();
        String dob = Dob.getText().toString().trim();
        String phone = Phone.getText().toString().trim();
        String clg = Clg.getText().toString().trim();
        String year = Year.getText().toString().trim();
          if (!name.isEmpty() && !age.isEmpty()) {
              String id = databaseReference.push().getKey();
              Map<String, String> dataMap = new HashMap<>();
              dataMap.put("name", name);
              dataMap.put("age", age);
              dataMap.put("dob", dob);
              dataMap.put("phone", phone);
              dataMap.put("clg", clg);
              dataMap.put("year", year);
              databaseReference.child(id).setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                  @Override
                  public void onComplete(@NonNull Task<Void> task) {
                      Toast.makeText(MainActivity.this, "Successfully updated :)", Toast.LENGTH_SHORT).show();
                  }
              }).addOnFailureListener(new OnFailureListener() {
                  @Override
                  public void onFailure(@NonNull Exception e) {
                      Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                  }
              });
          }
              else{
              Toast.makeText(this,"Some thing is missed",Toast.LENGTH_SHORT).show();
          }
    }
}
