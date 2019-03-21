package com.example.predictor.condition;

import com.example.predictor.prediction.Prediction;
import com.example.predictor.system.*;
import com.example.predictor.util.GeometryUtil;
import javafx.geometry.Pos;

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

        final Position p1 = planetsPositions.get(0);
        final Position p2 = planetsPositions.get(1);
        final Position p3 = planetsPositions.get(2);


        final boolean planetsFormATriangle = geometryUtil.formATriangle(p1,p2,p3);
        final boolean sunIsInsideOfTriangle = geometryUtil.pointInsideOfTriangle(p1,p2,p3,sun.getPosition());
        return  planetsFormATriangle && sunIsInsideOfTriangle ? new ConditionResult(true, new Prediction(RAIN,this.calculateIntensity())) : new ConditionResult(false);
    }


    private BigDecimal calculateIntensity(){
        return null;
    }
}
