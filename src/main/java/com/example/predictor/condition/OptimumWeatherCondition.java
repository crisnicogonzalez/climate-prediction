package com.example.predictor.condition;

import com.example.predictor.system.*;
import com.example.predictor.util.GeometryUtil;
import com.example.predictor.weather.Weather;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.predictor.weather.Weather.OPTIMUM;
import static com.google.common.collect.Lists.newArrayList;

public class OptimumWeatherCondition extends WeatherCondition {

    private GeometryUtil geometryUtil;

    @Autowired
    public OptimumWeatherCondition(GeometryUtil geometryUtil) {
        this.geometryUtil = geometryUtil;
    }

    @Override
    public ConditionResult meetsConditions(SolarSystem system) {
        final Sun sun = system.getSun();
        final List<Planet> planets = system.getPlanets();
        final List<Position> planetsPositions = planets.stream().map(Planet::getPosition).collect(Collectors.toList());
        final List<Position> allPositions = newArrayList(planetsPositions);
        allPositions.add(sun.getPosition());
        final boolean planetsAreAligned = geometryUtil.formALine(planetsPositions);
        final boolean sunIsAlignedWithPlanes = geometryUtil.formALine(allPositions);

        return planetsAreAligned && !sunIsAlignedWithPlanes ? new ConditionResult(true, OPTIMUM) : new ConditionResult(false);
    }
}
