package com.example.chrizsocial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.chrizsocial.dao.postdao;
import com.example.chrizsocial.models.Post;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MainActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    private CollectionReference postsCollections = db.collection("posts");
    private PostAdapterC adapter;
    FloatingActionButton floatingActionButton;
    AutoCompleteTextView autoCompleteTextView;
    RecyclerView recyclerView;
    Button scrolldown;
    ImageView profilepic;
    TextView profileusername;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    com.example.chrizsocial.dao.postdao postdao = new postdao();
    private androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navmenu);
        navigationView.bringToFront();
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        setUpDrawerMenuHeader();
        navigationView.setNavigationItemSelectedListener(this);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView2);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String postText = autoCompleteTextView.getText().toString();
                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                if (postText.toString().isEmpty()) {
                    autoCompleteTextView.setHint("No text typed, Please type something...");
                } else {
                    postdao.createPost(postText);
                    autoCompleteTextView.setText("");
                    autoCompleteTextView.setHint("Type something...");
                    recyclerView.smoothScrollToPosition(Objects.requireNonNull(recyclerView.getAdapter()).getItemCount());
                }
            }
        });
        setupRecyclerView();

        scrolldown = findViewById(R.id.scrolldown);
        scrolldown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.smoothScrollToPosition(Objects.requireNonNull(recyclerView.getAdapter()).getItemCount());
            }
        });
    }

    private void setUpDrawerMenuHeader() {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        View headerView = navigationView.getHeaderView(0);
        profilepic = headerView.findViewById(R.id.profilePic);
        assert currentUser != null;
        Glide.with(profilepic.getContext()).load(currentUser.getPhotoUrl()).circleCrop().into(profilepic);
        profileusername = headerView.findViewById(R.id.usernameProfile);
        profileusername.setText(currentUser.getDisplayName());
    }

    private void setupRecyclerView() {
        Query query = postsCollections.orderBy("timestamp",Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Post> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Post>().setQuery(query,Post.class).build();
        adapter = new PostAdapterC(firestoreRecyclerOptions);
         recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(null);
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =  getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.profile: {
                break;
            }

            case R.id.friends:{
                break;
            }

            case R.id.users:{
                break;
            }

            case R.id.logoutMenu: {
                FirebaseAuth.getInstance().signOut();
                Intent previous = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(previous);
                break;
            }
        }
        return true;
    }
}