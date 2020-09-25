package com.example.planetsapptraining

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.planetsapptraining.ui.fragments.PlanetListener
import kotlinx.android.synthetic.main.item_planet_list.view.*

class PlanetAdapter(
    private val planets: List<Planet>,
    private val context: Context?,
    private val itemClickListener: PlanetListener
) :
    RecyclerView.Adapter<PlanetAdapter.PlanetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        return PlanetViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_planet_list, parent, false)
        )
    }

    override fun getItemCount() = planets.size

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        val planet = planets[position]
        holder.bind(planet)
    }

    inner class PlanetViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(planet: Planet) {
            view.textPlanetName.text = planet.name
            view.textPlanetShortDesc.text = planet.shortDescription
            view.setOnClickListener {
                itemClickListener.onPlanetTapped(planet)
            }
        }
    }
}
