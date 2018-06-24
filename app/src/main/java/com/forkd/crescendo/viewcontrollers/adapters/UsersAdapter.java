package com.forkd.crescendo.viewcontrollers.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;
import com.forkd.crescendo.R;
import com.forkd.crescendo.models.User;
import com.forkd.crescendo.viewcontrollers.activities.ArtistDetailActivity;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private List<User> users;


    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(UsersAdapter.ViewHolder holder, int position) {
        holder.updatesViews(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public List<User> getUsers() {
        return users;
    }

    public UsersAdapter setUsers(List<User> users) {
        this.users = users;
        return this;
    }

    public UsersAdapter() {

    }

    public UsersAdapter(List<User> users) {
        this.users = users;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ANImageView imageUser;
        private TextView textName;
        private TextView musicRole;
        private TextView musicGenre;
        private TextView age;
        private ConstraintLayout userLayout;
        public ViewHolder(View view) {
            super(view);
            imageUser = (ANImageView) view.findViewById(R.id.image_user);
            textName = (TextView) view.findViewById(R.id.text_name);
            musicRole = (TextView) view.findViewById(R.id.text_music_role);
            musicGenre = (TextView) view.findViewById(R.id.text_music_genre);
            age = (TextView) view.findViewById(R.id.text_age);
            userLayout = (ConstraintLayout) view.findViewById(R.id.layout_user);
        }

        public void updatesViews(final User user) {
            imageUser.setDefaultImageResId(R.mipmap.ic_launcher);
            imageUser.setErrorImageResId(R.mipmap.ic_launcher);
            imageUser.setImageUrl(user.getPhoto());
            textName.setText(user.getName());
            musicRole.setText(user.getRole());
            musicGenre.setText(user.getGenre());
            age.setText("21");
            userLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    context.startActivity(new Intent(
                            context, ArtistDetailActivity.class
                    ).putExtras(user.toBundle()));
                }
            });
        }
    }
}
