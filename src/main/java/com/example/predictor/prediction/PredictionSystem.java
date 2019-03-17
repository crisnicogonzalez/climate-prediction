package com.example.predictor.prediction;

import com.example.predictor.system.Planet;
import com.example.predictor.system.SolarSystem;
import com.example.predictor.system.Sun;
import com.example.predictor.weather.Weather;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.example.predictor.system.Planet.*;
import static com.example.predictor.system.Planet.BETASOID;
import static com.example.predictor.system.Planet.VULCANO;
import static com.google.common.collect.Lists.newArrayList;

public class PredictionSystem {

    @Autowired
    private Predictor predictor;
    private static final Sun SUN = new Sun();
    private static final List<Planet> PLANETS = newArrayList(VULCANO, BETASOID, FERENGI);


    public void predictWeatherForDays(Integer quantityOfDays){
        final SolarSystem solarSystem = new SolarSystem(PLANETS,SUN);
        int quantityOfDroughtDays = 0;
        int quantityOfRainDays = 0;
        int quantityOfOptimumDays = 0;
        int higherDayIntensityOfRain = 0;
        BigDecimal higherIntensityOfRain = BigDecimal.ZERO;
        final List<DayPrediction> predictions = new ArrayList<>();

        for (int day = 1;day <= quantityOfDays;day++){
            solarSystem.passADay();
            final Prediction prediction = predictor.predict(solarSystem);
            final Weather weatherPredicted = prediction.getWeather();
            switch (weatherPredicted){
                case DROUGHT:
                    quantityOfDroughtDays++;
                    break;
                case RAIN:
                    quantityOfRainDays++;
                    if (higherIntensityOfRain.compareTo(prediction.getIntensity()) < 0){
                        higherDayIntensityOfRain = day;
                        higherIntensityOfRain = prediction.getIntensity();
                    }
                case OPTIMUM:
                    quantityOfOptimumDays++;
                    break;
            }

            final DayPrediction dayPrediction = new DayPrediction(day,prediction.getWeather(),prediction.getIntensity());
            predictions.add(dayPrediction);

        }
    }





}
