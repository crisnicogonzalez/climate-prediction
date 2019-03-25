package com.predictor.service;

import com.predictor.prediction.DayPrediction;
import com.predictor.prediction.DayPredictionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PredictionService {

    @Autowired
    private DayPredictionDAO predictionDAO;

    public DayPrediction getPrediction(int day){
       return predictionDAO.get(day);
    }
}
