package com.example.planetsapptraining

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlanetListViewState(val title : String, val planets : List<PlanetListItemViewState>) : Parcelable

@Parcelize
data class PlanetListItemViewState (val name : String, val shortDescription : String) : Parcelable