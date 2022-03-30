package com.example.fragmaps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class SetLocFragment(val callback: (Double, Double) -> Unit) : Fragment(R.layout.set_location) {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val btn = view.findViewById<Button>(R.id.btn2)
        val tvLat = view.findViewById<EditText>(R.id.etLat2)
        val tvLon = view.findViewById<EditText>(R.id.etLon2)
        btn.setOnClickListener {
            setLocation(tvLon.text.toString().toDouble(), tvLat.text.toString().toDouble())
        }
    }

    fun setLocation(lon: Double, lat: Double) {
        callback(lon, lat)
    }
}