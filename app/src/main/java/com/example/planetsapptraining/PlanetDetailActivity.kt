package com.example.planetsapptraining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_planet_detail.*

class PlanetDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planet_detail)

        val planet = Planet("Mars","Where the androids live")

        textPlanetName.text = planet.name
        textPlanetShortDesc.text = planet.shortDescription

    }
}
