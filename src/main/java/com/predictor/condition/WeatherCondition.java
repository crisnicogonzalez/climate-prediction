package com.predictor.condition;

import com.predictor.system.SolarSystem;

public abstract class WeatherCondition {

    public abstract ConditionResult meetsConditions(SolarSystem system);

}
