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

import static com.google.common.collect.Lists.newArrayList;


@Component
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

        return planetsAreAligned && !sunIsAlignedWithPlanes ? new ConditionResult(true, new Prediction(Weather.OPTIMUM)) : new ConditionResult(false);
    }


}
