package com.example.planetsapptraining.ui.fragments.planetlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planetsapptraining.domain.PlanetRepository
import com.example.planetsapptraining.ui.fragments.planetdetail.PlanetViewState
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlanetListViewModel @Inject constructor(private val planetRepository: PlanetRepository) :
    ViewModel() {

    private val title: String = "Planets"
    private val _viewState = MutableLiveData<PlanetListViewState>()
    val viewState: LiveData<PlanetListViewState>
        get() = _viewState

    fun getPlanetListViewState() {
        viewModelScope.launch {
            _viewState.value = PlanetListViewState(title, planetRepository.getPlanetList().map {
                PlanetViewState(
                    it.name,
                    it.shortDescription,
                    it.imageUrl,
                    it.id,
                    it.distanceFromSun
                )
            })
        }
    }

    fun handleIntent(intent: PlanetListFragment.Intent) {
        when (intent) {
            is PlanetListFragment.Intent.TappedOnFavorite -> {
                handleFavoriteTapped(intent)
            }
        }
    }

    private fun handleFavoriteTapped(intent: PlanetListFragment.Intent.TappedOnFavorite) {
        _viewState.value = _viewState.value?.let { planetsViewState ->
            PlanetListViewState(title, planetsViewState.planets.map {
                if (it.id == intent.id)
                    it.copy(favorite = !it.favorite)
                else
                    it
            })
        }
    }
}