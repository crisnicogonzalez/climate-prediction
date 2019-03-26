package com.predictor.universe;

import java.util.List;

public class SolarSystem {

    private List<Planet> planets;
    private Sun sun;

    public SolarSystem(List<Planet> planets, Sun sun) {
        this.planets = planets;
        this.sun = sun;
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }

    public Sun getSun() {
        return sun;
    }

    public void setSun(Sun sun) {
        this.sun = sun;
    }

    public void passADay() {
    }
}
