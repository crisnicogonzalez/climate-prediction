package com.predictor.universe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;




/**For the solution SolarSystem is stateless*/
@Component
public class SolarSystem {

    private List<Planet> planets;
    private Sun sun;

    public SolarSystem() {
        final Planet ferengi = new Planet(-3,2000);
        final Planet vulcano = new Planet(-1,500);
        final Planet betasoid = new Planet(5,1000);
        this.planets = newArrayList(ferengi,vulcano,betasoid);
        this.sun = new Sun();
    }

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
