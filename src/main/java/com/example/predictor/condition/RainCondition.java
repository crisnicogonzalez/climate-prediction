package com.example.predictor.condition;

import com.example.predictor.prediction.Prediction;
import com.example.predictor.system.*;
import com.example.predictor.util.GeometryUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.predictor.weather.Weather.RAIN;



public class RainCondition extends WeatherCondition {

    private GeometryUtil geometryUtil;

    public RainCondition(GeometryUtil geometryUtil) {
        this.geometryUtil = geometryUtil;
    }

    @Override
    public ConditionResult meetsConditions(SolarSystem system) {
        final List<Planet> planets = system.getPlanets();
        final Sun sun = system.getSun();
        final List<Position> planetsPositions = planets.stream().map(Planet::getPosition).collect(Collectors.toList());
        final boolean planetsFormATriangle = geometryUtil.formATriangle(planetsPositions);
        final boolean sunIsInsideOfTriangle = geometryUtil.pointInsideOfTriangle(sun.getPosition(),planetsPositions);
        return  planetsFormATriangle && sunIsInsideOfTriangle ? new ConditionResult(true, new Prediction(RAIN,this.calculateIntensity())) : new ConditionResult(false);
    }


    private BigDecimal calculateIntensity(){
        return null;
    }
}
