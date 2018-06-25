package com.forkd.crescendo.viewcontrollers.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.forkd.crescendo.R;
import com.forkd.crescendo.network.CrescendoApi;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText password;
    private EditText genre;
    private EditText role;

    String JWT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences mPrefs = getSharedPreferences("jwt", 0);
        JWT = mPrefs.getString("jwt", "");

        name = findViewById(R.id.register_text_user);
        email = findViewById(R.id.register_text_email);
        password = findViewById(R.id.register_text_password);
        genre = findViewById(R.id.register_text_genre);
        role = findViewById(R.id.register_text_role);

        findViewById(R.id.register_button)
            .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String nameSrtring = name.getText().toString();
                    String emailString = email.getText().toString();
                    String passwordString = password.getText().toString();
                    String genreString = genre.getText().toString();
                    String roleString = role.getText().toString();

                    if (nameSrtring.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Ingrese un nombre", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (emailString.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Ingrese un email", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (passwordString.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Ingrese una contraseña", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (genreString.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Ingrese un género", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (roleString.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Ingrese un rol", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String randomSex = "male";
                    String randomNumber = "16";

                    String photoString = "https://uinames.com/api/photos/" + randomSex + "/" + randomNumber + ".jpg";

                    AndroidNetworking
                            .post(CrescendoApi.createUser())
                            .addBodyParameter("name", nameSrtring)
                            .addBodyParameter("email", emailString)
                            .addBodyParameter("password", passwordString)
                            .addBodyParameter("photo", photoString)
                            .addBodyParameter("genre", genreString)
                            .addBodyParameter("role", roleString)
                            .setPriority(Priority.HIGH)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                }

                                @Override
                                public void onError(ANError anError) {
                                    Log.d("CrescendoAppFail", anError.getErrorDetail());
                                    Toast.makeText(getApplicationContext(), "Error Conexion", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            });

    }
}
