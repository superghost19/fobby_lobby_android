package com.app.fobbylobby.fobbylobby_android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.app.fobbylobby.fobbylobby_android.R;

public class AgentDetailsActivity extends AppCompatActivity {

    private TextView agentName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_details);

        Intent intent = getIntent();

        agentName = (TextView) findViewById(R.id.agent_name);
        String agentName = intent.getStringExtra("EXTRA_AGENT_NAME");
        this.agentName.setText(agentName);
    }
}
