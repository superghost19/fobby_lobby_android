package com.app.fobbylobby.fobbylobby_android.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.app.fobbylobby.fobbylobby_android.R;
import com.app.fobbylobby.fobbylobby_android.adapters.AgentListAdapter;
import com.app.fobbylobby.fobbylobby_android.models.Agent;
import com.app.fobbylobby.fobbylobby_android.services.MySQLHelper;

import java.util.ArrayList;

public class AgentListActivity extends AppCompatActivity {

    private ListView listView;
    private ListAdapter listAdapter;

    private ArrayList<Agent> listItems = new ArrayList<Agent>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        makeChangesToDB();
        bindViews();
    }

    private void bindViews() {
        listView = (ListView) findViewById(R.id.book_list);
        listAdapter = new AgentListAdapter(AgentListActivity.this, listItems);

        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new MyOnItemClickListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Toolbar tb = (Toolbar) findViewById(R.id.my_toolbar);
        tb.inflateMenu(R.menu.agent_list_menu);
        tb.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return onOptionsItemSelected(item);
                    }
                });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                break;

            case R.id.action_map_view:
                Intent intent = new Intent(this, AgentMapsActivity.class);
                intent.putParcelableArrayListExtra("EXTRA_AGENTS", listItems);
                startActivity(intent);
                break;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                break;

        }
        return true;
    }

    private void makeChangesToDB() {
        MySQLHelper db = new MySQLHelper(this);
        /** CRUD Operations **/
        // add Books
        if(db.getIds() == 0) {
            db.addAgent(new Agent("Centurion Realty & Estates", "2325 S Michigan Ave", 41.8571078, -87.6243462, 5));
            db.addAgent(new Agent("CENTURY 32 S.G.R., Inc", "1823 S Michigan Ave", 41.8571698, -87.6236542, 3));
            db.addAgent(new Agent("Bricks Realty", "3107 S Morgan St", 41.8377042, -87.6531397, 4));
            db.addAgent(new Agent("Dream Town Realty", "1950 N Sedgwick St", 41.9179905, -87.6390923, 2));
            db.addAgent(new Agent("Baird & Warner Real Estate", "120 S La Salle St", 41.8804696, -87.6327948, 5));
            db.addAgent(new Agent("Coldwell Banker Residential Brokerage", "1314 E 47th St", 41.8104711, -87.5942225,
                    3));
            db.addAgent(new Agent("Conlon: A Real Estate Company", "401 W Ontario St #400", 41.89287, -87.6387435,
                    4));
            db.addAgent(new Agent("Davis Russell REal Estate & Management, LLC", "3473 S king Drive #304", 41.8490542,
                    -87.6392181, 2));
            db.addAgent(new Agent("Ultimate Real Estate Group", "400 E 41st St", 41.8490116, -87.6392182, 5));
            db.addAgent(new Agent("CONLON/Christie's International Real Estate", "401 W Ontario St", 41.8489691, -87.6392184, 3));
        }
        listItems = db.getAllAgents();
    }

    public class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
            Agent agentSelected = listItems.get(pos);
            Intent intent = new Intent(AgentListActivity.this, AgentDetailsActivity.class);
            intent.putExtra("EXTRA_AGENT_SELECTED", agentSelected);
            startActivity(intent);
        }
    }
}
