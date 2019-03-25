package com.predictor.condition;

import com.predictor.prediction.Prediction;
import com.predictor.prediction.predictionCounter.Counter;
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

    /**
     * This condition is fulfilled when the planets are aligned but the sun is not aligned with them
     * */
    @Override
    public boolean meetsConditions(SolarSystem system,int day) {
        final Sun sun = system.getSun();
        final List<Planet> planets = system.getPlanets();
        final List<Position> planetsPositions = planets.stream().map(Planet::getPosition).collect(Collectors.toList());
        final List<Position> allPositions = newArrayList(planetsPositions);
        allPositions.add(sun.getPosition());
        final boolean planetsAreAligned = geometryUtil.formALine(planetsPositions);
        final boolean sunIsAlignedWithPlanes = geometryUtil.formALine(allPositions);

        return planetsAreAligned && !sunIsAlignedWithPlanes;
    }

    @Override
    public Prediction getPrediction(SolarSystem system,int day) {
        return new Prediction(Weather.OPTIMUM);
    }

    @Override
    public void accept(Counter counter) {
        counter.visit(this);
    }


}
