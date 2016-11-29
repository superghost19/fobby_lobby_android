package com.app.fobbylobby.fobbylobby_android.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.fobbylobby.fobbylobby_android.R;

public class HomeActivity extends AppCompatActivity {
    private Button nearbyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nearbyButton = (Button) findViewById(R.id.nearbyAgentButton);
        nearbyButton.setOnClickListener(new MyOnClickListener());
    }

    class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, AgentListActivity.class);
            startActivity(intent);
        }
    }
}
