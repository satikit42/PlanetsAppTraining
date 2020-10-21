package com.example.planetsapptraining.ui.fragments.planetlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planetsapptraining.domain.PlanetRepository
import com.example.planetsapptraining.repositories.retrofit.PreferenceRepository
import com.example.planetsapptraining.repositories.retrofit.PreferenceRepositoryImplementation
import com.example.planetsapptraining.ui.fragments.planetdetail.PlanetViewState
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlanetListViewModel @Inject constructor(val planetRepository: PlanetRepository,val preferenceRepository: PreferenceRepository) : ViewModel() {

    private val _viewState = MutableLiveData<PlanetListViewState>()
    val viewState: LiveData<PlanetListViewState>
        get() = _viewState

    fun getPlanetListViewState() {

        viewModelScope.launch {
            val savedFavorites = preferenceRepository.getFavoritesPlanet()
            _viewState.value = PlanetListViewState("Planets", planetRepository.getPlanetList().map {

                PlanetViewState(it.name, it.shortDescription, it.imageUrl, it.id, it.distanceFromSun,favorite = savedFavorites.contains(it.id.toString()))
            })
        }
    }

    fun favouriteTapped(id: Int) {
        _viewState.value = _viewState.value?.let { planetsViewState ->
            PlanetListViewState("Planets", planetsViewState.planets.map {
                if (it.id == id) {
                    it.copy(favorite = !it.favorite)
                } else
                    it
            })
        }
        preferenceRepository.saveFavoritePlanet(id)
    }
}