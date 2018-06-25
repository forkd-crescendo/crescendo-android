package com.forkd.crescendo.viewcontrollers.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUser;
    private EditText editTextPassword;
    private boolean isAllowed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextUser = findViewById(R.id.text_user);
        editTextPassword = findViewById(R.id.text_password);

        editTextUser.setText("isac@windler.net");
        editTextPassword.setText("password123");

        findViewById(R.id.button_login)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String userText = editTextUser.getText().toString();
                        String passwordText = editTextPassword.getText().toString();

                        if (userText.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Ingrese al usuario", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (passwordText.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Ingrese la contraseña", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        isAuthorized(userText, passwordText);
                    }
                });
    }

    private boolean isAuthorized(String user, String password) {
        AndroidNetworking
                .post(CrescendoApi.authentication())
                .addBodyParameter("email", user)
                .addBodyParameter("password", password)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("CrescendoAppSuccess", response.getString("auth_token"));
                            writeToSharedPreferences(response.getString("auth_token"));
                            isAllowed = true;
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("CrescendoAppFail", anError.getErrorDetail());
                        Toast.makeText(getApplicationContext(), "Usuario o Contraseña incorrecta.", Toast.LENGTH_SHORT).show();
                    }
                });

        return isAllowed;
    }

    private void writeToSharedPreferences(String accessToken) {
        Activity activity = this;

        SharedPreferences mPrefs = getSharedPreferences("jwt", 0);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString("jwt", accessToken);
        editor.commit();
    }

}
