package com.example.fragmaps

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationView
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.OverlayItem

class MainActivity : AppCompatActivity() {

    lateinit var mapFrag: MapFragment
    lateinit var styleFrag: StyleFragment
    lateinit var setLocFrag: SetLocFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapFrag = MapFragment()
        styleFrag = StyleFragment {
            mapFrag.setPendingStyle(it)
            showMap()
        }
        setLocFrag = SetLocFragment { lon, lat ->
            mapFrag.setPendingPosition(lon, lat)
            showMap()
        }

        val nv = findViewById<NavigationView>(R.id.nv)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        nv.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            when (it.itemId) {
                R.id.menuMap -> {
                    showMap()
                    true
                }
                R.id.menuItemSetMapStyle -> {
                    supportFragmentManager.commit {
                        replace(R.id.frame1, styleFrag)
                    }

                    true

                }
                R.id.menuItemSetLocation -> {
                    supportFragmentManager.commit {
                        replace(R.id.frame1, setLocFrag)
                    }
                    true
                }
                else -> {
                    false
                }
            }
        }
        showMap()
    }

    fun showMap() {
        supportFragmentManager.commit {
            replace(R.id.frame1, mapFrag)
        }
    }
}