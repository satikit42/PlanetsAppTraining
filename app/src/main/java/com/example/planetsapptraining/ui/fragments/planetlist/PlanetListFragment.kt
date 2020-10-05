package com.example.planetsapptraining.ui.fragments.planetlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planetsapptraining.App
import com.example.planetsapptraining.R
import com.example.planetsapptraining.ui.components.itemWithImage.ItemWithImageAndText
import kotlinx.android.synthetic.main.fragment_planet_list.*
import javax.inject.Inject

class PlanetListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: PlanetAdapter
    @Inject
    lateinit var viewModel: PlanetListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_planet_list, container, false)
    }

    override fun onStart() {
        super.onStart()

        viewAdapter = PlanetAdapter(
            requireContext(),
            ::onItemTapped
        )

        recyclerView = recycler_view_planets.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = viewAdapter
        }

        viewModel.viewState.observe(viewLifecycleOwner, Observer { render(it) })
        viewModel.getPlanetListViewState()
    }

    private fun render(viewState: PlanetListViewState) {
        textPlanetsTitle.text = viewState.title
        viewAdapter.setViewState(viewState.planets)
    }

    private fun onItemTapped(intent: ItemWithImageAndText.Intent) {
        when (intent) {
            is ItemWithImageAndText.Intent.ItemClicked -> {
                findNavController(this).navigate(
                    PlanetListFragmentDirections.actionPlanetListFragmentToPlanetDetailFragment(
                        intent.id
                    )
                )
            }
            is ItemWithImageAndText.Intent.TappedOnFavorite -> viewModel.handleIntent(
                Intent.TappedOnFavorite(
                    intent.id
                )
            )
        }
    }

    sealed class Intent {
        data class TappedOnFavorite(val id: Int) : Intent()
    }
}


