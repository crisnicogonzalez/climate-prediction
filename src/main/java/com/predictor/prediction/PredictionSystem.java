package com.predictor.prediction;

import com.predictor.condition.WeatherCondition;
import com.predictor.prediction.predictionCounter.Counter;
import com.predictor.system.Planet;
import com.predictor.system.SolarSystem;
import com.predictor.system.Sun;
import com.predictor.weather.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;


@Component
public class PredictionSystem {

    @Autowired
    private Predictor predictor;
    private static final Sun SUN = new Sun();
    private List<Planet> planets;


    public PredictionSystem() {
    }

    public void predictWeatherForDays(Integer quantityOfDays){
        final List<Planet> planets = newArrayList(Planet.BETASOID,Planet.FERENGI,Planet.VULCANO);
        final SolarSystem solarSystem = new SolarSystem(planets,new Sun());
        final Counter counter = new Counter();
        for (int day = 1;day <= quantityOfDays;day++){
            solarSystem.passADay();
            final WeatherCondition prediction = predictor.predict(solarSystem,day);
            prediction.accept(counter);
        }
    }
}
