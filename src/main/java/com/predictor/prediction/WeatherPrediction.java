package com.predictor.prediction;

import com.predictor.weather.Weather;

public class WeatherPrediction {
    private Weather weather;
    private double intensity;

    public WeatherPrediction(Weather weather, double intensity) {
        this.weather = weather;
        this.intensity = intensity;
    }

    public WeatherPrediction(Weather weather) {
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
