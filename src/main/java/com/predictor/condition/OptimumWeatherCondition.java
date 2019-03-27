package com.predictor.condition;

import com.predictor.prediction.WeatherPrediction;
import com.predictor.util.GeometryUtil;
import com.predictor.weather.Weather;
import com.predictor.universe.Planet;
import com.predictor.universe.Position;
import com.predictor.universe.SolarSystem;
import com.predictor.universe.Sun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;


@Component
public class OptimumWeatherCondition extends WeatherCondition {

    private GeometryUtil geometryUtil;
    private static final Logger LOGGER = LoggerFactory.getLogger(OptimumWeatherCondition.class);


    @Autowired
    public OptimumWeatherCondition(GeometryUtil geometryUtil) {
        this.geometryUtil = geometryUtil;
    }

    /**
     * This condition is fulfilled when the planets are aligned but the sun is not aligned with them
     * */
    @Override
    public boolean meetsConditions(SolarSystem system,int day) {

        LOGGER.debug("Calculate if day {} meets conditions",day);

        final Sun sun = system.getSun();
        final List<Planet> planets = system.getPlanets();
        final List<Position> planetsPositions = planets.stream().map(p -> p.getPositionForDay(day)).collect(Collectors.toList());
        final List<Position> allPositions = newArrayList(planetsPositions);
        allPositions.add(sun.getPosition());
        final boolean planetsAreAligned = geometryUtil.formALine(planetsPositions);
        final boolean sunIsAlignedWithPlanes = geometryUtil.formALine(allPositions);

        return planetsAreAligned && !sunIsAlignedWithPlanes;
    }

    @Override
    public WeatherPrediction getPrediction(SolarSystem system, int day) {
        return new WeatherPrediction(Weather.OPTIMUM);
    }


}
