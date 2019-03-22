package com.predictor.prediction;

import com.predictor.weather.Weather;

public class Prediction {
    private Weather weather;
    private double intensity;

    public Prediction(Weather weather, double intensity) {
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

    public double getIntensity() {
        return intensity;
    }

    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }
}
