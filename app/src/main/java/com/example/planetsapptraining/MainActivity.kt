package com.example.planetsapptraining

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.planetsapptraining.ui.fragments.planetlist.PlanetListFragmentDirections
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    override fun onResume() {
        super.onResume()
        val planetId = intent.getIntExtra("planetId", -1)
        if (planetId != -1) {
            findNavController(nav_host_fragment).navigate(
                PlanetListFragmentDirections.actionPlanetListFragmentToPlanetDetailFragment(
                    planetId
                )
            )
        }

    }
}