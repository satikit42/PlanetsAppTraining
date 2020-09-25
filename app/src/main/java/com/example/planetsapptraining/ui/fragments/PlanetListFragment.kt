package com.example.planetsapptraining.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planetsapptraining.Planet
import com.example.planetsapptraining.PlanetAdapter
import com.example.planetsapptraining.PlanetMockedData
import com.example.planetsapptraining.R
import kotlinx.android.synthetic.main.fragment_planet_list.view.*

class PlanetListFragment : Fragment(), PlanetListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: PlanetAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_planet_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewAdapter = PlanetAdapter(
            PlanetMockedData.planets,
            requireContext(),
            this)

        recyclerView = view.recycler_view_planets.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = viewAdapter
        }
    }

    override fun onPlanetTapped(planet: Planet) {
        findNavController(this).navigate(PlanetListFragmentDirections.actionPlanetListFragmentToPlanetDetailFragment(planet))
    }

}

interface PlanetListener{
    fun onPlanetTapped(planet: Planet)
}


