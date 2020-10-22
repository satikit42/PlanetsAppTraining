package com.example.planetsapptraining.ui.fragments.planetdetail

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.planetsapptraining.*
import kotlinx.android.synthetic.main.fragment_planet_detail.*
import javax.inject.Inject

class PlanetDetailFragment : Fragment() {

    private val arguments: PlanetDetailFragmentArgs by navArgs()
    @Inject lateinit var viewModel: PlanetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext as App).appComponent.inject(this)
    }

    override fun onStart() {
        super.onStart()
        viewModel.viewState.observe(viewLifecycleOwner, Observer { render(it) })
        viewModel.getPlanetViewState(arguments.planetId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_planet_detail, container, false)
    }

    private fun render(viewState: PlanetViewState) {
        textPlanetName.text = viewState.name
        /*(AnimatorInflater.loadAnimator(context,R.animator.animator) as AnimatorSet).apply {
            setTarget(textPlanetName.text)
            start()
        }*/
        ObjectAnimator.ofFloat(textPlanetName, "scaleX", 0.8f, 1f).start()
        ObjectAnimator.ofFloat(textPlanetName, "scaleY", 0.8f, 1f).start()
        (AnimatorInflater.loadAnimator(
            requireContext(),
            R.animator.animator
        ) as AnimatorSet).apply {
            setTarget(imagePlanet)
            start()
        }
        textPlanetShortDesc.text = viewState.shortDescription
        Glide.with(this).load(viewState.imageUrl).into(imagePlanet)
        //ObjectAnimator.ofFloat(viewState.imageUrl,"scaleX",0.0f,1.0f).start()
        //ObjectAnimator.ofFloat(viewState.imageUrl,"scaleY",0.0f,1.0f).start()
        view_detail_row_distance_from_sun.render("Distance from sun", viewState.distanceFromSun.toString())
        view_detail_row_surface_gravity.render("Surface gravity", viewState.surfaceGravity.toString())
        view_detail_row_planet_type.render("Planet type", viewState.planetType.toString())
        view_detail_row_description.render("Description", viewState.description.toString())
    }
}