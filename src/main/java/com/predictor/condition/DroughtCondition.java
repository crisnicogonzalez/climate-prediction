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

@Component
public class DroughtCondition extends WeatherCondition {


    private GeometryUtil geometryUtil;
    private static final Logger LOGGER = LoggerFactory.getLogger(DroughtCondition.class);


    @Autowired
    public DroughtCondition(GeometryUtil geometryUtil) {
        this.geometryUtil = geometryUtil;
    }

    /**
     * This condition is fulfilled when both the planets and the sun are
     * aligned in a straight line
     */
    @Override
    public boolean meetsConditions(SolarSystem system,int day) {
        LOGGER.debug("Calculate if day {} meets conditions",day);
        final Sun sun = system.getSun();
        final List<Planet> planets = system.getPlanets();
        final List<Position> planetsPositions = planets.stream().map(p -> p.getPositionForDay(day)).collect(Collectors.toList());
        final Position sunPosition = sun.getPosition();
        planetsPositions.add(sunPosition);
        LOGGER.debug("Form a line result {}",geometryUtil.formALine(planetsPositions));
        return geometryUtil.formALine(planetsPositions);
    }

    @Override
    public WeatherPrediction getPrediction(SolarSystem system, int day) {
        return new WeatherPrediction(Weather.DROUGHT);
    }
}
