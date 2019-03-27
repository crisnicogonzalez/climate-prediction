package com.predictor.condition;

import com.predictor.prediction.Prediction;

public class ConditionResult {
    private Prediction prediction;


    public ConditionResult(Prediction prediction) {
        this.prediction = prediction;
    }

    public Prediction getPrediction() {
        return prediction;
    }

    public void setPrediction(Prediction prediction) {
        this.prediction = prediction;
    }
}
