package com.example.planetsapptraining

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Planet (val name : String, val shortDescription : String, val imageUrl: String) : Parcelable {
    override fun toString(): String {
        return name
    }
}