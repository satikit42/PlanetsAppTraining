package com.example.planetsapptraining.ui.fragments.planetdetail

import android.os.Parcelable
import com.example.planetsapptraining.ui.components.itemWithImage.ItemWithImageAndTextViewState
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlanetViewState(
    override val name: String,
    override val shortDescription: String,
    override val imageUrl: String,
    override val id: Int,
    val distanceFromSun: Double,
    val description: String? = null,
    val planetType: String? = null,
    val surfaceGravity: Double? = null,
    val favorite: Boolean = false
) : Parcelable,
    ItemWithImageAndTextViewState