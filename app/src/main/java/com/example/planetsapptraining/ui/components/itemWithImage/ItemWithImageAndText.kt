package com.example.planetsapptraining.ui.components.itemWithImage

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.planetsapptraining.R
import kotlinx.android.synthetic.main.item_image_text_view.view.*

class ItemWithImageAndText
@JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.item_image_text_view, this)
    }

    fun render(viewState : ItemWithImageAndTextViewState) {
        textItemName.text = viewState.name
        textItemShortDesc.text = viewState.shortDescription
        Glide.with(this).load(viewState.imageUrl).into(imageItem)
    }
}