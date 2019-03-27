package com.predictor.prediction;

import com.predictor.dao.entity.Forecast;
import com.predictor.dao.ForecastDAO;
import com.predictor.report.ReportBuilder;
import com.predictor.universe.SolarSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.google.common.collect.Lists.newArrayList;


@Component
public class PredictionSystem {

    private static final Logger LOGGER = LoggerFactory.getLogger(PredictionSystem.class);
    private ReportBuilder builder;
    private ForecastDAO forecastDAO;
    private Predictor predictor;
    private SolarSystem solarSystem;


    @Autowired
    public PredictionSystem(Predictor predictor, ReportBuilder builder, ForecastDAO forecastDAO, SolarSystem solarSystem) {
        this.predictor = predictor;
        this.builder = builder;
        this.forecastDAO = forecastDAO;
        this.solarSystem = solarSystem;
    }


    @PostConstruct
    public void init(){
        this.predictWeatherForDays(365);
    }

    @Async
    public void predictWeatherForDays(Integer quantityOfDays){
        for (int day = 1 ; day <= quantityOfDays; day++){
            final WeatherPrediction weatherPredicted = predictor.predict(solarSystem,day);
            LOGGER.info("For day {} the weather applied is {}",day, weatherPredicted.getWeather());
            builder.register(weatherPredicted,day);
            forecastDAO.save(new Forecast(day, weatherPredicted.getWeather()));
        }
        builder.showReport();
    }
}
