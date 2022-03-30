package com.example.fragmaps

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

class MapFragment : Fragment(R.layout.layout_map_frag) {
    var map1: MapView? = null

    var lastPoint: GeoPoint? = null
    var lastZoom = 14.0
    var isOpenTopo = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // This line sets the user agent, a requirement to download OSM maps


        Configuration.getInstance()
            .load(activity, PreferenceManager.getDefaultSharedPreferences(activity));

        Log.d("app1", "onViewCreated() MapFragment lastPoint=$lastPoint lastZoom=$lastZoom")

        // map resets itself even though it's already there
        // so we have to reload the last settings
        map1 = view.findViewById(R.id.map1)

        setPosition(lastPoint?.longitude ?: -0.72, lastPoint?.latitude ?: 51.05, lastZoom)
        setStyle(if(isOpenTopo) TileSourceFactory.OpenTopo else TileSourceFactory.MAPNIK)
    }

    fun setPosition(lon: Double, lat: Double, zoom: Double = 14.0) {
        map1?.controller?.setCenter(GeoPoint(lat, lon))
        map1?.controller?.setZoom(zoom)
    }

    fun setStyle(style: OnlineTileSourceBase) {
        map1?.tileProvider?.tileSource = style
    }

    fun setPendingPosition(pendingLon: Double, pendingLat: Double) {
        lastPoint?.latitude = pendingLat
        lastPoint?.longitude = pendingLon
    }

    fun setPendingStyle(openTopo: Boolean) {
        isOpenTopo = openTopo
    }

    override fun onStop() {
        super.onStop()
        Log.d("app1", "MapFragment onStop()")
    }

    // need to save settings to reload map next time
    override fun onDestroy() {
        Log.d("app1", "MapFragment onDestroy()")
        lastPoint = map1?.mapCenter as GeoPoint?
        lastZoom = map1?.zoomLevelDouble ?: 14.0
        isOpenTopo = map1?.tileProvider?.tileSource == TileSourceFactory.OpenTopo
        super.onDestroy()
    }
}