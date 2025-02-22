package com.school.sensorsapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.school.sensorsapp.R
import com.school.sensorsapp.data.api.sensors.SensorsAPIService
import com.school.sensorsapp.databinding.ActivityMainBinding
import com.school.sensorsapp.model.Sensor
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Circle
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.CameraUpdateReason
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MainActivity : AppCompatActivity(), CameraListener {
    private var binding: ActivityMainBinding? = null
    private val startLocation = Point(52.646505, 42.680170)
    private val zoomValue = 16.5f
    var pm25 = 0
    var pm10 = 0
    var airQuality = 0
    var temperature = 0
    var humidity = 0
    var pressure = 0
    var latitude = 0
    var longitude = 0
    var altitude = 0
    private var mapObjectCollection: MapObjectCollection? = null
    private var placemarkMapObject: PlacemarkMapObject? = null
    var circle = Circle(
        Point(52.646505, 42.680170),
        100f
    )
    private var mapView: MapView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("1f1b1543-6db3-42a5-adcb-cee50acb0253")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.getRoot())
        mapView = binding!!.mapview
        val mapObjects = mapView!!.map.mapObjects
        mapObjects.addCircle(circle).apply {
            strokeWidth = 2f
//            if (0 < airQuality < 50) {
            strokeColor = ContextCompat.getColor(this@MainActivity, R.color.grey)
            fillColor = ContextCompat.getColor(this@MainActivity, R.color.green)
//            }
        }
//        binding.imageBtn.setOnClickListener(val intent  = Intent(this@MainActivity,Profile::class.java)
//        startActivity(intent))
        moveToStartLocation()
        setMarkerInStartLocation()
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding!!.mapview.onStart()
    }

    override fun onStop() {
        binding!!.mapview.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    private fun moveToStartLocation() {
        binding!!.mapview.map.move(
            CameraPosition(startLocation, zoomValue, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 5f),
            null
        )
    }

    private fun setMarkerInStartLocation() {
        val marker = R.drawable.pin
        mapObjectCollection =
            binding!!.mapview.map.mapObjects
        placemarkMapObject = mapObjectCollection!!.addPlacemark(
            startLocation,
            ImageProvider.fromResource(this, marker)
        )
        placemarkMapObject!!.opacity = 0.5f
    }

    private fun updateUI(sensor: Sensor) {
        pm25 = Math.round(sensor.pm25)
        pm10 = Math.round(sensor.pm10)
        airQuality = Math.round(sensor.airQuality)
        temperature = Math.round(sensor.temperature)
        humidity = Math.round(sensor.humidity)
        pressure = Math.round(sensor.pressure)
        latitude = Math.round(sensor.latitude)
        longitude = Math.round(sensor.longitude)
        altitude = Math.round(sensor.altitude)
        binding!!.humidity.text = getString(R.string.humidity_value, humidity) + "%"
        binding!!.temperature.text = getString(R.string.temperature_value, temperature) + "°C"
        binding!!.value1.text = getString(R.string.pm25_value, pm25)
        binding!!.value2.text = getString(R.string.pm10_value, pm10)
    }

    private fun fetchSensorData() {
        SensorsAPIService.getInstance().getLast().enqueue(object : Callback<Sensor?> {
            override fun onResponse(call: Call<Sensor?>, response: Response<Sensor?>) {
                if (response.isSuccessful) {
                    val sensor = response.body()
                    sensor?.let { updateUI(it) } ?: Log.w("Sensor", "No data received")
                } else {
                    try {
                        throw IOException("Unexpected code $response")
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<Sensor?>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        fetchSensorData()
    }

    override fun onCameraPositionChanged(
        map: Map,
        cameraPosition: CameraPosition,
        cameraUpdateReason: CameraUpdateReason,
        b: Boolean
    ) {
    }
}
