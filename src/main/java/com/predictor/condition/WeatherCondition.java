package com.predictor.condition;

import com.predictor.prediction.WeatherPrediction;
import com.predictor.universe.SolarSystem;

public abstract class WeatherCondition {

    public abstract boolean meetsConditions(SolarSystem system,int day);
    public abstract WeatherPrediction getPrediction(SolarSystem system, int day);
}
