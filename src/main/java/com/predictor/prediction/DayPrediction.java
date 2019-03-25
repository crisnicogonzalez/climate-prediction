package com.predictor.prediction;

import com.predictor.weather.Weather;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Table;

import javax.persistence.Id;

@Immutable
@Table(appliesTo = "DayPrediction")
public class DayPrediction {

    @Id
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
