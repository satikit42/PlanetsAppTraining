package com.example.planetsapptraining

import androidx.lifecycle.ViewModel

class PlanetListViewModel : ViewModel() {
    private val viewState : PlanetListViewState = PlanetListViewState("Planets", PlanetMockedData.planets.map {
        PlanetListItemViewState(it.name, it.shortDescription)
    })

    fun getPlanetListViewState() : PlanetListViewState = viewState
}