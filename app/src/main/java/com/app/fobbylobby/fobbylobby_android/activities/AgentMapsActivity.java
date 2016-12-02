package com.app.fobbylobby.fobbylobby_android.activities;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.app.fobbylobby.fobbylobby_android.R;
import com.app.fobbylobby.fobbylobby_android.models.Agent;

import java.util.ArrayList;

public class AgentMapsActivity extends AppCompatActivity {

    private GoogleMap mMap;
    private ArrayList<Agent> listItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_maps);

        Intent intent = getIntent();
        listItems = intent.getParcelableArrayListExtra("EXTRA_AGENTS");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        SupportMapFragment mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
        if (mapFragment != null) {
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap map) {
                    loadMap(map);
                }
            });
        } else {
            Toast.makeText(this, "Error - Map Fragment was null!!", Toast.LENGTH_SHORT).show();
        }
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
                Intent intent = new Intent(this, AgentListActivity.class);
                startActivity(intent);
                break;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                break;

        }
        return true;
    }

    public void loadMap(GoogleMap googleMap) {
        mMap = googleMap;

        for(Agent agent : listItems) {
            mMap.addMarker(new MarkerOptions().position(new LatLng(agent.getLatitute(), agent.getLongitute())).title
                    (agent.getName()));
        }
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(11);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(41.85, -87.635)));
        mMap.animateCamera(zoom);
    }
}
