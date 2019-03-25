package com.predictor.condition;

import com.predictor.prediction.Prediction;
import com.predictor.prediction.predictionCounter.Counter;
import com.predictor.system.SolarSystem;

public abstract class WeatherCondition {

    public abstract boolean meetsConditions(SolarSystem system,int day);
    public abstract Prediction getPrediction(SolarSystem system,int day);
    public abstract void accept(Counter counter);

}
