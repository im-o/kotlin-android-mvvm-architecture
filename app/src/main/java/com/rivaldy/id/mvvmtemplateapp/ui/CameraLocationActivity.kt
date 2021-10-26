package com.rivaldy.id.mvvmtemplateapp.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.LocationManager
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.gms.maps.model.LatLng
import com.rivaldy.id.mvvmtemplateapp.R
import com.rivaldy.id.mvvmtemplateapp.base.BaseActivity
import com.rivaldy.id.mvvmtemplateapp.data.network.DataResource
import com.rivaldy.id.mvvmtemplateapp.databinding.ActivityCameraLocationBinding
import com.rivaldy.id.mvvmtemplateapp.utils.UtilConstants.MY_LAT
import com.rivaldy.id.mvvmtemplateapp.utils.UtilConstants.MY_LNG
import com.rivaldy.id.mvvmtemplateapp.utils.UtilFunctions.openAlertDialogExit
import com.rivaldy.id.mvvmtemplateapp.utils.UtilListener
import com.rivaldy.id.mvvmtemplateapp.utils.UtilMaps.checkedPermittedMap
import com.rivaldy.id.mvvmtemplateapp.utils.UtilMaps.displayLocationSettingsRequest
import com.rivaldy.id.mvvmtemplateapp.utils.UtilMaps.getCurrentMyLocation
import com.rivaldy.id.mvvmtemplateapp.utils.UtilMaps.getLatLongAddress
import com.rivaldy.id.mvvmtemplateapp.utils.UtilMaps.mapResultPermission

class CameraLocationActivity : BaseActivity<ActivityCameraLocationBinding>() {
    override fun getViewBinding() = ActivityCameraLocationBinding.inflate(layoutInflater)
    private var myLatLong: LatLng? = null
    private var myLocationName: String? = null

    override fun initView() {
        checkPermitted()
        initClick()
    }

    override fun initObservers() {
    }

    override fun showFailure(failure: DataResource.Failure) {
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (permissions.size == 1 && permissions[0] == android.Manifest.permission.ACCESS_FINE_LOCATION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                }
                displayLocationSettingsRequest(this)
            } else {
                checkPermitted()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                if (requestCode == ImagePicker.REQUEST_CODE) {
                    val uri: Uri = data?.data!!
                    val fileName = uri.path.toString()
                    val imageStream = contentResolver.openInputStream(uri)
                    val imageBitmap = BitmapFactory.decodeStream(imageStream)
                    val infoImage = "File Name : $fileName\n\nLatLong : $myLatLong\n\nLocation Name : $myLocationName"
                    Glide.with(binding.root.context)
                        .load(imageBitmap)
                        .placeholder(R.color.colorDividerHigh)
                        .into(binding.imageIV)
                    binding.infoTV.text = infoImage
                }
            }
        }
    }

    private fun checkPermitted() {
        checkedPermittedMap(this, object : UtilListener.ICheckedPermittedMap {
            override fun onPermittedGranted() {}
            override fun onPermittedDenied() {
                val title = resources.getString(R.string.map_req_title)
                val msg = resources.getString(R.string.map_req_desc)
                openAlertDialogExit(this@CameraLocationActivity, title, msg, object : UtilListener.IDialogButtonClickListener {
                    override fun onPositiveButtonClick() {
                        mapResultPermission(this@CameraLocationActivity)
                    }

                    override fun onNegativeButtonClick() {
                        showLoading(true)
                        Handler(Looper.getMainLooper()).postDelayed({
                            showLoading(false)
                            finish()
                        }, 1000)
                    }
                })
            }
        })
    }

    private fun initClick() {
        binding.openCameraMB.setOnClickListener {
            if (checkGPSOn()) {
                getCurrentLocation()
            } else {
                val title = resources.getString(R.string.gps_req_title)
                val msg = resources.getString(R.string.gps_req_desc)
                openAlertDialogExit(this@CameraLocationActivity, title, msg, object : UtilListener.IDialogButtonClickListener {
                    override fun onPositiveButtonClick() {
                        startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                    }

                    override fun onNegativeButtonClick() {
                        showLoading(true)
                        Handler(Looper.getMainLooper()).postDelayed({
                            showLoading(false)
                            finish()
                        }, 1000)
                    }
                })
            }
        }
    }

    private fun checkGPSOn(): Boolean {
        val manager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun getCurrentLocation() {
        getCurrentMyLocation(this, object : UtilListener.IMyLatLong {
            override fun onLoadLatLong(latLng: LatLng?, isCurrentLocation: Boolean) {
                myLatLong = latLng
                myLocationName = getLatLongAddress(this@CameraLocationActivity, latLng?.latitude ?: MY_LAT, latLng?.longitude ?: MY_LNG)
                ImagePicker.with(this@CameraLocationActivity)
                    //.cameraOnly()
                    .compress(INT_SIZE_500_KB)
                    .maxResultSize(INT_SIZE_640_PX, INT_SIZE_640_PX)
                    .start()
            }

            override fun onErrorLatLong() {

            }
        })
    }

    companion object {
        const val INT_SIZE_500_KB = 512
        const val INT_SIZE_640_PX = 640
        const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 100
    }
}