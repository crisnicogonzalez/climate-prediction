package com.predictor.service;
import com.predictor.dao.DayPrediction;
import com.predictor.dao.DayPredictionDAO;
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
