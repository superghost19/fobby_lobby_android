package com.app.fobbylobby.fobbylobby_android.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Rating;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import com.app.fobbylobby.fobbylobby_android.R;
import com.app.fobbylobby.fobbylobby_android.models.Agent;

public class AgentDetailsActivity extends AppCompatActivity {

    private TextView agentName;
    private Agent agent;
    private TextView addressView;
    private RatingBar ratingBar;
    private MapView mapView;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_details);

        Intent intent = getIntent();
        agent = intent.getExtras().getParcelable("EXTRA_AGENT_SELECTED");

        agentName = (TextView) findViewById(R.id.agent_name);
        addressView = (TextView) findViewById(R.id.agent_address);
        ratingBar = (RatingBar) findViewById(R.id.agent_rating);
        mapView = (MapView) findViewById(R.id.map);

        agentName.setText(agent.getName());
        addressView.setText(agent.getAddress());
        ratingBar.setRating(agent.getRating());

        LatLng position = new LatLng(agent.getLatitute(), agent.getLongitute());

        mapView.onCreate(savedInstanceState);
//        if (mapView != null) {
//            googleMap = mapView.getMap();
//            googleMap.addMarker(new MarkerOptions()
//                    .anchor(0.0f, 1.0f)
//                    .position(position));
//            googleMap.getUiSettings().setMyLocationButtonEnabled(false);
//            if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) !=
//                    PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest
//                    .permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                return;
//            }
//            googleMap.setMyLocationEnabled(true);
//            googleMap.getUiSettings().setZoomControlsEnabled(true);
//            MapsInitializer.initialize(this.getApplicationContext());
//            LatLngBounds.Builder builder = new LatLngBounds.Builder();
//            builder.include(position);
//            LatLngBounds bounds = builder.build();
//            int padding = 0;
//            // Updates the location and zoom of the MapView
//            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding);
//            googleMap.moveCamera(cameraUpdate);
//        }



        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                LatLng position = new LatLng(agent.getLatitute(), agent.getLongitute());
                googleMap.addMarker(new MarkerOptions().position(position).title
                        (agent.getName()));
                CameraUpdate zoom = CameraUpdateFactory.zoomTo(11);
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(position));
                googleMap.animateCamera(zoom);
                mapView.onResume();
            }
        });
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
