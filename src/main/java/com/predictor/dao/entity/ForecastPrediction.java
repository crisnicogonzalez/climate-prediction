package com.predictor.dao.entity;

import com.predictor.weather.Weather;
import javax.persistence.*;

@Entity
@Table(name = "forecast_prediction")
public class ForecastPrediction {

    @Id
    @Column(name="day")
    private int day;

    @Column(name="weather")
    @Enumerated(EnumType.STRING)
    private Weather weather;



    public ForecastPrediction() {
    }

    public ForecastPrediction(int day, Weather weather, double intensity) {
        this.day = day;
        this.weather = weather;
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
}
