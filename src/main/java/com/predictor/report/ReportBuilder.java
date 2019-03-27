package com.predictor.report;

import com.predictor.prediction.WeatherPrediction;
import com.predictor.weather.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

@Component
public class ReportBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportBuilder.class);

    private long moreRainyDay = 0;
    private double currentIntensityRain = 0;

    private Map<Weather,Integer> quantityOfDaysByWeather = new HashMap<>();


    /**
     *
     * Set default values for all Weathers
     * */

    @PostConstruct
    public void init(){
        newArrayList(Weather.values()).forEach(
                w -> quantityOfDaysByWeather.put(w,0)
        );
    }


    public void register(WeatherPrediction weatherPrediction, long day){
        final Weather weather = weatherPrediction.getWeather();
        final int currentValue = quantityOfDaysByWeather.get(weather);
        quantityOfDaysByWeather.put(weather,currentValue+1);

        if(Weather.RAIN.equals(weather)){
            if(weatherPrediction.getIntensity() > currentIntensityRain){
                currentIntensityRain = weatherPrediction.getIntensity();
                moreRainyDay = day;
            }
        }
    }

    public void showReport(){
        LOGGER.info("Resultado final del sistema de prediccion de clima");
        LOGGER.info("Dias con estado optimo   =  {}",this.quantityOfDaysByWeather.get(Weather.OPTIMUM));
        LOGGER.info("Dias con estado lluvioso =  {}",this.quantityOfDaysByWeather.get(Weather.RAIN));
        LOGGER.info("Dia mas lluvioso         =  {}",this.moreRainyDay);
        LOGGER.info("Dias con sequia          =  {}",this.quantityOfDaysByWeather.get(Weather.DROUGHT));
        LOGGER.info("Dias sin predecir        =  {}",this.quantityOfDaysByWeather.get(Weather.UNKNOWN));
    }





}
