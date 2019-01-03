package com.example.locationservices

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.location.*
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationSettingsRequest
import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.android.gms.location.LocationSettingsStatusCodes
import android.content.IntentSender
import android.content.pm.PackageManager
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.common.api.ApiException
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnFailureListener
import android.os.Looper
import android.view.View
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.OnCompleteListener
import org.jetbrains.anko.alert
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.okButton


class MainActivity : AppCompatActivity() {

// https://github.com/googlesamples/android-play-location/blob/master/LocationUpdates/app/src/main/java/com/google/android/gms/location/sample/locationupdates/MainActivity.java

    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var mSettingsClient: SettingsClient
    private lateinit var mLocationRequest: LocationRequest
    private lateinit var mLocationSettingsRequest: LocationSettingsRequest
    private lateinit var mLocationCallback: LocationCallback
    private lateinit var mCurrentLocation: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mSettingsClient = LocationServices.getSettingsClient(this)

        createLocationCallback()
        createLocationRequest()
        buildLocationSettingsRequest()

        if (checkPermissions()) {
            startLocationUpdates()
        } else  {
            requestPermissions()
        }
    }

    override fun onStop() {

        stopLocationUpdates()
        super.onStop()
    }

    private fun createLocationRequest() {
        mLocationRequest = LocationRequest()
        mLocationRequest.interval = 1000
        mLocationRequest.fastestInterval = 1000
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    private fun createLocationCallback() {
        mLocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                super.onLocationResult(locationResult)

                mCurrentLocation = locationResult!!.lastLocation
                Log.i("@codekul", "Latitude ${mCurrentLocation.latitude} Longitude ${mCurrentLocation.longitude}")

            }
        }
    }

    private fun buildLocationSettingsRequest() {
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest)
        mLocationSettingsRequest = builder.build()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            // Check for the integer request code originally supplied to startResolutionForResult().
            123 -> when (resultCode) {
                Activity.RESULT_OK -> Log.i("@codekul", "User agreed to make required location settings changes.")
                Activity.RESULT_CANCELED -> {
                    Log.i("@codekul", "User chose not to make required location settings changes.")
                }
            }// Nothing to do. startLocationupdates() gets called in onResume again.
        }
    }

    private fun startLocationUpdates() {
        // Begin by checking if the device has the necessary location settings.
        mSettingsClient.checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(this) {
                    Log.i("@codekul", "All location settings are satisfied.")

                    if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                        return@addOnSuccessListener
                    }
                    mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                                mLocationCallback, Looper.myLooper())
                }
                .addOnFailureListener(this) { e ->
                    val statusCode = (e as ApiException).statusCode
                    when (statusCode) {
                        LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                            Log.i("@codekul", "Location settings are not satisfied. Attempting to upgrade " + "location settings ")
                            try {
                                // Show the dialog by calling startResolutionForResult(), and check the
                                // result in onActivityResult().
                                val rae = e as ResolvableApiException
                                rae.startResolutionForResult(this@MainActivity, 123)
                            } catch (sie: IntentSender.SendIntentException) {
                                Log.i("@codekul", "PendingIntent unable to execute request.")
                            }

                        }
                        LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                            val errorMessage = "Location settings are inadequate, and cannot be " + "fixed here. Fix in Settings."
                            Log.e("@codekul", errorMessage)
                            Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_LONG).show()
                        }
                    }

                }
    }

    private fun stopLocationUpdates() {
        mFusedLocationClient.removeLocationUpdates(mLocationCallback)
                .addOnCompleteListener(this) {

                }
    }

    private fun checkPermissions() : Boolean {
        val permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
        return permissionState == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        val shouldProvideRationale = ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)

        if (shouldProvideRationale) {
            alert {
                okButton {
                    ActivityCompat.requestPermissions(this@MainActivity,
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                            1234)
                }
                cancelButton {
                    it.dismiss()
                }
            }.show()

        } else {
            Log.i("@codekul", "Requesting permission")
            ActivityCompat.requestPermissions(this@MainActivity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    1234)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            1234 -> if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates()
            }
        }
    }
}
