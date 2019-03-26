package com.predictor.condition;

import com.predictor.prediction.Prediction;
import com.predictor.util.GeometryUtil;
import com.predictor.universe.Planet;
import com.predictor.universe.Position;
import com.predictor.universe.SolarSystem;
import com.predictor.universe.Sun;
import com.predictor.weather.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RainCondition extends WeatherCondition {


    private GeometryUtil geometryUtil;

    @Autowired
    public RainCondition(GeometryUtil geometryUtil) {
        this.geometryUtil = geometryUtil;
    }



    /**
     * This condition is fulfilled when the planets form a triangle and the sun is inside the same
     *
     * */
    @Override
    public boolean meetsConditions(SolarSystem system,int day) {
        final List<Planet> planets = system.getPlanets();
        final Sun sun = system.getSun();
        final List<Position> planetsPositions = planets.stream().map(Planet::getPosition).collect(Collectors.toList());

        final Position p1 = planetsPositions.get(0);
        final Position p2 = planetsPositions.get(1);
        final Position p3 = planetsPositions.get(2);


        final boolean planetsFormATriangle = geometryUtil.formATriangle(p1,p2,p3);
        final boolean sunIsInsideOfTriangle = geometryUtil.pointInsideOfTriangle(p1,p2,p3,sun.getPosition());
        return  planetsFormATriangle && sunIsInsideOfTriangle;
    }

    @Override
    public Prediction getPrediction(SolarSystem system,int day){
        final List<Planet> planets = system.getPlanets();
        final List<Position> planetsPositions = planets.stream().map(Planet::getPosition).collect(Collectors.toList());

        final Position p1 = planetsPositions.get(0);
        final Position p2 = planetsPositions.get(1);
        final Position p3 = planetsPositions.get(2);
        return new Prediction(Weather.RAIN,this.calculateIntensity(p1,p2,p3));
    }


    private double calculateIntensity(Position p1,Position p2,Position p3){
        return geometryUtil.calculatePerimeterOfTriangle(p1,p2,p3);
    }
}
