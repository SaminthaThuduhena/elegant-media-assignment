package com.elegantmedia.test.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.elegantmedia.test.R;
import com.elegantmedia.test.base.BaseActivity;
import com.elegantmedia.test.databinding.ActivityMainBinding;
import com.elegantmedia.test.model.Datum;
import com.elegantmedia.test.ui.hoteldetail.HotelDetailFragment;
import com.elegantmedia.test.ui.hotels.HotelsFragment;
import com.elegantmedia.test.ui.map.MapsActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends BaseActivity implements HotelsFragment.FragmentEventListener
        , HotelDetailFragment.FragmentEventListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding mMainBinding;
    private Datum mDatum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainBinding = ActivityMainBinding.bind(findViewById(R.id.main_activity));

        init();

        startFragment(R.id.container, HotelsFragment.newInstance(), false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        MapsActivity.startIntentActivity(MainActivity.this, mDatum);
        return super.onOptionsItemSelected(item);
    }

//  FragmentEventListener of Hotels fragment
    @Override
    public void onMenuDetailFragment(Datum datum) {
        startFragment(R.id.container, HotelDetailFragment.newInstance(datum), true);
    }

//  FragmentEventListener of HotelDetail fragment
    @Override
    public void onHotelAdded(Datum datum) {
        mDatum = datum;
    }

    private void init() {
    }
}