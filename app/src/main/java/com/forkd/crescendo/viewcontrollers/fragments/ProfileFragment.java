package com.forkd.crescendo.viewcontrollers.fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.forkd.crescendo.R;
import com.forkd.crescendo.network.CrescendoApi;
import com.forkd.crescendo.viewcontrollers.activities.LoginActivity;
import com.forkd.crescendo.viewcontrollers.activities.MainActivity;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private EditText newArtworkVideoId;
    private EditText newArtworkTitle;
    private EditText newArtworkDescription;

    private String JWT;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        SharedPreferences mPrefs = getActivity().getSharedPreferences("jwt", 0);
        JWT = mPrefs.getString("jwt", "");

        newArtworkVideoId = view.findViewById(R.id.new_artwork_video_id);
        newArtworkTitle = view.findViewById(R.id.new_artwork_title);
        newArtworkDescription = view.findViewById(R.id.new_artwork_description);

        view.findViewById(R.id.new_artwork_button)
            .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String videoIdString = newArtworkVideoId.getText().toString();
                    String titleString = newArtworkTitle.getText().toString();
                    String descriptionString = newArtworkDescription.getText().toString();

                    if (videoIdString.isEmpty()) {
                        Toast.makeText(getContext(), "Ingrese el id del video", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (videoIdString.length() != 11) {
                        Toast.makeText(getContext(), "Ingrese un id valido", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (titleString.isEmpty()) {
                        Toast.makeText(getContext(), "Ingrese el titulo del video", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (descriptionString.isEmpty()) {
                        Toast.makeText(getContext(), "Ingrese la descripcion del video", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    AndroidNetworking
                        .post(CrescendoApi.createUserArtwork())
                        .addHeaders("Authorization", JWT)
                        .addBodyParameter("videoId", videoIdString)
                        .addBodyParameter("title", titleString)
                        .addBodyParameter("description", descriptionString)
                        .setPriority(Priority.HIGH)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                newArtworkVideoId.setText("");
                                newArtworkTitle.setText("");
                                newArtworkDescription.setText("");
                                Toast.makeText(getContext(), "Se agregó canción!", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(ANError anError) {
                                Log.d("CrescendoAppFail", anError.getErrorDetail());
                                Toast.makeText(getContext(), "Error de conexión.", Toast.LENGTH_SHORT).show();
                            }
                        });
                }
            });
        view.findViewById(R.id.logout_button)
            .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }
            });
        return view;
    }

}
