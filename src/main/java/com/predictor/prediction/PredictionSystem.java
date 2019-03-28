package com.predictor.prediction;

import com.predictor.dao.entity.Forecast;
import com.predictor.dao.ForecastDAO;
import com.predictor.report.ReportBuilder;
import com.predictor.universe.SolarSystem;
import com.predictor.weather.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.System.currentTimeMillis;


@Component
public class PredictionSystem {

    private static final Logger LOGGER = LoggerFactory.getLogger(PredictionSystem.class);
    private ReportBuilder builder;
    private ForecastDAO forecastDAO;
    private Predictor predictor;
    private SolarSystem solarSystem;

    private static final int DAYS_TO_PREDICT = 10000;


    @Autowired
    public PredictionSystem(Predictor predictor, ReportBuilder builder, ForecastDAO forecastDAO, SolarSystem solarSystem) {
        this.predictor = predictor;
        this.builder = builder;
        this.forecastDAO = forecastDAO;
        this.solarSystem = solarSystem;
    }


    @PostConstruct
    public void init(){
        this.predictWeatherForDays(DAYS_TO_PREDICT);
    }

    @Async
    public void predictWeatherForDays(Integer quantityOfDays){
        long executionTime = currentTimeMillis();
        LOGGER.info("Init calculate predictions");
        for (int day = 1 ; day <= quantityOfDays; day++){
            try{
                final WeatherPrediction weatherPredicted = predictor.predict(solarSystem,day);
                LOGGER.debug("For day {} the weather applied is {}",day, weatherPredicted.getWeather());
                builder.register(weatherPredicted,day);
                forecastDAO.save(new Forecast(day, weatherPredicted.getWeather()));
            }catch (Exception e){
                forecastDAO.save(new Forecast(day, Weather.INCALCULABLE));
            }

        }
        LOGGER.info("Calculate predictions finished in [{}] ms ",currentTimeMillis() - executionTime);
        builder.showReport();
    }
}
