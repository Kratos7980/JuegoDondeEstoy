package com.example.dondeestoygame.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Rect
import android.media.MediaPlayer
import org.osmdroid.config.Configuration
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.dondeestoygame.R
import com.example.dondeestoygame.databinding.ActivityPantallaMapaBinding
import com.example.dondeestoygame.modelo.Comida
import com.example.dondeestoygame.modelo.Informacion
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
    private lateinit var comida:Comida

    // Ubicaciones de interés (latitud y longitud)
    private val latitudCiudadReal = 38.9863
    private val longitudCiudadReal = -3.9291
    private val latitudSegovia = 40.9429
    private val longitudSegovia = -4.1184
    private val latitudMurcia = 38.0000
    private val longitudMurcia = -1.5000
    private val latitudCordoba = 37.8882
    private val longitudCordoba = -4.7794
    private val latitudValencia = 39.4699
    private val longitudValencia = -0.3763
    private var radioDeAlerta = 100000.0 // Radio en metros

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Pedir permisos
        checkPermissionsState()

        Configuration.getInstance().setUserAgentValue(getPackageManager().toString())

        binding = ActivityPantallaMapaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Recuperar comida de la actividad anterior
        val bundle = intent.getBundleExtra("data")
        comida = bundle?.getSerializable("comida") as Comida
        // Cambiar radioDeAlerta según la dificultad
        if(Informacion.getDificultad() == 2){
            radioDeAlerta = 50000.0
        }
        // Cambiar imagen y texto de la comida
        binding.imageView.setImageResource(comida.image)
        binding.txtComida.text = comida.title
        // Recuperar el mapa
        mapView = binding.map
        // Iniciar el mapa
        setupMap()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
    // Pedir permisos
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
    // Pedir permisos
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
    // Iniciar el mapa
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

    fun createMarkerCiudadReal(){
        //Crear marcador (Ciudad Real)
        val markerCR = Marker(mapView)
        markerCR.position = GeoPoint(latitudCiudadReal, longitudCiudadReal)
        markerCR.title = "Ciudad Real"
        markerCR.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        mapView.overlays.add(markerCR)

    }
    fun createMarkerSegovia(){
        //Crear marcador (Segovia)
        val markerZamora = Marker(mapView)
        markerZamora.position = GeoPoint(latitudSegovia, longitudSegovia)
        markerZamora.title = "Segovia"
        markerZamora.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        mapView.overlays.add(markerZamora)
    }

    fun createMarkerValencia(){
        //Crear marcador (Valencia)
        val markerValencia = Marker(mapView)
        markerValencia.position = GeoPoint(latitudValencia, longitudValencia)
        markerValencia.title = "Valencia"
        markerValencia.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        mapView.overlays.add(markerValencia)
    }

    fun createMarkerCordoba(){
        //Crear marcador (Córdoba)
        val markerCordoba = Marker(mapView)
        markerCordoba.position = GeoPoint(latitudCordoba, longitudCordoba)
        markerCordoba.title = "Córdoba"
        markerCordoba.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        mapView.overlays.add(markerCordoba)
    }

    fun createMarketMurcia(){
        //Crear marcador (Murcia)
        val markerMurcia = Marker(mapView)
        markerMurcia.position = GeoPoint(latitudMurcia, longitudMurcia)
        markerMurcia.title = "Murcia"
        markerMurcia.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        mapView.overlays.add(markerMurcia)
    }

    // Reiniciar puntero para que no se quede marcado.
    private var ultimoMarcador: Marker? = null
    private var ultimoCirculo: Polygon? = null

    override fun singleTapConfirmedHelper(point: GeoPoint?): Boolean {
        val distanciaCiudadReal:Double
        val distanciaSegovia:Double
        val distanciaMurcia:Double
        val distanciaCordoba:Double
        val distanciaValencia:Double
        val direccion:String


        point?.let {
            // Verificar si la distancia con alguno de los marcadores es menor a 100 metros
            when(comida.title){
                "Migas de pastor" -> {
                    distanciaCiudadReal = calcularDistancia(it.latitude, it.longitude, latitudCiudadReal, longitudCiudadReal)
                    if(distanciaCiudadReal <= radioDeAlerta){
                        createMarkerCiudadReal()
                        ampliarMapa(latitudCiudadReal, longitudCiudadReal)
                        sumarPuntos()
                        Informacion.addComida(comida)
                        Informacion.sumarVictorias()
                        mostrarAcierto("Ciudad Real");
                    }else{
                        if(Informacion.getIntentos() >1 ){
                            Informacion.restarIntentos(1)
                            direccion = calcularDireccion(it.latitude, it.longitude, latitudCiudadReal, longitudCiudadReal)
                            mostrarAlerta(direccion)
                        }else{
                            Informacion.restarIntentos(1)
                            volverAPrincipal()
                        }
                    }
                }
                "Sopa de ajo" -> {
                    distanciaSegovia = calcularDistancia(it.latitude, it.longitude, latitudSegovia, longitudSegovia)
                    if(distanciaSegovia <= radioDeAlerta){


                        createMarkerSegovia()
                        ampliarMapa(latitudSegovia, longitudSegovia)
                        sumarPuntos()
                        Informacion.sumarVictorias()
                        Informacion.addComida(comida)
                        mostrarAcierto("Segovia");
                    }else{
                        if(Informacion.getIntentos() > 1 ){
                            Informacion.restarIntentos(1)
                            direccion = calcularDireccion(it.latitude, it.longitude, latitudSegovia, longitudSegovia)
                            mostrarAlerta(direccion)
                        }else{
                            volverAPrincipal()
                        }
                    }
                }
                "Cocas" -> {
                    distanciaValencia = calcularDistancia(it.latitude, it.longitude, latitudValencia, longitudValencia)
                    if(distanciaValencia <= radioDeAlerta){
                        createMarkerValencia()
                        ampliarMapa(latitudValencia, longitudValencia)
                        sumarPuntos()
                        Informacion.sumarVictorias()
                        Informacion.addComida(comida)
                        mostrarAcierto("Valencia");
                    }else{
                        if(Informacion.getIntentos() > 1 ){
                            Informacion.restarIntentos(1)
                            direccion = calcularDireccion(it.latitude, it.longitude, latitudValencia, longitudValencia)
                            mostrarAlerta(direccion)
                        }else{
                            volverAPrincipal()
                        }
                    }
                }
                "Salmorejo" -> {
                    distanciaCordoba = calcularDistancia(it.latitude, it.longitude, latitudCordoba, longitudCordoba)
                    if(distanciaCordoba <= radioDeAlerta){
                        createMarkerCordoba()
                        ampliarMapa(latitudCordoba, longitudCordoba)
                        sumarPuntos()
                        Informacion.sumarVictorias()
                        Informacion.addComida(comida)
                        mostrarAcierto("Córdoba");
                    }else{
                        if(Informacion.getIntentos() > 1 ){
                            Informacion.restarIntentos(1)
                            direccion = calcularDireccion(it.latitude, it.longitude, latitudCordoba, longitudCordoba)
                            mostrarAlerta(direccion)
                        }else{
                            volverAPrincipal()
                        }
                    }
                }
                "Zarangollo" -> {
                    distanciaMurcia = calcularDistancia(it.latitude, it.longitude, latitudMurcia, longitudMurcia)
                    if(distanciaMurcia <= radioDeAlerta){
                        createMarketMurcia()
                        ampliarMapa(latitudMurcia, longitudMurcia)
                        sumarPuntos()
                        Informacion.sumarVictorias()
                        Informacion.addComida(comida)
                        mostrarAcierto("Murcia");
                    }else{
                        if(Informacion.getIntentos() > 1 ){
                            Informacion.restarIntentos(1)
                            direccion = calcularDireccion(it.latitude, it.longitude, latitudMurcia, longitudMurcia)
                            mostrarAlerta(direccion)
                        }else{
                            volverAPrincipal()
                        }
                    }
                }
            }
        }

        if (Informacion.getVictorias() == 5) {
            if (Informacion.getSonido()) {
                val mediaPlayer = MediaPlayer.create(this, R.raw.celebracion)
                mediaPlayer?.start()
                mediaPlayer?.setOnCompletionListener {
                    it.release()
                }
            }
        }
        // Eliminar el último marcador si existe
        ultimoMarcador?.let { mapView.overlays.remove(it) }
        ultimoCirculo?.let { mapView.overlays.remove(it) }

        //Maneja el evento clic del mapa
        //Agregar marcador
        val marker = Marker(mapView)
        marker.position = point
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        mapView.overlays.add(marker)

        // Guardar la referencia al último marcador
        ultimoMarcador = marker

        //Agregar un circulo de un radio de 100 km
        val circle = Polygon(mapView)
        circle.points = Polygon.pointsAsCircle(point, 100.0)
        circle.fillPaint.color = Color.argb(50,0,0,255)
        circle.fillPaint.strokeWidth = 2.0f
        mapView.overlays.add(circle)
        ultimoCirculo = circle

        mapView.invalidate()

        return true
    }

    override fun longPressHelper(point: GeoPoint?): Boolean {
        return false
    }
    // Calcular distancia entre dos puntos.
    private fun calcularDistancia(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val radioTierra = 6371000.0 // Radio de la Tierra en metros
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2).pow(2.0) + cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) * sin(dLon / 2).pow(2.0)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return radioTierra * c // Distancia en metros
    }
    // Calcula la direccion hacia la que está orientada la comida.
    private fun calcularDireccion(lat1:Double, lon1:Double, lat2:Double, lon2:Double):String{
        // Determinar dirección
        val direccion = when {
            lat2 > lat1 -> "Norte"
            lat2 < lat1 -> "Sur"
            lon2 > lon1 -> "Este"
            lon2 < lon1 -> "Oeste"
            else -> "Misma ubicación"
        }
        return direccion
    }
    // Ampliar el mapa.
    fun ampliarMapa(latitud:Double, longitud:Double){
        
        // Configurar el centro en España y el nivel de zoom
        val geoPointEspana = GeoPoint(latitud, longitud) // Centro aproximado de España
        mapView.controller.setCenter(geoPointEspana)
        mapView.controller.setZoom(12.00) // Ajusta el zoom según la vista deseada

    }
    // Mostrar alerta cuando se equivoca el usuario.
    private fun mostrarAlerta(direccion:String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¡Te equivocaste!")
        builder.setMessage("Está más al " + direccion)
        builder.setPositiveButton("OK") { dialog, which ->
            ultimoMarcador?.let { mapView.overlays.remove(it) }
            ultimoCirculo?.let { mapView.overlays.remove(it) }
        }
        builder.show()
    }
    // Mostrar alerta cuando acierta el usuario.
    private fun mostrarAcierto(nombreCiudad:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¡ACERTASTE!")
        builder.setMessage( comida.title + " es un plato típico de " + nombreCiudad)
        when(comida.title){
            "Migas de pastor" -> builder.setIcon(R.drawable.migas)
            "Sopa de ajo" -> builder.setIcon(R.drawable.sopa_castellana)
            "Cocas" -> builder.setIcon(R.drawable.cocas)
            "Salmorejo" -> builder.setIcon(R.drawable.salmorejo)
            "Zarangollo" -> builder.setIcon(R.drawable.zarangollo)
        }

        builder.setPositiveButton("OK") { dialog, which ->
            val intent = Intent(this, PantallaPrincipal::class.java)
            startActivity(intent)
            finish()
        }
        builder.show()
    }
    // Sumar puntos cuando acierta el usuario.
    private fun sumarPuntos(){
        val intentos = Informacion.getIntentos()
        when(intentos){
            5 -> Informacion.sumarPuntos(100)
            4 -> Informacion.sumarPuntos(75)
            3 -> Informacion.sumarPuntos(50)
            2 -> Informacion.sumarPuntos(25)
            1 -> Informacion.sumarPuntos(10)
        }
    }
    // Volver al menú principal cuando el usuario se queda sin intentos.
    private fun volverAPrincipal(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¡PERDISTE!")
        builder.setMessage("¡Te has quedado sin intentos!")
        builder.setPositiveButton("OK") { dialog, which ->
            val intent = Intent(this, PantallaPrincipal::class.java)
            startActivity(intent)
            finish()
        }
        builder.show()
    }

}