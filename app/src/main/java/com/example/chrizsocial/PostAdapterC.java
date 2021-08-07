package com.example.chrizsocial;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.net.URI;
import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chrizsocial.models.Post;
import com.example.chrizsocial.MainActivity;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class PostAdapterC extends FirestoreRecyclerAdapter<Post, PostAdapterC.PostViewHolder> {
    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;
    FirebaseUser user  = FirebaseAuth.getInstance().getCurrentUser();
    public PostAdapterC(@NonNull @NotNull FirestoreRecyclerOptions<Post> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull PostViewHolder holder, int position, @NonNull @NotNull Post model) {
    holder.posttextX.setText(model.getTextPost());
    holder.usernameX.setText(model.getUserNameOfCreator());
    holder.timestampX.setText(Utils.getTimeAgo(model.getTimestamp()));
        Uri myUri;
        Glide.with(holder.userimageX.getContext()).load(myUri = Uri.parse(model.getUserImageOfCreator())).circleCrop().into(holder.userimageX);
    }
    @NonNull
    @NotNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == MSG_TYPE_RIGHT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.youritempost, parent, false);
        }
        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itempost, parent, false);
        }
        return new PostViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        if(getItem(position).getCreator().equals(user.getUid()))
            return MSG_TYPE_RIGHT;
        else
            return MSG_TYPE_LEFT;
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
         TextView usernameX;
         TextView timestampX;
         TextView posttextX;
         ImageView userimageX;
        public PostViewHolder(View itemView) {
            super(itemView);
            usernameX = (TextView) itemView.findViewById(R.id.userName);
            timestampX = (TextView) itemView.findViewById(R.id.timeStamp);
            posttextX = (TextView) itemView.findViewById(R.id.postTitle);
            userimageX = (ImageView) itemView.findViewById(R.id.userImage);
        }
    }
}

