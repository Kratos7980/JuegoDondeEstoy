package com.example.dondeestoygame.view

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Rect
import org.osmdroid.config.Configuration
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.dondeestoygame.R
import com.example.dondeestoygame.databinding.ActivityPantallaMapaBinding
import com.example.dondeestoygame.modelo.Comida
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polygon
import org.osmdroid.views.overlay.ScaleBarOverlay
import org.osmdroid.views.overlay.compass.CompassOverlay
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

class PantallaMapa : AppCompatActivity(), MapEventsReceiver {

    private val MULTIPLE_PERMISSION_REQUEST_CODE: Int = 4
    private lateinit var mapView: MapView
    private lateinit var binding: ActivityPantallaMapaBinding

    // Ubicaciones de interés (latitud y longitud)
    private val latitudCiudadReal = 38.9908
    private val longitudCiudadReal = -3.9206
    private val latitudZamora = 38.6929
    private val longitudZamora = -4.1086
    private val latitudMurcia = 0.00
    private val longitudMurcia = 0.00
    private val latitudCordoba = 0.00
    private val longitudCordoba = 0.00
    private val latitudValencia = 0.00
    private val longitudValencia = 0.00
    private val radioDeAlerta = 100.0 // Radio en metros

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkPermissionsState()

        Configuration.getInstance().setUserAgentValue(getPackageManager().toString())

        binding = ActivityPantallaMapaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.getBundleExtra("data")
        val comida:Comida = bundle?.getSerializable("comida") as Comida

        binding.imageView.setImageResource(comida.image)
        binding.txtComida.text = comida.title

        mapView = binding.map

        setupMap()
        createMarkers()
        //myLocation()

    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    private fun checkPermissionsState(){

        val fineLocationPermissionCheck = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        if(fineLocationPermissionCheck != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MULTIPLE_PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            MULTIPLE_PERMISSION_REQUEST_CODE -> {
                if(grantResults.size > 0) {
                    var somePermissionWasDenied = false
                    for(result in grantResults){
                        if(result == PackageManager.PERMISSION_DENIED){
                            somePermissionWasDenied = true
                        }
                    }
                    if(somePermissionWasDenied){
                        Toast.makeText(this, "Some permissions were denied", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(this, "All permissions were granted", Toast.LENGTH_SHORT).show()
                    }
                    return
                }
            }
        }

    }

    private fun setupMap(){
        mapView.setClickable(true)
        mapView.setDestroyMode(false);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setMultiTouchControls(true);
        mapView.getLocalVisibleRect(Rect())

        mapView.overlays.add(MapEventsOverlay(this))

        val compassOverlay = CompassOverlay(this, InternalCompassOrientationProvider(this), mapView)
        compassOverlay.enableCompass()
        mapView.overlays.add(compassOverlay)

        val dm: DisplayMetrics = this.resources.displayMetrics
        val scaleBarOverlay = ScaleBarOverlay(mapView)
        scaleBarOverlay.setCentred(true)
        scaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 40)
        mapView.overlays.add(scaleBarOverlay)

        // Configurar el centro en España y el nivel de zoom
        val geoPointEspana = GeoPoint(40.0, -3.5) // Centro aproximado de España
        mapView.controller.setCenter(geoPointEspana)
        mapView.controller.setZoom(6.95) // Ajusta el zoom según la vista deseada

    }

    private fun myLocation(){

        val mLocationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(this), mapView)
        mLocationOverlay.enableMyLocation()

        mLocationOverlay.enableFollowLocation()

        val icon = BitmapFactory.decodeResource(resources, R.drawable.baseline_location_pin_24)
        mLocationOverlay.setDirectionIcon(icon)
        mLocationOverlay.runOnFirstFix{
            runOnUiThread{
                mapView.controller.setCenter(mLocationOverlay.myLocation)
                mapView.controller.animateTo(mLocationOverlay.myLocation)
                mapView.controller.setZoom(18.0)
                mapView.invalidate()
            }
        }
        mapView.overlays.add(mLocationOverlay)
    }

    fun createMarkers(){
        //Crear marcador migas de pastor (Ciudad Real)
        val markerCR = Marker(mapView)
        markerCR.position = GeoPoint(latitudCiudadReal, longitudCiudadReal)
        markerCR.title = "Ciudad Real"
        markerCR.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        mapView.overlays.add(markerCR)

        //Crear marcador sopa_castellana (Zamora)
        val markerZamora = Marker(mapView)
        markerZamora.position = GeoPoint(latitudZamora, longitudZamora)
        markerZamora.title = "Zamora"
        markerZamora.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        mapView.overlays.add(markerZamora)

        //Crear marcador cocas (Valencia)
        val markerValencia = Marker(mapView)
        markerValencia.position = GeoPoint(latitudValencia, longitudValencia)
        markerValencia.title = "Valencia"
        markerValencia.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        mapView.overlays.add(markerValencia)

        //Crear marcador salmorejo (Córdoba)
        val markerCordoba = Marker(mapView)
        markerCordoba.position = GeoPoint(latitudCordoba, longitudCordoba)
        markerCordoba.title = "Córdoba"
        markerCordoba.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        mapView.overlays.add(markerCordoba)

        //Crear marcador zarangollo (Murcia)
        val markerMurcia = Marker(mapView)
        markerMurcia.position = GeoPoint(latitudMurcia, longitudMurcia)
        markerMurcia.title = "Murcia"
        markerMurcia.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        mapView.overlays.add(markerMurcia)

    }

    override fun singleTapConfirmedHelper(point: GeoPoint?): Boolean {

        point?.let {
            // Verificar si la distancia con alguno de los marcadores es menor a 100 metros
            val distanciaCiudadReal = calcularDistancia(it.latitude, it.longitude, latitudCiudadReal, longitudCiudadReal)
            val distanciaZamora = calcularDistancia(it.latitude, it.longitude, latitudZamora, longitudZamora)
            val distanciaMurcia = calcularDistancia(it.latitude, it.longitude, latitudMurcia, longitudMurcia)
            val distanciaCordoba = calcularDistancia(it.latitude, it.longitude, latitudCordoba, longitudCordoba)
            val distanciaValencia = calcularDistancia(it.latitude, it.longitude, latitudValencia, longitudValencia)

            if (distanciaCiudadReal <= radioDeAlerta || distanciaZamora <= radioDeAlerta || distanciaMurcia <= radioDeAlerta || distanciaCordoba <= radioDeAlerta || distanciaValencia <= radioDeAlerta) {
               //Ampliar a zona exacta.
            } else {
                Toast.makeText(this, "No está cerca de un sitio específico.", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        //Maneja el evento clic del mapa
        //Agregar marcador
        val marker = Marker(mapView)
        marker.position = point
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        mapView.overlays.add(marker)

        //Agregar un circulo de un radio de 100 metros
        val circle = Polygon(mapView)
        circle.points = Polygon.pointsAsCircle(point, 100.0)
        circle.fillPaint.color = Color.argb(50,0,0,255)
        circle.fillPaint.strokeWidth = 2.0f
        mapView.overlays.add(circle)

        mapView.invalidate()
        return true
    }

    override fun longPressHelper(point: GeoPoint?): Boolean {
        return false
    }

    private fun calcularDistancia(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val radioTierra = 6371000.0 // Radio de la Tierra en metros
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2).pow(2.0) + cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) * sin(dLon / 2).pow(2.0)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return radioTierra * c // Distancia en metros
    }

}