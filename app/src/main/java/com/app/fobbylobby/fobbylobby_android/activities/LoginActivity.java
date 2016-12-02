package com.app.fobbylobby.fobbylobby_android.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.fobbylobby.fobbylobby_android.R;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginBtn = (Button) findViewById(R.id.loginButton);
        loginBtn.setOnClickListener(new LoginButtonListener());
    }

    public class LoginButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String userName = username.getText().toString();
            String passWord = password.getText().toString();
            if (userName.equals("abc") && passWord.equals("xyz")) {

                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}
