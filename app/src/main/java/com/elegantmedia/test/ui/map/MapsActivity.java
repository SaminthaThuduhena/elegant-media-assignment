package com.elegantmedia.test.ui.map;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.elegantmedia.test.R;
import com.elegantmedia.test.model.Datum;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.elegantmedia.test.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String KEY_DATUM = "com.elegantmedia.test.ui.map.KEY_DATUM";
    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    public static void startIntentActivity(Context context, Datum datum) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_DATUM, datum);
        Intent intent = new Intent(context, MapsActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if(intent == null || intent.getExtras() == null)
            showError(getString(R.string.data_null));

        Bundle bundle = intent.getExtras();

        Datum datum = bundle.getParcelable(KEY_DATUM);

        // Add a marker in to set the location
        LatLng location = new LatLng(Double.parseDouble(datum.getLatitude()), Double.parseDouble(datum.getLongitude()));
        mMap.addMarker(new MarkerOptions().position(location).title(datum.getAddress()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));//Store these lat lng values somewhere. These should be constant.
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                location, 15);
        mMap.animateCamera(cameraUpdate);
    }

    private void showError(String message) {
        Toast.makeText(MapsActivity.this, message, Toast.LENGTH_LONG).show();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        onNewIntent(getIntent());
    }
}