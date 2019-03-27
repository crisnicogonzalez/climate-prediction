package com.predictor.condition;

import com.predictor.prediction.WeatherPrediction;
import com.predictor.universe.SolarSystem;
import com.predictor.weather.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NoOneCondition extends WeatherCondition {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoOneCondition.class);


    @Override
    public boolean meetsConditions(SolarSystem system, int day) {
        LOGGER.info("NoOne Condition");
        return true;
    }

    @Override
    public WeatherPrediction getPrediction(SolarSystem system, int day) {
        return new WeatherPrediction(Weather.UNKNOWN);
    }
}
