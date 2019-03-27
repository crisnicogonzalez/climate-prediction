package com.predictor.prediction;

import com.predictor.condition.WeatherCondition;
import com.predictor.dao.entity.ForecastPrediction;
import com.predictor.dao.ForecastPredictionDAO;
import com.predictor.report.Report;
import com.predictor.report.ReportBuilder;
import com.predictor.universe.Planet;
import com.predictor.universe.SolarSystem;
import com.predictor.universe.Sun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;


@Component
public class PredictionSystem {

    @Autowired
    private Predictor predictor;
    private static final Sun SUN = new Sun();
    private List<Planet> planets;
    private static final Logger LOGGER = LoggerFactory.getLogger(PredictionSystem.class);
    @Autowired
    private ReportBuilder builder;
    @Autowired
    private ForecastPredictionDAO forecastPredictionDAO;



    public PredictionSystem() {
    }

    @PostConstruct
    public void init(){
        this.predictWeatherForDays(100);
    }

    public void predictWeatherForDays(Integer quantityOfDays){
        final List<Planet> planets = newArrayList(Planet.BETASOID,Planet.FERENGI,Planet.VULCANO);
        final SolarSystem solarSystem = new SolarSystem(planets,new Sun());
        for (int day = 1;day <= quantityOfDays;day++){
            final WeatherCondition predictionFulfilled = predictor.predict(solarSystem,day);
            final Prediction prediction = predictionFulfilled.getPrediction(solarSystem,day);
            LOGGER.info("For day {} the weather applied is {}",day,prediction.getWeather());
            builder.register(predictionFulfilled,prediction);
            forecastPredictionDAO.save(new ForecastPrediction(day,prediction.getWeather()));
        }
        final Report report = builder.doReport();
    }
}
