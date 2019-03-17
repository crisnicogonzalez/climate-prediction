package com.example.predictor.prediction;

import com.example.predictor.weather.Weather;

import java.math.BigDecimal;

public class Prediction {
    private Weather weather;
    private BigDecimal intensity;

    public Prediction(Weather weather, BigDecimal intensity) {
        this.weather = weather;
        this.intensity = intensity;
    }

    public Prediction(Weather weather) {
        this.weather = weather;
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
