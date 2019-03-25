package com.predictor.prediction;


import com.predictor.weather.Weather;
import org.springframework.stereotype.Component;

@Component
public class DayPredictionDAO {

    public void save(DayPrediction dayPrediction){}


    /**
     * Find prediction by day
     * @param day is a int not null
     * @return prediction of day
     * */
    public DayPrediction get(int day){
        return new DayPrediction(day, Weather.DROUGHT,10);
    }
}
