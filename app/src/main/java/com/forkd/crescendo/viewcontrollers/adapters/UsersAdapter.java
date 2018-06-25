package com.forkd.crescendo.viewcontrollers.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.widget.ANImageView;
import com.forkd.crescendo.R;
import com.forkd.crescendo.models.User;
import com.forkd.crescendo.network.CrescendoApi;
import com.forkd.crescendo.viewcontrollers.activities.ArtistDetailActivity;

import org.json.JSONObject;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private List<User> users;
    private List<String> favoriteUsers;

    private Context context;
    private String JWT;

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

    public UsersAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    public void setFavoriteUsers(List<String> favoriteUsers) {
        this.favoriteUsers = favoriteUsers;
    }

    public void setJWT(String JWT) {
        this.JWT = JWT;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ANImageView imageUser;
        private TextView textName;
        private TextView musicRole;
        private TextView musicGenre;
        private TextView age;
        private ImageView imageFavorite;

        public ViewHolder(View view) {
            super(view);
            imageUser = (ANImageView) view.findViewById(R.id.image_user);
            textName = (TextView) view.findViewById(R.id.text_name);
            musicRole = (TextView) view.findViewById(R.id.text_music_role);
            musicGenre = (TextView) view.findViewById(R.id.text_music_genre);
            age = (TextView) view.findViewById(R.id.text_age);
            imageFavorite = (ImageView) view.findViewById(R.id.image_favorite);
        }

        private void changeFavoriteIcon(boolean isFavorite) {
            if (isFavorite) {
                imageFavorite.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_favorite_black_24dp));
            } else {
                imageFavorite.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp));
            }
        }

        private boolean checkIfFavorite(User user) {
            boolean isFavorite = false;

            for(String favoriteUser : favoriteUsers){
                if(favoriteUser.equals(user.getId())) {
                    isFavorite = true;
                    break;
                }
            }

            return isFavorite;
        }

        public void changeFavorite(final User user) {
            final boolean isFavorite = checkIfFavorite(user);

            if (isFavorite)  {
                AndroidNetworking
                        .delete(CrescendoApi.deleteUserFavorite(user.getId()))
                        .addHeaders("Authorization", JWT)
                        .addBodyParameter("favourite_id", user.getId())
                        .setPriority(Priority.HIGH)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                favoriteUsers.remove(user.getId());
                                Toast.makeText(context, "Se eliminó favorito!", Toast.LENGTH_SHORT).show();
                                changeFavoriteIcon(false);
                            }

                            @Override
                            public void onError(ANError anError) {
                                Log.d("CrescendoAppFail", anError.getErrorDetail());
                                Toast.makeText(context, "Error de conexión.", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                AndroidNetworking
                        .post(CrescendoApi.setUserFavorite())
                        .addHeaders("Authorization", JWT)
                        .addBodyParameter("favourite_id", user.getId())
                        .setPriority(Priority.HIGH)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                favoriteUsers.add(user.getId());
                                Toast.makeText(context, "Se agregó favorito!", Toast.LENGTH_SHORT).show();
                                changeFavoriteIcon(true);
                            }

                            @Override
                            public void onError(ANError anError) {
                                Log.d("CrescendoAppFail", anError.getErrorDetail());
                                Toast.makeText(context, "Error de conexión.", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }

        public void updatesViews(final User user) {
            imageUser.setDefaultImageResId(R.mipmap.ic_launcher);
            imageUser.setErrorImageResId(R.mipmap.ic_launcher);
            imageUser.setImageUrl(user.getPhoto());
            textName.setText(user.getName());
            musicRole.setText(user.getRole());
            musicGenre.setText(user.getGenre());

            boolean isFavorite = checkIfFavorite(user);
            changeFavoriteIcon(isFavorite);

            age.setText("21");

            imageUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    context.startActivity(new Intent(
                            context, ArtistDetailActivity.class
                    ).putExtras(user.toBundle()));
                }
            });

            imageFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeFavorite(user);
                }
            });
        }
    }
}
