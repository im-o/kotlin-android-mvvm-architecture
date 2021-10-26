package com.rivaldy.id.mvvmtemplateapp.utils

import android.Manifest
import android.content.Context
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.maps.model.LatLng
import com.rivaldy.id.mvvmtemplateapp.R
import com.rivaldy.id.mvvmtemplateapp.ui.CameraLocationActivity.Companion.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
import com.rivaldy.id.mvvmtemplateapp.utils.UtilExtensions.myToast
import com.rivaldy.id.mvvmtemplateapp.utils.UtilFunctions.loge
import java.util.*

/**
 * Created by rivaldy on 23/10/21
 * Find me on my Github -> https://github.com/im-o
 **/

object UtilMaps {
    fun checkedPermittedMap(activity: AppCompatActivity, listener: UtilListener.ICheckedPermittedMap) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            listener.onPermittedGranted()
        } else {
            listener.onPermittedDenied()
        }
    }

    fun mapResultPermission(activity: AppCompatActivity) {
        return ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
        )
    }

    fun displayLocationSettingsRequest(activity: AppCompatActivity) {
        activity.let {
            val locationRequest = LocationRequest.create()
            locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

            val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
            builder.setAlwaysShow(true)
            val task = LocationServices.getSettingsClient(it).checkLocationSettings(builder.build())
            task.addOnSuccessListener { response ->
                val states = response.locationSettingsStates
                if (states?.isLocationPresent == true) {
                    activity.myToast("addOnSuccessListener : 1")
                } else {
                    activity.myToast("addOnSuccessListener : 2")
                }
            }.addOnFailureListener { e ->
                if (e is ResolvableApiException) {
                    try {
                        e.startResolutionForResult(it, LOCATION_SETTING_REQUEST)
                    } catch (sendEx: IntentSender.SendIntentException) {
                        activity.myToast("addOnFailureListener 1 -> $sendEx")
                    }
                } else {
                    activity.myToast("addOnFailureListener 2 -> ${e.message}")
                }
            }

        }
    }

    fun getCurrentMyLocation(context: Context, listener: UtilListener.IMyLatLong) {
        val locationManager = context.getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
        val criteria = Criteria()
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        val location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false) ?: LocationManager.GPS_PROVIDER)

        if (location != null) {
            val lat = location.latitude
            val lng = location.longitude
            val latLng = LatLng(lat, lng)
            loge("INIII getCurrentMyLocation : $lat $lng")
            listener.onLoadLatLong(latLng, true)

        } else { //getLastKnownLocation
            loge("setMyLastLocation: excecute, and get last location")
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
            fusedLocationClient.lastLocation.addOnSuccessListener { loc: Location? ->
                if (loc != null) {
                    val lat = loc.latitude
                    val lng = loc.longitude
                    val latLng = LatLng(lat, lng)
                    loge("INIII getLastKnownLocation : $lat $lng")
                    listener.onLoadLatLong(latLng, false)
                } else {
                    listener.onErrorLatLong()
                }
            }
        }
    }

    fun getLatLongAddress(context: Context, lat: Double, lng: Double): String {
        val geo = Geocoder(context, Locale("in", "ID"))
        loge("All data : $geo")
        return try {
            val myList = geo.getFromLocation(lat, lng, 1)
            val obj = myList[0] as Address
            obj.getAddressLine(0)
        } catch (er: Exception) {
            context.myToast("This problem : ${er.message}")
            context.resources.getString(R.string.no_address)
        }
    }

    private const val LOCATION_SETTING_REQUEST = 999
}