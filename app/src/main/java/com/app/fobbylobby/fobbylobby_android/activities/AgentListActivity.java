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
import java.util.List;

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
        listView = (ListView) findViewById(R.id.bookList);
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
            db.addAgent(new Agent("Zhou Agent", "500 W China Plaza", 40.1, 50.2, 5));
            db.addAgent(new Agent("Zhang Agent", "2000 N State St", 40.2, 50.3, 3));
            db.addAgent(new Agent("Buy New Home Real Estate", "1234 W Madison St", 40.3, 50.4, 4));
            db.addAgent(new Agent("EasySell Real Estate", "567 W Monroe", 40.4, 50.5, 2));
        }
        // get all books
//        List<Agent> list = db.getAllAgents();
//        // update one book
//        int j = db.updateBook(list.get(2), "Liu Agent", "501 W China Plaza", 50.6, 40.5, 5);
//        // delete one book
//        db.deleteAgent(list.get(0));
        // get all books
        listItems = db.getAllAgents();
    }

    public class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
//            String agentName = parent.getItemAtPosition(pos).toString();
            String agentName = listItems.get(pos).getName();
            Intent intent = new Intent(AgentListActivity.this, AgentDetailsActivity.class);
            intent.putExtra("EXTRA_AGENT_NAME", agentName);
            startActivity(intent);
        }
    }
}
