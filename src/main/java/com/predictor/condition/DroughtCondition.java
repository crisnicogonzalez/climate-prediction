package com.predictor.condition;

import com.predictor.prediction.Prediction;
import com.predictor.util.GeometryUtil;
import com.predictor.weather.Weather;
import com.predictor.system.Planet;
import com.predictor.system.Position;
import com.predictor.system.SolarSystem;
import com.predictor.system.Sun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DroughtCondition extends WeatherCondition {


    private GeometryUtil geometryUtil;

    @Autowired
    public DroughtCondition(GeometryUtil geometryUtil) {
        this.geometryUtil = geometryUtil;
    }

    /**
     * This condition is fulfilled when both the planets and the sun are aligned
     */
    @Override
    public boolean meetsConditions(SolarSystem system,int day) {
        final Sun sun = system.getSun();
        final List<Planet> planets = system.getPlanets();
        final List<Position> planetsPositions = planets.stream().map(Planet::getPosition).collect(Collectors.toList());
        final Position sunPosition = sun.getPosition();
        planetsPositions.add(sunPosition);
        return geometryUtil.formALine(planetsPositions);
    }

    @Override
    public Prediction getPrediction(SolarSystem system,int day) {
        return new Prediction(Weather.DROUGHT);
    }
}
