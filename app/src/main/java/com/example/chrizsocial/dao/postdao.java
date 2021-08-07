package com.example.chrizsocial.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class postdao {



    FirebaseFirestore db = FirebaseFirestore.getInstance();
     FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private String TAG;
    public CollectionReference postCollections = db.collection("posts");

    public  void createPost(String text)
        {
            Map<String, Object> post = new HashMap<>();
            post.put("creator", mAuth.getCurrentUser().getUid());
            post.put("userNameOfCreator", mAuth.getCurrentUser().getDisplayName());
            post.put("userImageOfCreator", mAuth.getCurrentUser().getPhotoUrl().toString());
            post.put("timestamp", Calendar.getInstance().getTimeInMillis());
            post.put("text", text);

            db.collection("posts")
                    .add(post)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });
        }
}
