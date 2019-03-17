package com.example.predictor.condition;

import com.example.predictor.prediction.Prediction;
import com.example.predictor.system.*;
import com.example.predictor.util.GeometryUtil;
import com.example.predictor.weather.Weather;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;


public class DroughtCondition extends WeatherCondition {

    private GeometryUtil geometryUtil;

    @Autowired
    public DroughtCondition(GeometryUtil geometryUtil) {
        this.geometryUtil = geometryUtil;
    }

    @Override
    public ConditionResult meetsConditions(SolarSystem system) {
        final Sun sun = system.getSun();
        final List<Planet> planets = system.getPlanets();
        final List<Position> planetsPositions = planets.stream().map(Planet::getPosition).collect(Collectors.toList());
        final Position sunPosition = sun.getPosition();

        planetsPositions.add(sunPosition);
        boolean allAreAligned =  geometryUtil.formALine(planetsPositions);
        return allAreAligned ? new ConditionResult(true, new Prediction(Weather.DROUGHT)): new ConditionResult(false);
    }

    public void setGeometryUtil(GeometryUtil geometryUtil) {
        this.geometryUtil = geometryUtil;
    }
}
