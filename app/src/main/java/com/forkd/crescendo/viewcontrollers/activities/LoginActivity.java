package com.forkd.crescendo.viewcontrollers.activities;

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextUser = (EditText) findViewById(R.id.text_user);
        editTextPassword = (EditText) findViewById(R.id.text_password);


        findViewById(R.id.button_login)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isAuthorized(editTextUser.getText().toString(),
                                editTextPassword.getText().toString())) {
                            startActivity(new Intent(v.getContext(), MainActivity.class));
                        }
                    }
                });
    }

    private boolean isAuthorized(String user, String password) {

        AndroidNetworking
                .post(CrescendoApi.authentication())
                .addHeaders("Accept", "application/json")
                .addHeaders("Content-Type", "application/json")
                .addBodyParameter("strategy", "local")
                .addBodyParameter("email", user)
                .addBodyParameter("password", password)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("CrescendoAppSuccess", response.getString("accessToken"));

                            writeToSharedPreferences(response.getString("accessToken"));
                            isAllowed = true;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("CrescendoAppFail", anError.getErrorDetail());
                        Toast.makeText(LoginActivity.this, "User and password is not correct",
                                Toast.LENGTH_SHORT).show();
                    }
                });

        return isAllowed;
    }

    private void writeToSharedPreferences(String accessToken) {

//        SharedPreferences sharedPref = LoginActivity.this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = getSharedPreferences("accesstoken", MODE_PRIVATE).edit();

        editor.putString("jwt", accessToken);
        editor.commit();
    }

}
