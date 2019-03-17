package com.example.predictor.condition;

import com.example.predictor.system.SolarSystem;
import com.example.predictor.weather.Weather;

public abstract class WeatherCondition {

    public abstract ConditionResult meetsConditions(SolarSystem system);

}
