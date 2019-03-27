package com.magicbytes.githubcontributor.ui.geo

import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.magicbytes.githubcontributor.R

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val location = intent.getStringExtra("Location")
        val list = Geocoder(this).getFromLocationName(location, 1)
        val coordinates = LatLng(list[0].latitude, list[0].longitude)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinates))
        mMap.addMarker(MarkerOptions().position(coordinates).title("Marker in $location"))
    }

    companion object {
        fun start(context: Context, location: String) {
            val intent = Intent(context, MapsActivity::class.java)
            intent.putExtra("Location", location)
            context.startActivity(intent)
        }
    }
}
