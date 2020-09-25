package com.example.planetsapptraining

object PlanetMockedData {
    val planets: List<Planet>
        get() {
            return listOf(
                Planet("Mars", "Where the androids live"),
                Planet("Earth", "Home sweet home"),
                Planet("Mercury", "As close as you can get")
            )
        }
}
