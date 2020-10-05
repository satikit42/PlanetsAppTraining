package com.example.planetsapptraining.ui.components.itemWithImage

data class ItemWithImageAndTextViewState(
    val name : String,
    val shortDescription : String,
    val imageUrl : String,
    val id: Int,
    var favorite: Boolean
)