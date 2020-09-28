package com.example.planetsapptraining.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planetsapptraining.*
import kotlinx.android.synthetic.main.fragment_planet_list.*

class PlanetListFragment : Fragment(), PlanetListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: PlanetAdapter
    private val viewModel : PlanetListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_planet_list, container, false)
    }

    override fun onResume() {
        super.onResume()
        render()
    }

    private fun render() {

        textPlanetsTitle.text = viewModel.getPlanetListViewState().title

        viewAdapter = PlanetAdapter(
            viewModel.getPlanetListViewState().planets,
            requireContext(),
            this)

        recyclerView = recycler_view_planets.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = viewAdapter
        }
    }

    override fun onPlanetTapped(planet: PlanetListItemViewState) {
        findNavController(this).navigate(PlanetListFragmentDirections.actionPlanetListFragmentToPlanetDetailFragment(planet))
    }
}

interface PlanetListener{
    fun onPlanetTapped(planet: PlanetListItemViewState)
}


