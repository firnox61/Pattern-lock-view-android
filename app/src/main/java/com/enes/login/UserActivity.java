package com.enes.login;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;



public class UserActivity extends AppCompatActivity {

    TextView a,b,c;
    Button Getirbuton;
    String mailadi;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
   // DocumentReference noteRef=db.collection("user").document("dedede@gmail.com");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Getirbuton=findViewById(R.id.GetirDeger);

        c=findViewById(R.id.yas);
        a=findViewById(R.id.adsoyad);
        b=findViewById(R.id.cinsiyet);
        //txtKullanici=(TextView)findViewById(R.id.mailad);
        Intent gelenIntent=getIntent();
        mailadi=gelenIntent.getStringExtra("d1");
        String sonmail=mailadi.replace("x","");
       // txtKullanici.setText(mailadi);





        Getirbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference docRef = db.collection("sondeneme").document(sonmail);
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                //  Log.d(TAG, "DocumentSnapshot data: " + document.getData().get("UserName"));
                                String isim=document.getString("UserName");
                                String yas=document.getString("UserAge");
                                String cinsiyet=document.getString("UserSex");
                                a.setText(isim);
                                b.setText(cinsiyet);
                                c.setText(yas);

                                // a.setText((String) document.getData().get("UserName"));
                                // b.setText((String) document.getData().get("UserSex"));
                                // c.setText((String) document.getData().get("UserAge"));
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });





            }
        });




    }
}               /* noteRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            String title=documentSnapshot.getString()

                        }
                        else {
                            Toast.makeText(UserActivity.this, "Dokuman bulunammadı", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });*/