package com.example.predictor.prediction;

import com.example.predictor.weather.Weather;

import java.math.BigDecimal;

public class DayPrediction {

    private int day;
    private Weather weather;
    private BigDecimal intensity;

    public DayPrediction(int day, Weather weather, BigDecimal intensity) {
        this.day = day;
        this.weather = weather;
        this.intensity = intensity;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public BigDecimal getIntensity() {
        return intensity;
    }

    public void setIntensity(BigDecimal intensity) {
        this.intensity = intensity;
    }
}
