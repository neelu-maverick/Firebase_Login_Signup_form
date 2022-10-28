package com.example.firebase_login_signup_form.integrationactivities

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.firebase_login_signup_form.dataclasses.LocationHelper
import com.example.firebase_login_signup_form.R
import com.example.firebase_login_signup_form.databinding.ActivityMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMapsBinding
    lateinit var mapFragment: SupportMapFragment
    lateinit var currentLocation: Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        Dexter.withContext(applicationContext)
            .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(permissionGrantedResponse: PermissionGrantedResponse?) {
                    getMyCurrentLocation()
                }

                override fun onPermissionDenied(permissionDeniedResponse: PermissionDeniedResponse?) {
                    TODO("Not yet implemented")
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissionRequest: PermissionRequest?,
                    permissionToken: PermissionToken?,
                ) {
                    permissionToken?.continuePermissionRequest()
                }

            }

            ).check()

    }

    private fun getMyCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        val task: Task<Location> = fusedLocationProviderClient.lastLocation

        task.addOnSuccessListener { location: Location ->
            currentLocation = location

            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            mapFragment = supportFragmentManager.findFragmentById(R.id.myMap)
                    as SupportMapFragment
            mapFragment.getMapAsync(this)

        }
    }

    override fun onMapReady(googleMap: GoogleMap) {

        val helper = LocationHelper(
            currentLocation.latitude,
            currentLocation.longitude
        )

        FirebaseDatabase.getInstance().getReference(" Current Location")
            .setValue(helper).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Location saved", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Location not saved", Toast.LENGTH_LONG).show()
                }
            }

        val latlng = LatLng(currentLocation.latitude, currentLocation.longitude)
        Log.d("LATLNG", "$latlng")
        val markerOptions: MarkerOptions =
            MarkerOptions().position(latlng).title("You are here")
        googleMap.addMarker(markerOptions)
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 12f))

    }


}