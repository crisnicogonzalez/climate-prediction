package com.example.predictor.condition;

import com.example.predictor.weather.Weather;

public class ConditionResult {

    private boolean apply;
    private Weather weather;

    public ConditionResult(boolean apply) {
        this.apply = apply;
    }

    public ConditionResult(boolean apply, Weather weather) {
        this.apply = apply;
        this.weather = weather;
    }

    public boolean isApply() {
        return apply;
    }

    public void setApply(boolean apply) {
        this.apply = apply;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
