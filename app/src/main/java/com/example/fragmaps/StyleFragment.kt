package com.example.fragmaps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase
import org.osmdroid.tileprovider.tilesource.TileSourceFactory

class StyleFragment(val callback: (Boolean) -> Unit): Fragment(R.layout.map_choose) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val btnRegular = view.findViewById<Button>(R.id.btnRegular)
        val btnOpenTopoMap = view.findViewById<Button>(R.id.btnOpenTopoMap)

        btnRegular.setOnClickListener {
            chooseMap(false)
        }

        btnOpenTopoMap.setOnClickListener {
            chooseMap(true)
        }
    }

    fun chooseMap(openTopo: Boolean) {
        callback(openTopo)
    }
}