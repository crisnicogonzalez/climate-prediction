package com.predictor.prediction;

import com.predictor.weather.Weather;


public class DayPrediction {

    private int day;
    private Weather weather;
    private double intensity;

    public DayPrediction(int day, Weather weather, double intensity) {
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

    public double getIntensity() {
        return intensity;
    }

    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }
}
